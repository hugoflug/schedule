package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Move;
import org.coinor.opents.Solution;
import se.kth.hugosa.schedule.Classroom;
import se.kth.hugosa.schedule.ScheduleElement;
import se.kth.hugosa.schedule.TimeSlot;

public class SwapTimeMove implements Move {

    private int firstSchedule;
    private final int firstDay;
    private final int firstTimeSlot;
    private final int secondSchedule;
    private final int secondDay;
    private final int secondTimeSlot;

    public SwapTimeMove(int firstSchedule, int firstDay, int firstTimeSlot, int secondSchedule, int secondDay,
                        int secondTimeSlot) {
        this.firstSchedule = firstSchedule;
        this.firstDay = firstDay;
        this.firstTimeSlot = firstTimeSlot;
        this.secondSchedule = secondSchedule;
        this.secondDay = secondDay;
        this.secondTimeSlot = secondTimeSlot;
    }

    @Override
    public void operateOn(Solution solution) {
        ScheduleSolution sol = (ScheduleSolution)solution;
        TimeSlot first = sol.getSchedules().get(firstSchedule).days.get(firstDay).timeSlots.get(firstTimeSlot);
        TimeSlot second = sol.getSchedules().get(secondSchedule).days.get(secondDay).timeSlots.get(secondTimeSlot);
        ScheduleElement tempSe = first.scheduleElement;
        Classroom tempCl = first.classroom;
        first.scheduleElement = second.scheduleElement;
        first.classroom = second.classroom;
        second.scheduleElement = tempSe;
        second.classroom = tempCl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwapTimeMove that = (SwapTimeMove) o;

        if (firstDay != that.firstDay) return false;
        if (firstSchedule != that.firstSchedule) return false;
        if (firstTimeSlot != that.firstTimeSlot) return false;
        if (secondDay != that.secondDay) return false;
        if (secondSchedule != that.secondSchedule) return false;
        if (secondTimeSlot != that.secondTimeSlot) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstSchedule;
        result = 31 * result + firstDay;
        result = 31 * result + firstTimeSlot;
        result = 31 * result + secondSchedule;
        result = 31 * result + secondDay;
        result = 31 * result + secondTimeSlot;
        return result;
    }
}