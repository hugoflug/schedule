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
            int time = -1;
            String name = "";
            Mode mode = Mode.VERBOSE;
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("--listsize")) {
                    listSize = Integer.parseInt(args[i+1]);
                } else if (args[i].equals("--iter")) {
                    iterations = Integer.parseInt(args[i+1]);
                } else if (args[i].equals("--neighbors")) {
                    moves = Integer.parseInt(args[i+1]);
                } else if (args[i].equals(("--time"))) {
                    time = Integer.parseInt(args[i+1]);
                } else if (args[i].equals(("--mode"))) {
                    if (args[i+1].equals("PRINT_ITERS")) {
                        mode = Mode.PRINT_ITERS;
                    } else if (args[i+1].equals("PRINT_TIME")) {
                        mode = Mode.PRINT_TIME;
                    }
                } else if (args[i].equals(("--name"))) {
                    name = args[i+1];
                }
            }
            tabuSearch(args[1], listSize, iterations, moves, time, mode, name);
        } else if (args[0].equals("--genetic") || args[0].equals("-g")) {
        	int populationSize = 60;
            int iterations = 10000;
            int time = -1;
            String name = "";
            double mRate = 1.0;
            double cRate = 1.0;
            boolean mDynamic = true;
            boolean cDynamic = true;
            
            Mode mode = Mode.VERBOSE;
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("--populationsize")) {
                    populationSize = Integer.parseInt(args[i+1]);
                } else if (args[i].equals("--iter")) {
                    iterations = Integer.parseInt(args[i+1]);
                } else if (args[i].equals(("--time"))) {
                    time = Integer.parseInt(args[i+1]);
                } else if (args[i].equals(("--mutationrate"))) {
                    mRate = Double.parseDouble(args[i+1]);
                } else if (args[i].equals(("--setMRnotdynamic"))) {
                    mDynamic = false;
                } else if (args[i].equals(("--crossoverrate"))) {
                    cRate = Double.parseDouble(args[i+1]);
                } else if (args[i].equals(("--setCRnotdynamic"))) {
                    cDynamic = false;
                } else if (args[i].equals(("--mode"))) {
                    if (args[i+1].equals("PRINT_ITERS")) {
                        mode = Mode.PRINT_ITERS;
                    } else if (args[i+1].equals("PRINT_TIME")) {
                        mode = Mode.PRINT_TIME;
                    }
                } else if (args[i].equals(("--name"))) {
                    name = args[i+1];
                }
            }
            genetic(args[1], populationSize, iterations, mRate, mDynamic, cRate, cDynamic, time, mode, name);
        }
    }

    public static void genetic(String constraintFile, int populationSize, int iterations, double mRate, boolean mDynamic, double cRate, boolean cDynamic, int time, Mode mode, String name) {
        Loader loader = new Loader();
        Evaluator evaluator = new Evaluator();
        Constraints constraints = null;
        long timeStart;
        long timeDelta;
        
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
            genetic = new GeneticSchedule(constraints, populationSize, mRate, mDynamic, cRate, cDynamic, time);
        } catch (InvalidConfigurationException e) {
            System.out.println("Genetic algorithm failed.");
            return;
        }
        if (mode == Mode.PRINT_ITERS) {
        	System.out.print("Genetic_" + name + " = [");
        }
        timeStart = System.currentTimeMillis();
        ArrayList<Schedule> schedules = genetic.evolve(iterations, mode);
        timeDelta = System.currentTimeMillis() - timeStart;
        printEvaluation(schedules, evaluator, constraints, timeDelta, mode, name);
        
    }

    public static void tabuSearch(String constraintFile, int listSize, int iterations, int moves, int time, Mode mode, String name) {
        Evaluator evaluator = new Evaluator();
        Loader loader = new Loader();
        long timeStart;
        long timeDelta;

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
        
        if(mode == Mode.PRINT_ITERS){
        	System.out.print("Tabu_" + name + " = [");
        }
        timeStart = System.currentTimeMillis();
        ArrayList<Schedule> schedules = TabuSearcher.tabuSearch(evaluator, constraints, listSize, iterations, moves, time, mode);
        timeDelta = System.currentTimeMillis() - timeStart;
        String outSchedule = Schedule.schedulesToString(schedules);

        printEvaluation(schedules, evaluator, constraints, timeDelta, mode, name);
    }

    private static void printEvaluation(ArrayList<Schedule> schedules, Evaluator evaluator, Constraints constraints, long time, Mode mode, String name) {
        if (mode == Mode.VERBOSE) {
            String outSchedule = Schedule.schedulesToString(schedules);
            System.out.println(outSchedule);
            Evaluator.Result result =  evaluator.evaluateWithInfo(schedules, constraints);
            System.out.println();
            System.out.println("Schedule generated in: " + (time / 1000.0) + " seconds.");
            System.out.println("Free periods penalty: " + result.getFreePeriods());
            System.out.println("Multiple lessons in same course on same day penalty: " + result.getOnSameDay());
            System.out.println("Collisions: " + result.getCollisions());
            System.out.println("Over capacities: " + result.getOverCapacities());
            System.out.println("Total penalty: " + result.getTotal());
        } else if (mode == Mode.PRINT_TIME) {
            System.out.println("time_"  + name + " = " + (time / 1000.0));
        }
    }
}
