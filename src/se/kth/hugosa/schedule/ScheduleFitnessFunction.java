package se.kth.hugosa.schedule;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import java.util.ArrayList;

public class ScheduleFitnessFunction extends FitnessFunction {
	private Evaluator e;
	private Constraints constraints;
	
	public ScheduleFitnessFunction( Constraints constraints){
		this.constraints = constraints;
	}
	
	@Override
    public double evaluate( IChromosome c ){
        ArrayList<Schedule> s = GeneticSchedule.generateSchedule(c, constraints);
        return (double) e.evaluateSchedule(s, constraints);
    }
}