package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Solution;
import org.coinor.opents.SolutionAdapter;
import se.kth.hugosa.schedule.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleSolution extends SolutionAdapter {
    private ArrayList<Schedule> schedules;

    //for clone() to function properly
    public ScheduleSolution() {}

    public ScheduleSolution(Constraints constraints) {
        Map<String, Schedule> programs = new HashMap<String, Schedule>();
        Map<String, Integer> currentDay = new HashMap<String, Integer>();
        Map<String, Integer> currentTimeSlot = new HashMap<String, Integer>();


        for (ScheduleElement element : constraints.getScheduleElements()) {
            Schedule schedule = programs.get(element.getProgram());
            if (schedule == null) {
                programs.put(element.getProgram(), new Schedule(element.getProgram(),
                        constraints.getScheduleWeeks()));
                schedule = programs.get(element.getProgram());
            }

            Integer day =  currentDay.get(element.getProgram());
            if (day == null) {
                currentDay.put(element.getProgram(), 0);
                day = 0;
            }

            Integer timeSlot =  currentTimeSlot.get(element.getProgram());
            if (timeSlot == null) {
                currentTimeSlot.put(element.getProgram(), 0);
                timeSlot = 0;
            }

            Classroom classroom = Util.getRandomElement(constraints.getClassrooms());
            schedule.days.get(day).timeSlots.set(timeSlot, new TimeSlot(classroom, element));
            currentTimeSlot.put(element.getProgram(), currentTimeSlot.get(element.getProgram()) + 1);
            if (currentTimeSlot.get(element.getProgram()) == 4) {
                currentTimeSlot.put(element.getProgram(), 0);
                currentDay.put(element.getProgram(), currentDay.get(element.getProgram()) + 1);
            }
        }
        this.schedules = new ArrayList<Schedule>(programs.values());
    }

    public Object clone() {
        ScheduleSolution copy = (ScheduleSolution)super.clone();
        copy.schedules = new ArrayList<Schedule>();
        for (Schedule schedule : schedules) {
            copy.schedules.add(schedule.copy());
        }

        return copy;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
}
