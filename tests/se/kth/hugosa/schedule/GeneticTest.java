package se.kth.hugosa.schedule;

import org.junit.Test;
import se.kth.hugosa.schedule.genetic.GeneticSchedule;

import java.util.ArrayList;

public class GeneticTest {

	@Test
	public void test() throws Exception{
		Loader loader = new Loader();
		Evaluator evaluator = new Evaluator();
		Constraints c = loader.loadConstraints("results/small.constraints");
		
		for (ScheduleElement e : c.getScheduleElements()) {
            System.out.println(e.toString());
        }
        for (Classroom room : c.getClassrooms()){
        	System.out.println("Name: " + room.name + " cap: " + room.capacity);
        }
        System.out.println();
        
		GeneticSchedule genetic = new GeneticSchedule(c, 50, -1);
		ArrayList<Schedule> schedules = genetic.evolve(500, Mode.PRINT_ITERS);
		
		System.out.println("Best fitness value: " + evaluator.evaluateSchedule(schedules, c));
		Schedule.printSchedule(schedules);
		
		
		
	}

}
