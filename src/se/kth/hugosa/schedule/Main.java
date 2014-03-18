package se.kth.hugosa.schedule;

import org.coinor.opents.*;
import org.json.simple.parser.ParseException;
import se.kth.hugosa.schedule.tabusearch.ScheduleMoveManager;
import se.kth.hugosa.schedule.tabusearch.ScheduleObjectiveFunction;
import se.kth.hugosa.schedule.tabusearch.ScheduleSolution;
import se.kth.hugosa.schedule.tabusearch.TabuSearcher;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        if (args[1].equals("--tabu") || args[1].equals("-t")) {
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
            tabuSearch(args[2], listSize, iterations, moves);
        }
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

        System.out.println(outSchedule);
        System.out.println("\n\nbest value: " +
                evaluator.evaluateWithInfo(schedules, constraints));
    }
}
