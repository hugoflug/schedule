package se.kth.hugosa.schedule;

import java.util.ArrayList;

public class Schedule {
	private String program;
    //should perhaps be HashMap<Date, Day>
    public ArrayList<Day> days;
    
    public Schedule(String program, int weeks){
    	this.program = program;
    	this.days = new ArrayList<Day>();
    	for(int i = 0; i < weeks*5; i++){
    		days.add(null);
    	}
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
    				boolean slotEmpty = true;
    				System.out.print("Slot " + slots + ": ");
    				if (day != null){
    					TimeSlot slot = day.timeSlots.get(slots);
    					if (slot != null){
    						String classroom = slot.classroom.name;
            				ScheduleElement element = slot.scheduleElement;
            				System.out.println(element.getCourse() + " with " + element.getTeacher() + " in " + classroom + ".");
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
