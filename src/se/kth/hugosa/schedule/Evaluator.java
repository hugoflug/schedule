package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Evaluator {
    public int evaluateSchedule(List<Schedule> schedules, Constraints constraints) {
        for (int i = 0; i < schedules.get(0).days.size(); i++) {

           //pick out all Days on a certain date
           List<Day> days = new ArrayList<Day>();
           for (Schedule schedule : schedules) {
               days.add(schedule.days.get(i));
           }

           for (int j = 0; j < 4; j++) {

               //pick out all TimeSlots on a certain date and a certain time
               List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
               for (Day day : days) {
                   if (day.timeSlots.size() > j) {
                       timeSlots.add(day.timeSlots.get(j));
                   }
               }

               if (collides(timeSlots)) {
                   return 0;
               }

               for (TimeSlot timeSlot : timeSlots) {
                   if (timeSlot.scheduleElement.numStudents > timeSlot.classroom.capacity) {
                       return 0;
                   }
               }
           }

        }
        return 100;
    }

    //checks for collisions between a list of SchoolClasses held at the same time
    private boolean collides(List<TimeSlot> timeSlots) {
        Set<String> busyTeachers = new HashSet<String>();
        Set<Classroom> busyClassrooms = new HashSet<Classroom>();
        for (TimeSlot timeSlot : timeSlots) {
            if (busyTeachers.contains(timeSlot.scheduleElement.teacher)) {
                return true;
            } else {
                busyTeachers.add(timeSlot.scheduleElement.teacher);
            }

            if (busyClassrooms.contains(timeSlot.classroom)) {
                return true;
            } else {
                busyClassrooms.add(timeSlot.classroom);
            }

        }
        return false;
    }

}
