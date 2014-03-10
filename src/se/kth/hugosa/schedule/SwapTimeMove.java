package se.kth.hugosa.schedule;

import org.coinor.opents.Move;
import org.coinor.opents.Solution;

public class SwapTimeMove implements Move {
    private TimeSlot first, second;

    public SwapTimeMove(TimeSlot a, TimeSlot b) {
        first = a;
        second = b;
    }

    @Override
    public void operateOn(Solution solution) {
        ScheduleElement tempSe = first.scheduleElement;
        Classroom tempCl = first.classroom;
        first.scheduleElement = second.scheduleElement;
        first.classroom = second.classroom;
        second.scheduleElement = tempSe;
        second.classroom = tempCl;
    }

    public int hashCode() {
        return first.hashCode() + second.hashCode()*32000;
    }
}