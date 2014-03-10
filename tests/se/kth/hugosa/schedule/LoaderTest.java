package se.kth.hugosa.schedule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.hugosa.schedule.Classroom;
import se.kth.hugosa.schedule.Constraints;

/**
 * Created by hugo on 3/3/14.
 */
public class LoaderTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLoadConstraints() throws Exception {
        Loader loader = new Loader();
        Constraints c = loader.loadConstraints("tests/input_test.txt");

        for (ScheduleElement e : c.getScheduleElements()) {
            System.out.println(e.toString());
        }
        for (Classroom room : c.getClassrooms()){
        	System.out.println("Name: " + room.name + " cap: " + room.capacity);
        }

    }
}
