package se.kth.hugosa.schedule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EvaluatorTest {
    @Test
    public void bogus() throws Exception {

    }

    /*
    @Test
    public void testEvaluateSchedule() throws Exception {
        testTeacherCollision();
        testClassroomCollision();
        testClassroomCapacity();
        testNoCollision();
    }

    private void testClassroomCapacity() throws Exception {
        Schedule schedule1 = new Schedule("CDATE2", 2);
        Day day1 = new Day();

        day1.timeSlots = new ArrayList<TimeSlot>();
        day1.timeSlots.add(new TimeSlot(new Classroom("E32", 50),
                new ScheduleElement("Gunilla", "progp", "CDATE2", 60)));
        schedule1.days = Arrays.asList(day1);

        Evaluator evaluator = new Evaluator();
        int result = evaluator.evaluateSchedule(Arrays.asList(schedule1), null);

        if (result != 0) {
            throw new Exception();
        }
    }


    private void testClassroomCollision() throws Exception {
        Schedule schedule1 = new Schedule("CDATE2", 2);
        Day day1 = new Day();

        day1.timeSlots = new ArrayList<TimeSlot>();
        day1.timeSlots.add(new TimeSlot(new Classroom("E32", 50),
                new ScheduleElement("Gunilla", "progp", "CDATE2", 40)));

        day1.timeSlots.add(new TimeSlot(new Classroom("E1", 200),
                new ScheduleElement("Jan", "dtek", "CDATE2", 190)));

        day1.timeSlots.add(new TimeSlot(new Classroom("E2", 110),
                new ScheduleElement("Hugo", "logik", "CDATE2", 100)));
        schedule1.days = Arrays.asList(day1);

        Schedule schedule2 = new Schedule("CDATE1",2);
        Day day2_1 = new Day();
        day2_1.timeSlots = new ArrayList<TimeSlot>();
        day2_1.timeSlots.add(new TimeSlot(new Classroom("E31", 50),
                new ScheduleElement("Markus", "inda", "CDATE1", 40)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("D1", 200),
                new ScheduleElement("Peter", "fysik", "CDATE1", 190)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("E2", 110),
                new ScheduleElement("Anders", "numme", "CDATE1", 100)));
        schedule2.days = Arrays.asList(day2_1);

        Evaluator evaluator = new Evaluator();
        int result = evaluator.evaluateSchedule(Arrays.asList(schedule1, schedule2), null);

        if (result != 0) {
            throw new Exception();
        }
    }

    public void testNoCollision() throws Exception {
        Schedule schedule1 = new Schedule("CDATE2", 2);
        Day day1 = new Day();

        day1.timeSlots = new ArrayList<TimeSlot>();
        day1.timeSlots.add(new TimeSlot(new Classroom("E32", 50),
                new ScheduleElement("Gunilla", "progp", "CDATE2", 40)));

        day1.timeSlots.add(new TimeSlot(new Classroom("E1", 200),
                new ScheduleElement("Jan", "dtek", "CDATE2", 190)));

        day1.timeSlots.add(new TimeSlot(new Classroom("D2", 110),
                new ScheduleElement("Hugo", "logik", "CDATE2", 100)));
        schedule1.days = Arrays.asList(day1);

        Schedule schedule2 = new Schedule("CDATE1", 2);
        Day day2_1 = new Day();
        day2_1.timeSlots = new ArrayList<TimeSlot>();
        day2_1.timeSlots.add(new TimeSlot(new Classroom("E31", 50),
                new ScheduleElement("Markus", "inda", "CDATE1", 40)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("D1", 200),
                new ScheduleElement("Peter", "fysik", "CDATE1", 190)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("E2", 110),
                new ScheduleElement("Anders", "numme", "CDATE1", 100)));
        schedule2.days = Arrays.asList(day2_1);

        Evaluator evaluator = new Evaluator();
        int result = evaluator.evaluateSchedule(Arrays.asList(schedule1, schedule2), null);

        if (result != 100) {
            throw new Exception();
        }
    }

    public void testTeacherCollision() throws Exception {
        Schedule schedule1 = new Schedule("CDATE2", 2);
        Day day1 = new Day();

        day1.timeSlots = new ArrayList<TimeSlot>();
        day1.timeSlots.add(new TimeSlot(new Classroom("E32", 50),
                new ScheduleElement("Gunilla", "progp", "CDATE2", 40)));

        day1.timeSlots.add(new TimeSlot(new Classroom("E1", 200),
                new ScheduleElement("Jan", "dtek", "CDATE2", 190)));

        day1.timeSlots.add(new TimeSlot(new Classroom("D2", 110),
                new ScheduleElement("Hugo", "logik", "CDATE2", 100)));
        schedule1.days = Arrays.asList(day1);

        Schedule schedule2 = new Schedule("CDATE1", 2);
        Day day2_1 = new Day();
        day2_1.timeSlots = new ArrayList<TimeSlot>();
        day2_1.timeSlots.add(new TimeSlot(new Classroom("E31", 50),
                new ScheduleElement("Markus", "inda", "CDATE1", 40)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("D1", 200),
                new ScheduleElement("Peter", "fysik", "CDATE1", 190)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("E2", 110),
                new ScheduleElement("Hugo", "numme", "CDATE1", 100)));
        schedule2.days = Arrays.asList(day2_1);

        Evaluator evaluator = new Evaluator();
        int result = evaluator.evaluateSchedule(Arrays.asList(schedule1, schedule2), null);

        if (result != 0) {
            throw new Exception();
        }
    }
    */
}
