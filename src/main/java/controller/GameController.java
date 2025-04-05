package controller;

import model.Cell;

public class GameController {
    /*
     * This class manages the game logic and the state of the grid.
     * It handles the rules of the game and updates the grid accordingly.
     * The grid is represented as a 2D array of Cell objects.
     * Each Cell object has a boolean state indicating whether it is alive or dead.
     * The GameController class is responsible for initializing the grid, toggling cell states, 
     * and calculating the next generation of cells based on the rules of Conway's Game of Life.
     */
    private final int rows;
    private final int cols;
    private Cell[][] grid;

    public GameController(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell(false); // All cells start dead
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void toggleCell(int row, int col) {
        if (isValid(row, col)) {
            grid[row][col].toggle();
        }
    }

    public void nextGeneration() {
        Cell[][] nextGen = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(row, col);
                boolean alive = grid[row][col].isAlive();

                if (alive && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    nextGen[row][col] = new Cell(true);
                } else if (!alive && liveNeighbors == 3) {
                    nextGen[row][col] = new Cell(true);
                } else {
                    nextGen[row][col] = new Cell(false);
                }
            }
        }

        grid = nextGen;
    }

    private int countLiveNeighbors(int row, int col) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int r = row + i;
                int c = col + j;

                if (isValid(r, c) && grid[r][c].isAlive()) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
