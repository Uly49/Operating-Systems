/*
 * Ulysses Palomar
 * CSCI 370 - Operating Systems
 * Dr. Susan Lincke
 * Homework 2
 */

// import java.util.Arrays;

public class HeatMap {
    private static int gridSize;
    private static int totalThreads;
    private static int totalCells;

    public static void main(String[] args) {
        gridSize = 10;

        totalThreads = 1;

        System.out.println("Grid size: " + gridSize + "x" + gridSize);

        totalCells = (gridSize - 2) * (gridSize - 2);
        System.out.println("Total number of cells being calculated: " + totalCells);

        // Number of cells each thread will handle
        int rowsPerThread = ((gridSize - 2) / totalThreads);
        int leftOverRows = ((gridSize - 2) % totalThreads);

        // Initialize array grid to predetermined size
        double[][] grid = new double[gridSize][gridSize];

        // Set sides of grid to default values
        grid[0][0] = 22.5; // Top left cell is average of 15 and 30
        grid[0][gridSize - 1] = 51.0; // Top right cell is average of 30 and 72
        grid[gridSize - 1][0] = 45.0; // Bottom left cell is average of 15 and 75
        grid[gridSize - 1][gridSize - 1] = 73.5; // Bottom right cell is average of 72 and 75

        for (int i = 1; i < gridSize - 1; i++) {
            grid[0][i] = 30.0; // Set top row to default values
        } // End for loop

        for (int i = 1; i < gridSize - 1; i++) {
            grid[i][0] = 15.0; // Set left column to default values
        } // End for loop

        for (int i = 1; i < gridSize - 1; i++) {
            grid[i][gridSize - 1] = 72.0; // Set right column to default values
        } // End for loop

        for (int i = 1; i < gridSize - 1; i++) {
            grid[gridSize - 1][i] = 75.0; // Set bottom row to default values
        } // End for loop

        System.out.println("Statistics:");

        if (totalThreads == 1) {
            System.out.println("Type: Single thread");
        }
        if (totalThreads != 1) {
            System.out.println("Type: Multi thread (" + totalThreads + ")");
        }

        // Create threads and run Average method
        // double error = 0;
        int iteration = 0;
        int rowStart = 1;
        double[] threadError = new double[4];
        for (int i = 0; i < threadError.length; i++) {
            threadError[i] = 0;
        }
        for (int i = 0; i < totalThreads; i++) {
            int rowsForThisThread = rowsPerThread;

            // Add leftover rows to each thread
            if (leftOverRows > 0) {
                rowsForThisThread++;
                leftOverRows--;
            } // End if

            System.out.println("Thread " + i + " will handle " + rowsForThisThread + " rows");
            System.out.println("Thread " + i + " will start at " + rowStart);
            Multithread thread = new Multithread(gridSize, grid, i, rowStart, rowsForThisThread, /* error, */
                    iteration, threadError);
            thread.start();

            // Set new starting row for next thread
            rowStart += rowsForThisThread;
        } // End for loop

        // for (int i = 0; i < gridSize; i++) { // print out each row
        // System.out.println(Arrays.toString(grid[i]));
        // } // End for loop

    } // End main

} // End class