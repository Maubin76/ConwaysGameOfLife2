package model;

/**
 * Represents a single cell in the Game of Life grid.
 * Each cell can either be alive or dead.
 */
public class Cell {
    private boolean alive;

    /**
     * Constructs a Cell with the specified state.
     * @param alive True if the cell is alive, false otherwise.
     */
    public Cell(boolean alive) {
        this.alive = alive;
    }

    /**
     * Checks if the cell is alive.
     * @return True if the cell is alive, false otherwise.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the state of the cell.
     * @param alive True to make the cell alive, false to make it dead.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Toggles the state of the cell between alive and dead.
     */
    public void toggle() {
        this.alive = !this.alive;
    }
}