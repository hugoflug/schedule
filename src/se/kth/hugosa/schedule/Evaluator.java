package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<String> busyTeachers = new HashSet<String>();
        Set<Classroom> busyClassrooms = new HashSet<Classroom>();
        for (SchoolClass sClass : classes) {
            for (CourseMoment mom : sClass.moments) {
                for (String teacher : mom.teachers) {
                    if (busyTeachers.contains(teacher)) {
                        return true;
                    } else {
                        busyTeachers.add(teacher);
                    }
                    if (busyClassrooms.contains(mom.classroom)) {
                        return true;
                    } else {
                        busyClassrooms.add(mom.classroom);
                    }
                }
            }
        }
        return false;
    }
}
