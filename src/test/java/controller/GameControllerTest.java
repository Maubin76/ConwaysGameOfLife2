package controller;

import model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(3, 3);
    }

    @Test
    void testGridInitialization() {
        Cell[][] grid = controller.getGrid();

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                assertFalse(cell.isAlive());
            }
        }
    }

    @Test
    void testToggleCell() {
        controller.toggleCell(1, 1);
        assertTrue(controller.getGrid()[1][1].isAlive());

        controller.toggleCell(1, 1);
        assertFalse(controller.getGrid()[1][1].isAlive());
    }

    @Test
    void testNextGeneration() {
        controller.toggleCell(0, 1);
        controller.toggleCell(1, 1);
        controller.toggleCell(2, 1);

        controller.nextGeneration();

        assertTrue(controller.getGrid()[1][0].isAlive());
        assertTrue(controller.getGrid()[1][1].isAlive());
        assertTrue(controller.getGrid()[1][2].isAlive());
        assertFalse(controller.getGrid()[0][1].isAlive());
        assertFalse(controller.getGrid()[2][1].isAlive());
    }

    @Test
    void testResetGrid() {
        controller.toggleCell(1, 1);
        controller.resetGrid();

        for (Cell[] row : controller.getGrid()) {
            for (Cell cell : row) {
                assertFalse(cell.isAlive());
            }
        }
    }
}