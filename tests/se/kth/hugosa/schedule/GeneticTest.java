package se.kth.hugosa.schedule;

import java.util.ArrayList;

import org.junit.Test;

public class GeneticTest {

	@Test
	public void test() throws Exception{
		Loader loader = new Loader();
		Constraints constraints = loader.loadConstraints("tests/input_test.txt");
		
		GeneticSchedule genetic = new GeneticSchedule(constraints, 20);
		ArrayList<Schedule> schedules = genetic.evolve(100);
		
		Schedule.printSchedule(schedules);
		
		
		
	}

}
