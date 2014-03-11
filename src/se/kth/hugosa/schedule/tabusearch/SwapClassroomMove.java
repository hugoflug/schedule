package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Move;
import org.coinor.opents.Solution;
import se.kth.hugosa.schedule.Classroom;
import se.kth.hugosa.schedule.TimeSlot;

import java.util.ArrayList;

public class SwapClassroomMove implements Move {
    private Classroom newClassroom;
    private TimeSlot timeSlot;

    public SwapClassroomMove(TimeSlot timeSlot, Classroom newClassroom) {
        this.newClassroom = newClassroom;
        this.timeSlot = timeSlot;
    }

    @Override
    public void operateOn(Solution solution) {
        timeSlot.classroom = newClassroom;
    }

    public int hashCode() {
        return timeSlot.hashCode() + newClassroom.hashCode()*32000;
    }
}

