package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Move;
import org.coinor.opents.Solution;
import se.kth.hugosa.schedule.Classroom;
import se.kth.hugosa.schedule.TimeSlot;

public class SwapClassroomMove implements Move {
    private int day;
    private int timeSlotNo;
    private Classroom newClassroom;
    private int schedule;

    public SwapClassroomMove(int schedule, int day, int timeSlot, Classroom newClassroom) {
        this.schedule = schedule;
        this.day = day;
        this.timeSlotNo = timeSlot;
        this.newClassroom = newClassroom;
    }

    @Override
    public void operateOn(Solution solution) {
        ScheduleSolution sol = (ScheduleSolution)solution;
        TimeSlot timeSlot = sol.getSchedules().get(schedule).days.get(day).timeSlots.get(this.timeSlotNo);
        timeSlot.classroom = newClassroom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwapClassroomMove that = (SwapClassroomMove) o;

        if (day != that.day) return false;
        if (schedule != that.schedule) return false;
        if (timeSlotNo != that.timeSlotNo) return false;
        if (newClassroom != null ? !newClassroom.equals(that.newClassroom) : that.newClassroom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + timeSlotNo;
        result = 31 * result + (newClassroom != null ? newClassroom.hashCode() : 0);
        result = 31 * result + schedule;
        return result;
    }
}

