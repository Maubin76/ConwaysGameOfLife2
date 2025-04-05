# Game of Life in Java

This project implements Conway's Game of Life using Java with a graphical user interface. The Game of Life is a cellular automaton devised by mathematician John Conway. It consists of a grid of cells that can be either alive or dead, and the state of each cell changes based on a set of rules. This project can been seen as an improved version of the first game I developed in C (available on my github at https://github.com/Maubin76/ConwaysGameOfLife).

## Project Structure

```
ConwaysGameOfLife2
├── build.gradle
├── src
    ├── main
        ├── java
            ├── Main.java
            ├── controller/
                └── GameController.java
            ├── model/
                └── Cell.java
            └── view/
                └── GameOfLifeUI.java
```

## Setup Instructions

1. **Clone the Repository**: 
   ```
   git clone https://github.com/Maubin76/ConwaysGameOfLife2
   cd game-of-life
   ```

2. **Build the Project**: 
   Use Gradle to build the project. Make sure you have Gradle installed.
   ```
   ./gradle build
   ```

3. **Run the Application**: 
   After building, you can run the application using:
   ```
   ./gradle run
   ```

## Overview of the Game

- **Cells**: Each cell can be in one of two states: alive or dead. The state of the cells is updated based on the following rules:
  - Any live cell with fewer than two live neighbors dies (underpopulation).
  - Any live cell with two or three live neighbors lives on to the next generation.
  - Any live cell with more than three live neighbors dies (overpopulation).
  - Any dead cell with exactly three live neighbors becomes a live cell (reproduction).

## Contributing

Feel free to submit issues or pull requests if you would like to contribute to this project.