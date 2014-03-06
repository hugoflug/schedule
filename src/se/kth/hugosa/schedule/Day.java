package se.kth.hugosa.schedule;

import java.util.ArrayList;

public class Day {

    //should only hold four schoolclasses
    public ArrayList<TimeSlot> timeSlots;
    
    public Day(){
    	timeSlots = new ArrayList<TimeSlot>();
    	for(int i = 0; i<3; i++){
    		timeSlots.add(null);
    	}
    }
}
