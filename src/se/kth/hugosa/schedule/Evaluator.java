package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Evaluator {
	
	public Evaluator(){
		
	}

    public int freePeriods(List<Schedule> schedules) {
        int freePeriods = 0;
        for (Schedule schedule : schedules) {
            for (Day day : schedule.days) {
                boolean hadLessonToday = false;
                int currentFreePeriod = 0;
                for (TimeSlot timeSlot : day.timeSlots) {
                    if (timeSlot.scheduleElement != null) {
                        hadLessonToday = true;
                        freePeriods += currentFreePeriod;
                        currentFreePeriod = 0;
                    } else {
                        if (hadLessonToday) {
                            currentFreePeriod++;
                        }
                    }
                }
            }
        }
        return freePeriods;
    }

    public ArrayList<Day> getDays(List<Schedule> schedules, int dayNo) {
        ArrayList<Day> days = new ArrayList<Day>();
        for (Schedule schedule : schedules) {
            Day day = schedule.days.get(dayNo);
            if (day != null) {
                days.add(day);
            }
        }
        return days;
    }

    public ArrayList<TimeSlot> getTimeSlots(List<Day> days, int timeSlotNo) {
        ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (Day day : days) {
            TimeSlot slot = day.timeSlots.get(timeSlotNo);

            if (slot != null){
                timeSlots.add(slot);
            }

        }

        return timeSlots;
    }
	
    public double evaluateSchedule(List<Schedule> schedules, Constraints constraints) {
        double value = 0;

        for (int i = 0; i < schedules.get(0).days.size(); i++) {
           List<Day> days = getDays(schedules, i);

           for (int j = 0; j < 4; j++) {
               List<TimeSlot> timeSlots = getTimeSlots(days, j);

               if (collides(timeSlots)) {
                   value += 1;
               }

               for (TimeSlot timeSlot : timeSlots) {
                  if (overCapacity(timeSlot)) {
                      value += 1;
                  }
               }
           }

        }

        value += freePeriods(schedules);

        return value;
    }

    private boolean overCapacity(TimeSlot timeSlot) {
        if (timeSlot.scheduleElement != null && timeSlot.classroom != null) {
            if (timeSlot.scheduleElement.getNumStudents() > timeSlot.classroom.capacity) {
                return true;
            }
        }
        return false;
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
