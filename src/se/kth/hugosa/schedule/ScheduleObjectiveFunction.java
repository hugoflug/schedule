package se.kth.hugosa.schedule;

import org.coinor.opents.Move;
import org.coinor.opents.ObjectiveFunction;
import org.coinor.opents.Solution;

public class ScheduleObjectiveFunction implements ObjectiveFunction {
    private Evaluator eval;
    private Constraints constraints;

    public ScheduleObjectiveFunction(Evaluator eval, Constraints constraints) {
        this.eval = eval;
        this.constraints = constraints;
    }

    @Override
    public double[] evaluate(Solution solution, Move move) {
        ScheduleSolution scheduleSolution = (ScheduleSolution)solution;
        return new double[eval.evaluateSchedule(scheduleSolution.getSchedules(), constraints)];
    }
}
