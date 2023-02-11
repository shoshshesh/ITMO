package game;

import java.util.Scanner;

public class Match {
    private Player player1;
    private Player player2;
    private final int winsToWin;
    private final boolean enableLogging;

    public Match(Player player1, Player player2, String winsToWin, boolean enableLogging) {
        Scanner scanner = new Scanner(System.in);
        this.player1 = player1;
        this.player2 = player2;
        while (true) {
            if (!isValidInput(winsToWin)) {
                System.out.println("Please, enter a positive int number of wins");
                winsToWin = scanner.nextLine();
            } else {
                this.winsToWin = Integer.parseInt(winsToWin);
                break;
            }
        }
//        while (true) {
//            if (isValidInput(winsToWin)) {
//                this.winsToWin = Integer.parseInt(winsToWin);
//                break;
//            } else {
//                System.out.println("Please, enter a positive int number of wins");
//                winsToWin = scanner.nextLine();
//            }
//        }
        this.enableLogging = enableLogging;
    }

    public String playMatch(Board board) {
        int result;
        int wins1 = 0;
        int wins2 = 0;
        int games = 1;
        while (true) {
            System.out.println("Game №" + games);
            final Game game1 = new Game(player1, player2, enableLogging);
            result = game1.play(board);
            switch (result) {
                case 1 -> wins1++;
                case 2 -> wins2++;
            }
            if (wins1 == winsToWin) {
                System.out.println("Match result: Player 1 - " + wins1 + " win(s)");
                System.out.println("              Player 2 - " + wins2 + " win(s)");
                System.out.println("              Player 1 won");
                break;
            }
            if (wins2 == winsToWin) {
                System.out.println("Match result: Player 1 - " + wins1 + " win(s)");
                System.out.println("              Player 2 - " + wins2 + " win(s)");
                System.out.println("              Player 2 won");
                break;
            }
            games++;
            Player tmp = player1;
            player1 = player2;
            player2 = tmp;
            int temp = wins1;
            wins1 = wins2;
            wins2 = temp;
            board.clear();
        }
        return "Match is over";
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
