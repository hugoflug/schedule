package se.kth.hugosa.schedule.tabusearch;

import org.coinor.opents.Move;
import org.coinor.opents.MoveManager;
import org.coinor.opents.Solution;
import se.kth.hugosa.schedule.*;

import java.util.ArrayList;
import java.util.Random;

public class ScheduleMoveManager implements MoveManager {
    private Constraints constraints;
    private int movesN;

    private class TimeSlotIndexes {
        private int dayIndex;
        private int timeSlotIndex;
        private int scheduleIndex;

        public TimeSlotIndexes(int dayIndex, int timeSlotIndex, int scheduleIndex) {
            this.dayIndex = dayIndex;
            this.timeSlotIndex = timeSlotIndex;
            this.scheduleIndex = scheduleIndex;
        }

        public int getScheduleIndex() {
            return scheduleIndex;
        }

        public int getDayIndex() {
            return dayIndex;
        }

        public int getTimeSlotIndex() {
            return timeSlotIndex;
        }

        @Override
        public String toString() {
            return "TimeSlotIndexes{" +
                    "dayIndex=" + dayIndex +
                    ", timeSlotIndex=" + timeSlotIndex +
                    ", scheduleIndex=" + scheduleIndex +
                    '}';
        }
    }

    public ScheduleMoveManager(Constraints constraints, int movesN) {
        this.constraints = constraints;
        this.movesN = movesN;
    }

    @Override
    public Move[] getAllMoves(Solution solution) {
        ArrayList<Schedule> schedules = ((ScheduleSolution)solution).getSchedules();

        ArrayList<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < movesN; i++) {
            {
                TimeSlotIndexes indexes = getRandomValidTimeSlotIndexes(schedules);
                int scheduleNo = indexes.getScheduleIndex();
                int dayNo = indexes.getDayIndex();
                int timeSlotNo = indexes.getTimeSlotIndex();
                moves.add(new SwapClassroomMove(scheduleNo, dayNo, timeSlotNo,
                        Util.getRandomElement(constraints.getClassrooms())));
            }
            {
                TimeSlotIndexes indexes1 = getRandomValidTimeSlotIndexes(schedules);
                TimeSlotIndexes indexes2 = getRandomTimeSlotIndexes(schedules);
                int scheduleNo1 = indexes1.getScheduleIndex();
                int dayNo1 = indexes1.getDayIndex();
                int timeSlotNo1 = indexes1.getTimeSlotIndex();
                int scheduleNo2 = indexes2.getScheduleIndex();
                int dayNo2 = indexes2.getDayIndex();
                int timeSlotNo2 = indexes2.getTimeSlotIndex();
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

    private TimeSlotIndexes getRandomValidTimeSlotIndexes(ArrayList<Schedule> schedules) {
        TimeSlotIndexes indexes = getRandomTimeSlotIndexes(schedules);
        while (schedules.get(indexes.getScheduleIndex())
                .days.get(indexes.getDayIndex())
                .timeSlots.get(indexes.getTimeSlotIndex())
                .getOnlyScheduleElement() == null) {
            indexes = getRandomTimeSlotIndexes(schedules);
        }
        return indexes;
    }

    private TimeSlotIndexes getRandomTimeSlotIndexes(ArrayList<Schedule> schedules) {
        int scheduleNo = getRandomIndex(schedules);
        int dayNo = getRandomIndex(schedules.get(scheduleNo).days);
        int timeSlotNo = getRandomInt(4);
        return new TimeSlotIndexes(dayNo, timeSlotNo, scheduleNo);
    }

    private TimeSlot getRandomTimeSlot(Schedule schedule) {
        Day day = Util.getRandomElement(schedule.days);
        return Util.getRandomElement(day.timeSlots);
    }
}
