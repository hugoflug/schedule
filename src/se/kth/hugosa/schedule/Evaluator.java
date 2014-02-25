package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {
    //needs to also take constraints in the future
    public int evaluateSchedule(ArrayList<Schedule> schedules) {
        for (int i = 0; i < schedules.get(0).days.size(); i++) {
           for (Schedule schedule : schedules) {
               Day d = schedule.days.get(i);

           }
        }
        return 0;
    }

    //checks for collisions between a list of classes held at the same time
    private boolean collides(List<SchoolClass> classes) {
        return false;
    }
}
