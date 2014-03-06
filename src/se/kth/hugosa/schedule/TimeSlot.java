package se.kth.hugosa.schedule;

import java.util.List;

public class TimeSlot {
    public ScheduleElement scheduleElement;
    public Classroom classroom;
    
    public TimeSlot(ScheduleElement element, Classroom classroom){
    	scheduleElement = element;
    	this.classroom = classroom;
    }
}
