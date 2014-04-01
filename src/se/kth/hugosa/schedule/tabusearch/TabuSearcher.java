package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.*;
import se.kth.hugosa.schedule.Constraints;
import se.kth.hugosa.schedule.Evaluator;
import se.kth.hugosa.schedule.Schedule;

import java.util.ArrayList;

public class TabuSearcher {
    public static ArrayList<Schedule> tabuSearch(Evaluator evaluator, Constraints constraints, int tabuListSize, int iterations, int moves, final int time, final boolean print) {
        ObjectiveFunction objFunc = new ScheduleObjectiveFunction(evaluator, constraints);
        Solution initialSolution = new ScheduleSolution(constraints);
        MoveManager moveManager = new ScheduleMoveManager(constraints, moves);
        TabuList tabuList = new SimpleTabuList(tabuListSize);

        final TabuSearch tabuSearch = new SingleThreadedTabuSearch(
                initialSolution,
                moveManager,
                objFunc,
                tabuList,
                new BestEverAspirationCriteria(),
                false
        );

        final long startTime = System.nanoTime();

        tabuSearch.addTabuSearchListener(new TabuSearchAdapter() {
            @Override
            public void newBestSolutionFound(TabuSearchEvent e) {
                if (tabuSearch.getBestSolution().getObjectiveValue()[0] == 0) {
                	if (print) {
                		System.out.println("];");
                	}
                	System.out.println("Perfect solution found in: " + tabuSearch.getIterationsCompleted());
                    tabuSearch.setIterationsToGo(0);
                }
            }
            @Override
            public void newCurrentSolutionFound(TabuSearchEvent e) {
            	if(print){
            		System.out.print("" + tabuSearch.getIterationsCompleted() + ", " + tabuSearch.getBestSolution().getObjectiveValue()[0] + "; ");
            	}
                if (time != -1) {
                    if ((System.nanoTime() - startTime)/1000000 > time) {
                    	if (print) {
                    		System.out.println("];");
                    	}
                        System.out.println("Time is up (" + time + " ms, "+ tabuSearch.getIterationsCompleted() + " iterations)");
                        tabuSearch.setIterationsToGo(0);
                    }
                }
            }
        });

        tabuSearch.setIterationsToGo(iterations);
        tabuSearch.startSolving();
        ScheduleSolution best = (ScheduleSolution)tabuSearch.getBestSolution();

        return best.getSchedules();
    }
}
