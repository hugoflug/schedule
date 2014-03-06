package se.kth.hugosa.schedule;


import java.util.List;

public class Constraints {
    private List<ScheduleElement> elements;
    private List<Classroom> classroomList;
    private List<String> programs;
    private int scheduleWeeks;
    private int classrooms;

    public Constraints(List<ScheduleElement> scheduleElements) {
        this.elements = scheduleElements;
    }

    public List<ScheduleElement> getScheduleElements() {
        return elements;
    }
    
    public List<Classroom> getClassrooms(){
    	return classroomList;
    }
    
    public List<String> getPrograms(){
    	return programs;
    }
    
    public int getNumPrograms(){
    	return programs.size();
    }

    public int getScheduleWeeks() {
        return scheduleWeeks;
    }
    
    public int getNumSlots(){
    	return classrooms * 4 * scheduleWeeks * 5; //5 days per week, 4 slots per classroom and day
    }
    
    public int getNumClassrooms(){
    	return classrooms;
    }
    public int getNumElements(){
    	return elements.size();
    }
}
