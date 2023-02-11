package game;

public class Game {
    private final Player player1;
    private final Player player2;
    private final boolean enableLogging;

    public Game(final Player player1, final Player player2, final boolean enableLogging) {
        this.player1 = player1;
        this.player2 = player2;
        this.enableLogging = enableLogging;
    }

    public int play(final Board board) {
        int result;
        while (true) {
            result = makeMove(board, this.player1, 1);
            if (result >= 0) {
                break;
            }
            result = makeMove(board, this.player2, 2);
            if (result >= 0) {
                break;
            }
        }
        System.out.println("Final position:");
        System.out.println(board);
        if (result == 0) {
            System.out.println("Game result: draw");
        } else {
            System.out.println("Game result: player " + board.getTurn() + " won");
        }
        return result;
    }

    private int makeMove(final Board board, final Player player, final int no) {
        if (enableLogging) {
            System.out.println(board);
        }

        final Move move = player.makeMove(board, board.getTurn());
        if (enableLogging) {
            System.out.println("Move: " + move);
        }
        final Result result = board.makeMove(move);
        if (result == Result.WIN) {
            return no;
        } else if (result == Result.LOSE) {
            return 3 - no;
        } else if (result == Result.DRAW) {
            return 0;
        } else if (result == Result.UNKNOWN) {
            return -1;
        } else {
            throw new AssertionError("Unknown result type " + result);
        }
    }
}
