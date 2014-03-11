package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Solution;
import org.coinor.opents.SolutionAdapter;
import se.kth.hugosa.schedule.Constraints;
import se.kth.hugosa.schedule.Schedule;
import se.kth.hugosa.schedule.ScheduleElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleSolution extends SolutionAdapter {
    private ArrayList<Schedule> schedules;

    public ScheduleSolution(ArrayList<Schedule> schedules, Constraints constraints) {
        Map<String, Schedule> programs = new HashMap<String, Schedule>();

        for (ScheduleElement element : constraints.getScheduleElements()) {
            Schedule schedule = programs.get(element.program);
            if (schedule == null) {
                programs.put(element.program, new Schedule(element.program, 15));
                schedule = programs.get(element.program);
            }
    //      schedule.addInNextPlace(element)
        }
        //this.schedules = programs.values

        this.schedules = schedules;
    }

    public Object clone() {
        return this;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
}
