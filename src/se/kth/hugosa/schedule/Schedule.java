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
}
