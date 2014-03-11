package se.kth.hugosa.schedule;

import org.coinor.opents.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.hugosa.schedule.tabusearch.ScheduleMoveManager;
import se.kth.hugosa.schedule.tabusearch.ScheduleObjectiveFunction;
import se.kth.hugosa.schedule.tabusearch.ScheduleSolution;

public class TabuSearchTest {

    @Test
    public void testTabuSearch() throws Exception {
        Evaluator evaluator = new Evaluator();
        Loader loader = new Loader();

        Constraints constraints = loader.loadConstraints("tests/input_test.txt");

        ObjectiveFunction objFunc = new ScheduleObjectiveFunction(evaluator, constraints);
        Solution initialSolution = new ScheduleSolution(constraints);
        MoveManager moveManager = new ScheduleMoveManager(constraints);
        TabuList tabuList = new SimpleTabuList(10);

        TabuSearch tabuSearch = new SingleThreadedTabuSearch(
                initialSolution,
                moveManager,
                objFunc,
                tabuList,
                new BestEverAspirationCriteria(),
                false
            );
        tabuSearch.setIterationsToGo(10000);
        tabuSearch.startSolving();
        ScheduleSolution best = (ScheduleSolution)tabuSearch.getBestSolution();
        Schedule.printSchedule(best.getSchedules());

        System.out.println("best value: " +
                evaluator.evaluateSchedule(best.getSchedules(), constraints));
    }

}
