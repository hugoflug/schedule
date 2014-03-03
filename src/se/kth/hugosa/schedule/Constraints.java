package se.kth.hugosa.schedule;


import java.util.List;

public class Constraints {
    private List<ScheduleElement> elements;
    private int scheduleWeeks;

    public Constraints(List<ScheduleElement> scheduleElements) {
        this.elements = scheduleElements;
    }

    public List<ScheduleElement> getScheduleElements() {
        return elements;
    }

    public int getScheduleWeeks() {
        return scheduleWeeks;
    }
}
