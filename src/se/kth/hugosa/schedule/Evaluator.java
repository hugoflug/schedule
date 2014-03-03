package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Evaluator {
    public int evaluateSchedule(ArrayList<Schedule> schedules, Constraints constraints) {
        for (int i = 0; i < schedules.get(0).days.size(); i++) {

           //pick out all Days on a certain date
           List<Day> days = new ArrayList<Day>();
           for (Schedule schedule : schedules) {
               days.add(schedule.days.get(i));
           }

           for (int j = 0; j < 4; j++) {

               //pick out all SchoolClasses on a certain date and a certain time
               List<SchoolClass> sClasses = new ArrayList<SchoolClass>();
               for (Day day : days) {
                   sClasses.add(day.classes.get(j));
               }

               //collides(sClasses);
           }

        }
        return 0;
    }

    //returns the total amount of space in a certain class
    /*
    private int getCapacity(SchoolClass sClass) {
        int cap = 0;
        for (CourseMoment mom : sClass.moments) {
            cap += mom.classroom.capacity;
        }
        return cap;
    }

    //checks for collisions between a list of SchoolClasses held at the same time
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
    */
}
