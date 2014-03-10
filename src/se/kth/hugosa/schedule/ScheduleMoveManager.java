package se.kth.hugosa.schedule;

import org.coinor.opents.Move;
import org.coinor.opents.MoveManager;
import org.coinor.opents.Solution;

public class ScheduleMoveManager implements MoveManager {

    @Override
    public Move[] getAllMoves(Solution solution) {
        return new Move[0];
    }
}
