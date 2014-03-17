package se.kth.hugosa.schedule.genetic;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import se.kth.hugosa.schedule.Constraints;
import se.kth.hugosa.schedule.Evaluator;
import se.kth.hugosa.schedule.Schedule;

import java.util.ArrayList;

public class ScheduleFitnessFunction extends FitnessFunction {
	private Constraints constraints;
	private Evaluator e;
	
	public ScheduleFitnessFunction(Constraints constraints){
		this.e = new Evaluator();
		this.constraints = constraints;
	}
	
	@Override
    public double evaluate( IChromosome c ){
        ArrayList<Schedule> s = GeneticSchedule.generateSchedule(c, constraints);
        return (double) e.evaluateSchedule(s, constraints);
    }
}