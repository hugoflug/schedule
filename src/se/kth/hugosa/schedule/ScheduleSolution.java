package se.kth.hugosa.schedule;

import org.coinor.opents.Solution;
import org.coinor.opents.SolutionAdapter;

import java.util.ArrayList;

public class ScheduleSolution extends SolutionAdapter {
    private ArrayList<Schedule> schedule;

    public ScheduleSolution(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Object clone() {
        return this;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedule;
    }
}
