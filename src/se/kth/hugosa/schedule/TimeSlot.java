package se.kth.hugosa.schedule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class TimeSlot {
    public HashMap<Classroom, ScheduleElement> elementsMap;

    public TimeSlot() {
    	elementsMap = new HashMap<Classroom, ScheduleElement>();
    }

    public TimeSlot(Classroom classroom, ScheduleElement scheduleElement) {
        this();
        elementsMap.put(classroom, scheduleElement);
    }

    public Classroom getOnlyClassroom() {
        Set<Classroom> set = elementsMap.keySet();
        if (set.isEmpty()) {
            return null;
        } else {
            return (Classroom)elementsMap.keySet().toArray()[0];
        }
    }

    public void setOnlyClassroom(Classroom classroom) {
        ScheduleElement scheduleElement = getOnlyScheduleElement();
        elementsMap.clear();
        elementsMap.put(classroom, scheduleElement);
    }

    public ScheduleElement getOnlyScheduleElement() {
        Collection<ScheduleElement> collection = elementsMap.values();
        if (collection.isEmpty()) {
            return null;
        } else {
            return (ScheduleElement)elementsMap.values().toArray()[0];
        }
    }

    public void setOnlyScheduleElement(ScheduleElement scheduleElement) {
        Classroom classroom = getOnlyClassroom();
        elementsMap.put(classroom, scheduleElement);
    }

    public TimeSlot copy() {
        TimeSlot copy = new TimeSlot();
        copy.elementsMap.putAll(elementsMap);
        return copy;
    }
}
