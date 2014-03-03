package se.kth.hugosa.schedule;

import org.jgap.Configuration;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.FitnessFunction;
import java.util.ArrayList;

public class GeneticSchedule {
	Configuration conf = new DefaultConfiguration();
	FitnessFunction func;
	
	public GeneticSchedule ( Constraints constraints, ArrayList<ScheduleElement> list){
		func = new ScheduleFitnessFunction( constraints, list);
	}
	
}
