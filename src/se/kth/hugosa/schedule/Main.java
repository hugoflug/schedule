package se.kth.hugosa.schedule;

import org.jgap.InvalidConfigurationException;
import org.json.simple.parser.ParseException;
import se.kth.hugosa.schedule.genetic.GeneticSchedule;
import se.kth.hugosa.schedule.tabusearch.TabuSearcher;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        if (args[0].equals("--tabu") || args[0].equals("-t")) {
            int listSize = 10;
            int iterations = 10000;
            int moves = 100;
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("--listsize")) {
                    listSize = Integer.parseInt(args[i+1]);
                } else if (args[i].equals("--iter")) {
                    iterations = Integer.parseInt(args[i+1]);
                } else if (args[i].equals("--neighbors")) {
                    moves = Integer.parseInt(args[i+1]);
                }
            }
            tabuSearch(args[1], listSize, iterations, moves);
        } else if (args[0].equals("--genetic") || args[0].equals("-g")) {
            genetic(args[1]);
        }
    }

    public static void genetic(String constraintFile) {
        Loader loader = new Loader();
        Evaluator evaluator = new Evaluator();
        Constraints constraints = null;
        try {
            constraints = loader.loadConstraints(constraintFile);
        } catch (IOException e) {
            System.out.println("Couldn't load constraints file");
            return;
        } catch (ParseException e) {
            System.out.println("Couldn't parse constraints file: " + e);
            return;
        }

        GeneticSchedule genetic = null;
        try {
            genetic = new GeneticSchedule(constraints, 60);
        } catch (InvalidConfigurationException e) {
            System.out.println("Genetic algorithm failed.");
            return;
        }
        ArrayList<Schedule> schedules = genetic.evolve(500);

        printEvaluation(schedules, evaluator, constraints);
    }

    public static void tabuSearch(String constraintFile, int listSize, int iterations, int moves) {
        Evaluator evaluator = new Evaluator();
        Loader loader = new Loader();

        Constraints constraints = null;
        try {
            constraints = loader.loadConstraints(constraintFile);
        } catch (IOException e) {
            System.out.println("Couldn't load constraints file");
            return;
        } catch (ParseException e) {
            System.out.println("Couldn't parse constraints file: " + e);
            return;
        }

        ArrayList<Schedule> schedules = TabuSearcher.tabuSearch(evaluator, constraints, listSize, iterations, moves);
        String outSchedule = Schedule.schedulesToString(schedules);

        printEvaluation(schedules, evaluator, constraints);
    }

    private static void printEvaluation(ArrayList<Schedule> schedules, Evaluator evaluator, Constraints constraints) {
        String outSchedule = Schedule.schedulesToString(schedules);
        System.out.println(outSchedule);
        Evaluator.Result result =  evaluator.evaluateWithInfo(schedules, constraints);
        System.out.println();
        System.out.println("Free periods penalty: " + result.getFreePeriods());
        System.out.println("Multiple lessons in same course on on same day penalty: " + result.getOnSameDay());
        System.out.println("Total penalty: " + result.getTotal());
    }
}
