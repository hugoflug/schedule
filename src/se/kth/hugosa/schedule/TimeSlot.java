package se.kth.hugosa.schedule;

import java.util.List;

public class TimeSlot {
    public ScheduleElement scheduleElement;
    public Classroom classroom;

    public TimeSlot() {}

    public TimeSlot(Classroom classroom, ScheduleElement scheduleElement) {
        this.classroom = classroom;
        this.scheduleElement = scheduleElement;
    }

    public TimeSlot copy() {
        TimeSlot copy = new TimeSlot();
        copy.scheduleElement = scheduleElement;
        copy.classroom = classroom;
        return copy;
    }

    @Override
    public String toString() {
        return"(" + classroom.toString() + ", " + scheduleElement.toString() + ")";
    }
}
