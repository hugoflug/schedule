package se.kth.hugosa.schedule;

import java.util.List;

public class TimeSlot {
    public ScheduleElement scheduleElement;
    public Classroom classroom;

    public TimeSlot(Classroom classroom, ScheduleElement scheduleElement) {
        this.classroom = classroom;
        this.scheduleElement = scheduleElement;
    }
}
