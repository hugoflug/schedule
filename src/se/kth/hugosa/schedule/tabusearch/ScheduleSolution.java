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

    //for clone() to function properly
    public ScheduleSolution() {}

    public ScheduleSolution(ArrayList<Schedule> schedules, Constraints constraints) {
        Map<String, Schedule> programs = new HashMap<String, Schedule>();

        for (ScheduleElement element : constraints.getScheduleElements()) {
            Schedule schedule = programs.get(element.getProgram());
            if (schedule == null) {
                programs.put(element.getProgram(), new Schedule(element.getProgram(), 15));
                schedule = programs.get(element.getProgram());
            }
    //      schedule.addInNextPlace(element)
        }
        //this.schedules = programs.values

        this.schedules = schedules;
    }

    public Object clone() {
        ScheduleSolution copy = (ScheduleSolution)super.clone();
        copy.schedules = (ArrayList<Schedule>)schedules.clone();
        return this;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
}
