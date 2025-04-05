package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        // Center the button using an HBox
        HBox buttonContainer = new HBox(startButton);
        buttonContainer.setAlignment(Pos.CENTER);

        // Layout with the grid and button
        BorderPane root = new BorderPane();
        root.setCenter(gridPane);
        root.setBottom(buttonContainer);

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
        timeline.play();
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