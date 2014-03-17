package se.kth.hugosa.schedule;

import java.util.ArrayList;

public class Schedule {
	private String program;
    //should perhaps be HashMap<Date, Day>
    public ArrayList<Day> days;

    public Schedule() {}

    public Schedule(String program, int weeks) {
    	this.program = program;
    	this.days = new ArrayList<Day>();
    	for (int i = 0; i < weeks*5; i++) {
    		days.add(new Day());
    	}
    }

    public Schedule copy() {
        Schedule copy = new Schedule();
        copy.program = program;
        copy.days = new ArrayList<Day>();
        for (int i = 0; i < days.size(); i++) {
            Day day = new Day();
            for (int j = 0; j < day.timeSlots.size(); j++) {
                day.timeSlots.set(j, days.get(i).timeSlots.get(j).copy());
            }
            copy.days.add(day);
        }
        return copy;
    }
    
    public String getProgram(){
    	return program;
    }
    
    public static void printSchedule(ArrayList<Schedule> schedules){
    	for (Schedule schedule : schedules){
    		System.out.println("Program: " + schedule.getProgram());
    		System.out.println("-----------------------");
    		for (int days = 0; days < schedule.days.size(); days++){
    			System.out.println("Day " + days);
    			Day day = schedule.days.get(days);
    			System.out.println("-----------------------");
    			for (int slots = 0; slots < 4; slots++){
    				boolean slotEmpty = false;
    				System.out.print("Slot " + slots + ": ");
    				if (day != null){
    					TimeSlot slot = day.timeSlots.get(slots);
    					if (slot != null) {
                            String classroomName = "";
    						Classroom classroom = slot.classroom;
                            if (classroom == null) {
                                classroomName = "unassigned";
                            } else {
                                classroomName = classroom.name;
                            }
            				ScheduleElement element = slot.scheduleElement;
                            if (element == null) {
                                slotEmpty = true;
                            } else {
            				    System.out.println(element.getCourse() + " with " + element.getTeacher() +" in " + classroomName + ".");
                            }
    					}
    					else{
    						slotEmpty = true;
    					}
        			}
    				else{
    					slotEmpty = true;
    				}
    				
    				if(slotEmpty){
    					System.out.println("empty");
    				}
    			}
    			System.out.println();
    		}
    	}
    }
}
