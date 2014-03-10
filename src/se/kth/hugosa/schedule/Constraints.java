package se.kth.hugosa.schedule;


import java.util.ArrayList;
import java.util.List;

public class Constraints {
    private List<ScheduleElement> elements;
    private ArrayList<Classroom> classroomList;
    private List<String> programs;
    private int scheduleWeeks;

    public Constraints(List<ScheduleElement> scheduleElements, ArrayList<Classroom> classroomList, List<String> programList, int weeks) {
        this.elements = scheduleElements;
        this.classroomList = classroomList;
        this.programs = programList;
        this.scheduleWeeks = weeks;
    }

    public List<ScheduleElement> getScheduleElements() {
        return elements;
    }
    
    public ArrayList<Classroom> getClassrooms(){
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
    	return getNumClassrooms() * 4 * scheduleWeeks * 5; //5 days per week, 4 slots per classroom and day
    }
    
    public int getNumClassrooms(){
    	return classroomList.size();
    }
    public int getNumElements(){
    	return elements.size();
    }
}
