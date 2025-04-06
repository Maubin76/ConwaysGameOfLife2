package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents the graphical user interface for Conway's Game of Life.
 * Handles user interactions and visualizes the game grid.
 */
public class GameOfLifeUI extends Application {

    private static final int CELL_SIZE = 20;
    private static final int ROWS = 30;
    private static final int COLS = 30;

    private GameController controller;
    private Rectangle[][] cellRects;
    private Timeline timeline;
    private double gameSpeed = 1.0; // Default speed multiplier

    @Override
    public void start(Stage primaryStage) {
        controller = new GameController(ROWS, COLS);
        GridPane gridPane = new GridPane();
        cellRects = new Rectangle[ROWS][COLS];

        // Initialize the grid with clickable rectangles
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.LIGHTGRAY);

                final int r = row;
                final int c = col;

                rect.setOnMouseClicked(e -> {
                    controller.toggleCell(r, c);
                    updateGrid();
                });

                gridPane.add(rect, col, row);
                cellRects[row][col] = rect;
            }
        }

        // Create a Start button
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startGameLoop());

        // Create a Restart button
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> restartGame());

        // Create a speed adjustment slider
        Slider speedSlider = new Slider(0.5, 5.0, 1.0); // Min: 0.5x, Max: 5x, Default: 1x
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(0.5);
        speedSlider.setBlockIncrement(0.1);
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            gameSpeed = newVal.doubleValue();
            if (timeline != null) {
                timeline.setRate(gameSpeed); // Adjust the timeline speed
            }
        });

        // Layout for buttons and slider
        HBox controlsContainer = new HBox(10, startButton, restartButton, speedSlider); // Add spacing between controls
        controlsContainer.setAlignment(Pos.CENTER);

        // Layout with the grid and controls
        BorderPane root = new BorderPane();
        root.setCenter(gridPane);
        root.setBottom(controlsContainer);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.show();
    }

    /**
     * Starts the game loop, which updates the grid at regular intervals.
     */
    private void startGameLoop() {
        if (timeline == null) {
            timeline = new Timeline(new KeyFrame(Duration.millis(300), e -> {
                controller.nextGeneration();
                updateGrid();
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
        }
        timeline.setRate(gameSpeed); // Set the initial speed
        timeline.play();
    }

    /**
     * Restarts the game by resetting the grid and stopping the simulation.
     */
    private void restartGame() {
        if (timeline != null) {
            timeline.stop();
        }
        controller.resetGrid(); // Reset the grid in the controller
        updateGrid(); // Update the UI to reflect the reset grid
    }

    /**
     * Updates the visual representation of the grid based on the current state.
     */
    private void updateGrid() {
        var grid = controller.getGrid();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cellRects[row][col].setFill(grid[row][col].isAlive() ? Color.BLACK : Color.WHITE);
            }
        }
    }
}