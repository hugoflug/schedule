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
        for (Schedule schedule : schedules) {
            for (int i = 0; i < 10; i++) {
                moves.add(new SwapClassroomMove(getRandomTimeSlot(schedule),
                        Util.getRandomElement(constraints.getClassrooms())));
                moves.add(new SwapTimeMove(getRandomTimeSlot(schedule),
                        getRandomTimeSlot(schedule)));
            }
        }
        return moves.toArray(new Move[0]);
    }

    private TimeSlot getRandomTimeSlot(Schedule schedule) {
        Day day = Util.getRandomElement(schedule.days);
        return Util.getRandomElement(day.timeSlots);
    }
}
