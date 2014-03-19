package se.kth.hugosa.schedule;

import java.util.HashMap;

public class TimeSlot {
    public HashMap<Classroom, ScheduleElement> elementsMap;

    public TimeSlot() {
    	elementsMap = new HashMap<Classroom, ScheduleElement>();
    }

    public TimeSlot copy() {
        TimeSlot copy = new TimeSlot();
        copy.elementsMap.putAll(elementsMap);
        return copy;
    }
}
