package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.show();

        startGameLoop();
    }

    private void startGameLoop() {
        timeline = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            controller.nextGeneration();
            updateGrid();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateGrid() {
        var grid = controller.getGrid();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cellRects[row][col].setFill(grid[row][col].isAlive() ? Color.BLACK : Color.WHITE);
            }
        }
    }
}
