package se.kth.hugosa.schedule;

import java.util.ArrayList;

import org.junit.Test;
import se.kth.hugosa.schedule.genetic.GeneticSchedule;

public class GeneticTest {

	@Test
	public void test() throws Exception{
		Loader loader = new Loader();
		Evaluator evaluator = new Evaluator();
		Constraints c = loader.loadConstraints("tests/input_test_bigger.txt");
		
		for (ScheduleElement e : c.getScheduleElements()) {
            System.out.println(e.toString());
        }
        for (Classroom room : c.getClassrooms()){
        	System.out.println("Name: " + room.name + " cap: " + room.capacity);
        }
        System.out.println();
        
		GeneticSchedule genetic = new GeneticSchedule(c, 60);
		ArrayList<Schedule> schedules = genetic.evolve(500);
		
		System.out.println("Best fitness value: " + evaluator.evaluateSchedule(schedules, c));
		Schedule.printSchedule(schedules);
		
		
		
	}

}
