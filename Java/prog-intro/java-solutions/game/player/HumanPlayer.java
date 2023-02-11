package game.player;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(final Scanner in) {
        this.in = in;
    }

    public HumanPlayer() {
        this(new Scanner(System.in));
    }

    @Override
    public Move makeMove(Position position, Cell cell) {
        while (true) {
            System.out.println("Current position: ");
            System.out.println(position.toString());
            System.out.println("Enter " + cell + "'s move");

            System.out.println("Row: ");
            String rowcheck = in.nextLine();
            if (!isValidInput(rowcheck)) {
                System.out.println("Please, enter an int number");
                continue;
            }

            System.out.println("Col: ");
            String colcheck = in.nextLine();
            if (!isValidInput(colcheck)) {
                System.out.println("Please, enter an int number");
                continue;
            }

            int row = Integer.parseInt(rowcheck) - 1;
            int col = Integer.parseInt(colcheck) - 1;
            final Move move = new Move(row, col, cell);
            if (position.isValid(move)) {
                return move;
            }
            System.out.println("Invalid move: row " + (row + 1) + ", column " + (col + 1) + " is not empty or is out of bounds");
        }
    }

    private boolean isValidInput(String line) {
        if (line.isEmpty()) {
            return false;
        }
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return Integer.parseInt(line) > 0;
    }
}
