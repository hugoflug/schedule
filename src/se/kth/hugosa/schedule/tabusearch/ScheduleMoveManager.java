package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Move;
import org.coinor.opents.MoveManager;
import org.coinor.opents.Solution;
import se.kth.hugosa.schedule.*;

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
        for (int i = 0; i < 100; i++) {
            Schedule schedule = Util.getRandomElement(schedules);
            {
                int scheduleNo = getRandomIndex(schedules);
                int dayNo = getRandomIndex(schedules.get(scheduleNo).days);
                int timeSlotNo = getRandomInt(4);
                moves.add(new SwapClassroomMove(scheduleNo, dayNo, timeSlotNo,
                        Util.getRandomElement(constraints.getClassrooms())));
            }
            {
                int scheduleNo1 = getRandomIndex(schedules);
                int dayNo1 = getRandomIndex(schedules.get(scheduleNo1).days);
                int timeSlotNo1 = getRandomInt(4);
                int scheduleNo2 = getRandomIndex(schedules);
                int dayNo2 = getRandomIndex(schedules.get(scheduleNo2).days);
                int timeSlotNo2 = getRandomInt(4);
                moves.add(new SwapTimeMove(scheduleNo1, dayNo1, timeSlotNo1,
                        scheduleNo2, dayNo2, timeSlotNo2));
            }
        }
        return moves.toArray(new Move[0]);
    }

    private int getRandomIndex(ArrayList list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }

    private int getRandomInt(int below) {
        Random random = new Random();
        return random.nextInt(below);
    }

    private TimeSlot getRandomValidTimeSlot(Schedule schedule) {
        TimeSlot timeSlot = getRandomTimeSlot(schedule);
        while (timeSlot.getOnlyScheduleElement() == null) {
            timeSlot = getRandomTimeSlot(schedule);
        }
        return timeSlot;
    }

    private TimeSlot getRandomTimeSlot(Schedule schedule) {
        Day day = Util.getRandomElement(schedule.days);
        return Util.getRandomElement(day.timeSlots);
    }
}
