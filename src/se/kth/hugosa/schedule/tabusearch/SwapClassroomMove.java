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
        if (timeSlot != null) {
            timeSlot.classroom = newClassroom;
        }
    }

    public int hashCode() {
        int timeSlotHash = timeSlot == null ? 0 : timeSlot.hashCode();
        int classroomHash = newClassroom == null ? 0 : newClassroom.hashCode();
        return timeSlotHash + classroomHash*32000;
    }
}

