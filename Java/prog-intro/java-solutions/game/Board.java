package game;

import java.util.Scanner;

public class Board implements Position {
    private final int n;
    private final int m;
    private final int k;
    private final Cell[][] cells;
    private Cell turn;

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }


    public Board(String n, String m, String k) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (!isValidInput(n)) {
                System.out.println("Enter the positive int number of rows");
                n = scanner.nextLine();
                continue;
            }
            if (!isValidInput(m)) {
                System.out.println("Enter the positive int number of columns");
                m = scanner.nextLine();
                continue;
            }
            if (!isValidInput(k)) {
                System.out.println("Enter the positive int number of objects you need to collect to win");
                k = scanner.nextLine();
                continue;
            }
            if (isValidInput(n) && isValidInput(m) && isValidInput(k)) {
                break;
            }
        }
        this.n = Integer.parseInt(n);
        this.m = Integer.parseInt(m);
        this.k = Integer.parseInt(k);
        cells = new Cell[this.n][this.m];
        for (int row = 0; row < this.n; row++) {
            for (int col = 0; col < this.m; col++) {
                cells[row][col] = Cell.E;
            }
        }
        turn = Cell.X;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i = 1; i <= m; i++) {
            sb.append(i);
        }
        sb.append("\n");
        for (int r = 0; r < n; r++) {
            sb.append(r + 1).append(" ");
            for (Cell cell : cells[r]) {
                if (cell == Cell.E) {
                    sb.append('.');
                } else {
                    sb.append(cell);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public Cell getTurn() {
        return turn;
    }

    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getCol()] = move.getValue();

        int diag1 = 0;
        int diag2 = 0;
        int empty = 0;
        for (int row = 0; row < n; row++) {
            int rowCount = 0;
            int colCount = 0;
            for (int col = 0; col < m; col++) {
                for (int z = 0; z < k; z++) {
                    if (col + z < m && get(row, col + z) == turn) {
                        rowCount++;
                    }
                    if (col + z < n && row < m && get(col + z, row) == turn) {
                        colCount++;
                    }
                    if (row + z < n && col + z < m && get(row + z, col + z) == turn) {
                        diag1++;
                    }
                    if (row + z < n && col + z < m && get(row + z, m - 1 - (col + z)) == turn) {
                        diag2++;
                    }
                }
                if (rowCount == k || colCount == k || diag1 == k || diag2 == k) {
                    return Result.WIN;
                }
                rowCount = 0;
                colCount = 0;
                diag1 = 0;
                diag2 = 0;
                if (get(row, col) == Cell.E) {
                    empty++;
                }
            }
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    public void clear() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                cells[row][col] = Cell.E;
            }
        }
        turn = Cell.X;
    }

    @Override
    public Cell get(final int row, final int col) {
        return cells[row][col];
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && get(move.getRow(), move.getCol()) == Cell.E
                && move.getValue() == turn;
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
