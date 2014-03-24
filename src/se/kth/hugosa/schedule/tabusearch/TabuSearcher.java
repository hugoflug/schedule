package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.*;
import se.kth.hugosa.schedule.Constraints;
import se.kth.hugosa.schedule.Evaluator;
import se.kth.hugosa.schedule.Schedule;

import java.util.ArrayList;

public class TabuSearcher {
    public static ArrayList<Schedule> tabuSearch(Evaluator evaluator, Constraints constraints, int tabuListSize, int iterations, int moves) {
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

        tabuSearch.addTabuSearchListener(new TabuSearchAdapter() {
            public void newBestSolutionFound(TabuSearchEvent e) {
                if (tabuSearch.getBestSolution().getObjectiveValue()[0] == 0) {
                    System.out.println("Perfect solution found in: " + tabuSearch.getIterationsCompleted());
                    tabuSearch.setIterationsToGo(0);
                }
            }
        });

        tabuSearch.setIterationsToGo(iterations);
        tabuSearch.startSolving();
        ScheduleSolution best = (ScheduleSolution)tabuSearch.getBestSolution();

        return best.getSchedules();
    }
}
