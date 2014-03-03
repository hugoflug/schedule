package se.kth.hugosa.schedule;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import java.util.ArrayList;

public class ScheduleFitnessFunction extends FitnessFunction {
	private Evaluator e;
	private Constraints constraints;
	private ArrayList<ScheduleElement> elements;
	
	public ScheduleFitnessFunction( Constraints constraints, ArrayList<ScheduleElement> list){
		this.constraints = constraints;
		elements = list;
	}
	
	@Override
    public double evaluate( IChromosome c ){
        ArrayList<Schedule> s = getSchedules(c);
        return (double) e.evaluateSchedule(s, constraints);
    }
	
	private static ArrayList<Schedule> getSchedules( IChromosome c ){
		return null;
	}
}