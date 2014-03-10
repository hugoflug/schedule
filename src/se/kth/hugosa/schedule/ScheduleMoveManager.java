package se.kth.hugosa.schedule;

import org.coinor.opents.Move;
import org.coinor.opents.MoveManager;
import org.coinor.opents.Solution;

import java.util.ArrayList;
import java.util.Random;

public class ScheduleMoveManager implements MoveManager {
    private Constraints constraints;

    public ScheduleMoveManager(Constraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public Move[] getAllMoves(Solution solution) {
        ArrayList<Schedule> schedules = ((ScheduleSolution)solution).getSchedules();

        ArrayList<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < 25; i++) {
            moves.add(new SwapClassroomMove(getRandomTimeSlot(schedules),
                    Util.getRandomElement(constraints.getClassrooms())));
            moves.add(new SwapTimeMove(getRandomTimeSlot(schedules),
                    getRandomTimeSlot(schedules)));
        }
        return (Move[])moves.toArray();
    }

    private TimeSlot getRandomTimeSlot(ArrayList<Schedule> schedules) {
        Schedule schedule = Util.getRandomElement(schedules);
        Day day = Util.getRandomElement(schedule.days);
        return Util.getRandomElement(day.timeSlots);
    }
}
