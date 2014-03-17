package se.kth.hugosa.schedule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EvaluatorTest {
    @Test
    public void testFreePeriods() throws Exception {
        Schedule schedule = new Schedule("CDATE2", 2);
        TimeSlot first = schedule.days.get(0).timeSlots.get(0);
        first.scheduleElement = new ScheduleElement("Anna Andersson", "progp", "CDATE2", 200);
        first.classroom = new Classroom("E32", 230);

        TimeSlot second = schedule.days.get(0).timeSlots.get(3);
        second.scheduleElement = new ScheduleElement("Anna Andersson", "progp", "CDATE2", 200);
        second.classroom = new Classroom("E32", 230);

        TimeSlot third = schedule.days.get(1).timeSlots.get(2);
        third.scheduleElement = new ScheduleElement("Anna Andersson", "progp", "CDATE2", 200);
        third.classroom = new Classroom("E32", 230);

        Evaluator evaluator = new Evaluator();
        double result = evaluator.evaluateSchedule(new ArrayList<Schedule>(Arrays.asList(schedule)), null);

        System.out.println(result);
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
        schedule1.days = new ArrayList<Day>(Arrays.asList(day1));

        Evaluator evaluator = new Evaluator();
        double result = evaluator.evaluateSchedule(Arrays.asList(schedule1), null);

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
        schedule1.days = new ArrayList<Day>(Arrays.asList(day1));

        Schedule schedule2 = new Schedule("CDATE1",2);
        Day day2_1 = new Day();
        day2_1.timeSlots = new ArrayList<TimeSlot>();
        day2_1.timeSlots.add(new TimeSlot(new Classroom("E31", 50),
                new ScheduleElement("Markus", "inda", "CDATE1", 40)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("D1", 200),
                new ScheduleElement("Peter", "fysik", "CDATE1", 190)));

        day2_1.timeSlots.add(new TimeSlot(new Classroom("E2", 110),
                new ScheduleElement("Anders", "numme", "CDATE1", 100)));
        schedule2.days = new ArrayList<Day>(Arrays.asList(day2_1));

        double result = evaluator.evaluateSchedule(Arrays.asList(schedule1, schedule2), null);

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
