package se.kth.hugosa.schedule;


import java.util.List;

public class Constraints {
    private List<ScheduleElement> elements;
    private int scheduleWeeks;
    private int classrooms;

    public Constraints(List<ScheduleElement> scheduleElements) {
        this.elements = scheduleElements;
    }

    public List<ScheduleElement> getScheduleElements() {
        return elements;
    }

    public int getScheduleWeeks() {
        return scheduleWeeks;
    }
    
    public int getNumSlots(){
    	return classrooms * 4 * scheduleWeeks * 5; //5 days per week, 4 slots per classroom and day
    }
    
    public int getNumElements(){
    	return elements.size();
    }
}
