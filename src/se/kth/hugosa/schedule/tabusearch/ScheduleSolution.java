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

        int day = 0;
        int timeslot = 0;
        for (ScheduleElement element : constraints.getScheduleElements()) {
            Schedule schedule = programs.get(element.getProgram());
            if (schedule == null) {
                programs.put(element.getProgram(), new Schedule(element.getProgram(),
                        constraints.getScheduleWeeks()));
                schedule = programs.get(element.getProgram());
            }

            Classroom classroom = Util.getRandomElement(constraints.getClassrooms());
            schedule.days.get(day).timeSlots.set(timeslot, new TimeSlot(classroom, element));
            timeslot++;
            if (timeslot == 4) {
                timeslot = 0;
                day++;
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
