package se.kth.hugosa.schedule;

import java.util.ArrayList;

import org.junit.Test;
import se.kth.hugosa.schedule.genetic.GeneticSchedule;

public class GeneticTest {

	@Test
	public void test() throws Exception{
		Loader loader = new Loader();
		Constraints c = loader.loadConstraints("tests/input_test.txt");
		
		for (ScheduleElement e : c.getScheduleElements()) {
            System.out.println(e.toString());
        }
        for (Classroom room : c.getClassrooms()){
        	System.out.println("Name: " + room.name + " cap: " + room.capacity);
        }
        
		GeneticSchedule genetic = new GeneticSchedule(c, 20);
		ArrayList<Schedule> schedules = genetic.evolve(100);
		
		Schedule.printSchedule(schedules);
		
		
		
	}

}
