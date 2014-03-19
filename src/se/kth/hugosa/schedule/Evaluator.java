package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Evaluator {

    public class Result {
        private int freePeriods;
        private int onSameDay;
        private double total;

        public Result(double total, int freePeriods, int onSameDay) {
            this.freePeriods = freePeriods;
            this.onSameDay = onSameDay;
            this.total = total;
        }

        public int getFreePeriods() {
            return freePeriods;
        }

        public int getOnSameDay() {
            return onSameDay;
        }

        public double getTotal() {
            return total;
        }

        @Override
        public String toString() {
            return "free periods: " + freePeriods + " lessons on same day: " +
                    onSameDay + " total penalty: " + total;
        }
    }

	public Evaluator(){
		
	}

    //returns the amount of times that any lesson is repeated in a day
    private int onSameDay(Day day) {
        int duplicates = 0;
        Set<String> courses = new HashSet<String>();
        for (TimeSlot timeSlot : day.timeSlots) {
        	for(Map.Entry<Classroom, ScheduleElement> entry : timeSlot.elementsMap.entrySet()){
                String course = entry.getValue().getCourse();
                if (courses.contains(course)) {
                    duplicates++;
                } else {
                    courses.add(entry.getValue().getCourse());
                }
        	}
        }
        return duplicates;
    }

    //returns the amount of free periods in a day
    private int freePeriods(Day day) {
        int freePeriods = 0;
        boolean hadLessonToday = false;
        int currentFreePeriod = 0;
        for (TimeSlot timeSlot : day.timeSlots) {
        	if(timeSlot.elementsMap.size()>0){
        		hadLessonToday = true;
                freePeriods += currentFreePeriod;
                currentFreePeriod = 0;
        	} else {
                if (hadLessonToday) {
                    currentFreePeriod++;
                }
            }
        	
        }
        return freePeriods;
    }

    //return all the Days on dayNo in the specified schedules
    private ArrayList<Day> getDays(List<Schedule> schedules, int dayNo) {
        ArrayList<Day> days = new ArrayList<Day>();
        for (Schedule schedule : schedules) {
            Day day = schedule.days.get(dayNo);
            if (day != null) {
                days.add(day);
            }
        }
        return days;
    }

    //return all the timeSlots on timeSlotNo in the specified list of Days
    private ArrayList<TimeSlot> getTimeSlots(List<Day> days, int timeSlotNo) {
        ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (Day day : days) {
            TimeSlot slot = day.timeSlots.get(timeSlotNo);

            if (slot != null){
                timeSlots.add(slot);
            }

        }

        return timeSlots;
    }

    public Result evaluateWithInfo(List<Schedule> schedules, Constraints constraints) {
        double value = 0;

        for (int i = 0; i < schedules.get(0).days.size(); i++) {
            List<Day> days = getDays(schedules, i);

            for (int j = 0; j < 4; j++) {
                List<TimeSlot> timeSlots = getTimeSlots(days, j);

                if (collides(timeSlots)) {
                    value += 20;
                }

                for (TimeSlot timeSlot : timeSlots) {
                    if (overCapacity(timeSlot)) {
                        value += 20;
                    }
                }
            }

        }

        int freePeriods = 0;
        int onSameDay = 0;
        for (Schedule schedule : schedules) {
            for (Day day : schedule.days) {
                freePeriods += freePeriods(day);
                onSameDay += onSameDay(day);
            }
        }

        value += freePeriods;
        value += onSameDay;

        return new Result(value, freePeriods, onSameDay);
    }
	
    public double evaluateSchedule(List<Schedule> schedules, Constraints constraints) {
        double value = 0;

        for (int i = 0; i < schedules.get(0).days.size(); i++) {
           List<Day> days = getDays(schedules, i);

           for (int j = 0; j < 4; j++) {
               List<TimeSlot> timeSlots = getTimeSlots(days, j);

               if (collides(timeSlots)) {
                   value += 10;
               }

               for (TimeSlot timeSlot : timeSlots) {
                  if (overCapacity(timeSlot)) {
                      value += 10;
                  }
               }
           }

        }


        for (Schedule schedule : schedules) {
            for (Day day : schedule.days) {
                value += freePeriods(day);
                value += onSameDay(day);
            }
        }

        return value;
    }

    //checks whether a TimeSlot is over its' allotted capacity
    private boolean overCapacity(TimeSlot timeSlot) {
    	for(Map.Entry<Classroom, ScheduleElement> entry : timeSlot.elementsMap.entrySet()){
    		if (entry.getValue().getNumStudents() > entry.getKey().capacity) {
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
        	if(timeSlot.elementsMap.size()>1){
        		return true;
        	}
        	for(Map.Entry<Classroom, ScheduleElement> entry : timeSlot.elementsMap.entrySet()){
                if (busyTeachers.contains(entry.getValue().getTeacher())) {
                    return true;
                } else {
                    busyTeachers.add(entry.getValue().getTeacher());
                }

                if (busyClassrooms.contains(entry.getKey())) {
                    return true;
                } else {
                    busyClassrooms.add(entry.getKey());
                }
        	}
            

        }
        return false;
    }

}
