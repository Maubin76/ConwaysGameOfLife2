package controller;

import model.Cell;

/**
 * Manages the game logic and state of the grid for Conway's Game of Life.
 * Handles grid initialization, cell state toggling, and generation updates.
 */
public class GameController {
    private final int rows;
    private final int cols;
    private Cell[][] grid;

    /**
     * Constructs a GameController with the specified grid dimensions.
     * @param rows Number of rows in the grid.
     * @param cols Number of columns in the grid.
     */
    public GameController(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        initializeGrid();
    }

    /**
     * Initializes the grid with all cells set to dead.
     */
    private void initializeGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell(false); // All cells start dead
            }
        }
    }

    /**
     * Retrieves the current grid.
     * @return A 2D array of Cell objects representing the grid.
     */
    public Cell[][] getGrid() {
        return grid;
    }

    /**
     * Toggles the state of a cell at the specified position.
     * @param row Row index of the cell.
     * @param col Column index of the cell.
     */
    public void toggleCell(int row, int col) {
        if (isValid(row, col)) {
            grid[row][col].toggle();
        }
    }

    /**
     * Advances the grid to the next generation based on the rules of the game.
     */
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

    /**
     * Counts the number of live neighbors for a cell at the specified position.
     * @param row Row index of the cell.
     * @param col Column index of the cell.
     * @return The number of live neighbors.
     */
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

    /**
     * Checks if the specified position is within the bounds of the grid.
     * @param row Row index to check.
     * @param col Column index to check.
     * @return True if the position is valid, false otherwise.
     */
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Retrieves the number of rows in the grid.
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Retrieves the number of columns in the grid.
     * @return The number of columns.
     */
    public int getCols() {
        return cols;
    }
}