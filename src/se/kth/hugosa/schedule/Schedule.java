package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private String program;
    //should perhaps be HashMap<Date, Day>
    public List<Day> days;
    
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
    	for(Schedule schedule : schedules){
    		System.out.println("Program: " + schedule.getProgram());
    		System.out.println("-----------------------");
    		for(int days = 0; days < schedule.days.size(); days++){
    			System.out.println("Day " + days);
    			Day day = schedule.days.get(days);
    			System.out.println("-----------------------");
    			for(int slots = 0; slots < 4; slots++){
    				String classroom = day.timeSlots.get(slots).classroom.name;
    				ScheduleElement element = day.timeSlots.get(slots).scheduleElement;
    				System.out.println("Slot " + slots + ": " + element.course + " with " + element.teacher + " in " + classroom + ".");
    			}
    			System.out.println();
    		}
    	}
    }
}
