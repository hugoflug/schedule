package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Evaluator {
	
	public Evaluator(){
		
	}
	
    public double evaluateSchedule(List<Schedule> schedules, Constraints constraints) {
        double value = 0;

        for (int i = 0; i < schedules.get(0).days.size(); i++) {

           //pick out all Days on a certain date
           List<Day> days = new ArrayList<Day>();
           for (Schedule schedule : schedules) {
        	   Day day = schedule.days.get(i);
        	   if(day != null){
        		   days.add(day);
        	   }
           }

           for (int j = 0; j < 4; j++) {

               //pick out all TimeSlots on a certain date and a certain time
               List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
               for (Day day : days) {
            	   TimeSlot slot = day.timeSlots.get(j);
            	   
            	   if (slot != null){
            		   timeSlots.add(slot);
            	   }
            	   
               }

               if (collides(timeSlots)) {
                   value += 1;
               }

               for (TimeSlot timeSlot : timeSlots) {
                   if (timeSlot.scheduleElement != null && timeSlot.classroom != null) {
                       if (timeSlot.scheduleElement.getNumStudents() > timeSlot.classroom.capacity) {
                           value += 1;
                       }
                   }
               }
           }

        }
        return value;
    }

    //checks for collisions between a list of SchoolClasses held at the same time
    private boolean collides(List<TimeSlot> timeSlots) {
        Set<String> busyTeachers = new HashSet<String>();
        Set<Classroom> busyClassrooms = new HashSet<Classroom>();
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.scheduleElement != null) {
                if (busyTeachers.contains(timeSlot.scheduleElement.getTeacher())) {
                    return true;
                } else {
                    busyTeachers.add(timeSlot.scheduleElement.getTeacher());
                }
            }

            if (timeSlot.classroom != null) {
                if (busyClassrooms.contains(timeSlot.classroom)) {
                    return true;
                } else {
                    busyClassrooms.add(timeSlot.classroom);
                }
            }

        }
        return false;
    }

}
