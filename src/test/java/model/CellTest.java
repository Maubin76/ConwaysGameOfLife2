package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void testCellInitialization() {
        Cell aliveCell = new Cell(true);
        Cell deadCell = new Cell(false);

        assertTrue(aliveCell.isAlive());
        assertFalse(deadCell.isAlive());
    }

    @Test
    void testSetAlive() {
        Cell cell = new Cell(false);
        cell.setAlive(true);

        assertTrue(cell.isAlive());
    }

    @Test
    void testToggle() {
        Cell cell = new Cell(false);
        cell.toggle();

        assertTrue(cell.isAlive());

        cell.toggle();
        assertFalse(cell.isAlive());
    }
}