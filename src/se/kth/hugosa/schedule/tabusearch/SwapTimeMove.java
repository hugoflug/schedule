package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Move;
import org.coinor.opents.Solution;
import se.kth.hugosa.schedule.Classroom;
import se.kth.hugosa.schedule.ScheduleElement;
import se.kth.hugosa.schedule.TimeSlot;

public class SwapTimeMove implements Move {
    private TimeSlot first, second;

    public SwapTimeMove(TimeSlot a, TimeSlot b) {
        first = a;
        second = b;
    }

    @Override
    public void operateOn(Solution solution) {
        if (first != null && second != null) {
            ScheduleElement tempSe = first.scheduleElement;
            Classroom tempCl = first.classroom;
            first.scheduleElement = second.scheduleElement;
            first.classroom = second.classroom;
            second.scheduleElement = tempSe;
            second.classroom = tempCl;
        }
    }

    public int hashCode() {
        return first.hashCode() + second.hashCode()*32000;
    }
}