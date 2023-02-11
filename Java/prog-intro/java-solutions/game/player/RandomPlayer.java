package game.player;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(Position position, Cell cell) {
        Move move;
        do {
            move = new Move(random.nextInt(position.getN()), random.nextInt(position.getM()), cell);
        } while (!position.isValid(move));
        return move;
    }
}
