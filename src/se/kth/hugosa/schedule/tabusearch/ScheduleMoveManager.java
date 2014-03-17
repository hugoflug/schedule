package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Move;
import org.coinor.opents.MoveManager;
import org.coinor.opents.Solution;
import se.kth.hugosa.schedule.*;

import java.util.ArrayList;

public class ScheduleMoveManager implements MoveManager {
    private Constraints constraints;

    public ScheduleMoveManager(Constraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public Move[] getAllMoves(Solution solution) {
        ArrayList<Schedule> schedules = ((ScheduleSolution)solution).getSchedules();

        ArrayList<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < 100; i++) {
            Schedule schedule = Util.getRandomElement(schedules);
            moves.add(new SwapClassroomMove(getRandomValidTimeSlot(schedule),
                    Util.getRandomElement(constraints.getClassrooms())));
            moves.add(new SwapTimeMove(getRandomValidTimeSlot(schedule),
                    getRandomTimeSlot(schedule)));
        }
        return moves.toArray(new Move[0]);
    }

    private TimeSlot getRandomValidTimeSlot(Schedule schedule) {
        TimeSlot timeSlot = getRandomTimeSlot(schedule);
        while (timeSlot.scheduleElement == null) {
            timeSlot = getRandomTimeSlot(schedule);
        }
        return timeSlot;
    }

    private TimeSlot getRandomTimeSlot(Schedule schedule) {
        Day day = Util.getRandomElement(schedule.days);
        return Util.getRandomElement(day.timeSlots);
    }
}
