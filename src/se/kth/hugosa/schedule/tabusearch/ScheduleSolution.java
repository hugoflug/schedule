package se.kth.hugosa.schedule.tabusearch;

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

        for (ScheduleElement element : constraints.getScheduleElements()) {
            Schedule schedule = programs.get(element.getProgram());
            if (schedule == null) {
                programs.put(element.getProgram(), new Schedule(element.getProgram(),
                        constraints.getScheduleWeeks()));
                schedule = programs.get(element.getProgram());
            }

            int dayNo = Util.getRandomIndex(schedule.days);
            int timeSlotNo = Util.getRandomInt(4);
            TimeSlot timeSlot = schedule.days.get(dayNo).timeSlots.get(timeSlotNo);
            while (timeSlot.getOnlyScheduleElement() == null) {
                dayNo = Util.getRandomIndex(schedule.days);
                timeSlotNo = Util.getRandomInt(4);
                timeSlot = schedule.days.get(dayNo).timeSlots.get(timeSlotNo);
            }

            Classroom classroom = Util.getRandomElement(constraints.getClassrooms());

            schedule.days.get(dayNo).timeSlots.set(timeSlotNo, new TimeSlot(classroom, element));
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
