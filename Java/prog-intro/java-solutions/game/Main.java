package game;

import game.player.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board(scanner.next(), scanner.next(), scanner.next());
        Player player1 = new HumanPlayer();
        Player player2 = new RandomPlayer();
        System.out.println("Enter the number of wins");
        String wins = scanner.nextLine();
        Match match = new Match(player1, player2, wins, true);
        System.out.println(match.playMatch(board));
    }
}
