import java.util.Arrays;

public class Multithread extends Thread {
    // Initialize variables
    int gridSize;
    double[][] grid;
    int threadNum;
    int rowStart;
    int rowsForThisThread;
    int iteration;
    double[] threadError;
    double currentError;
    double nook;

    public Multithread(int gridSize, double[][] grid, int threadNum, int rowStart,
            int rowsForThisThread, int iteration, double[] threadError) {
        this.gridSize = gridSize;
        this.grid = grid;
        this.threadNum = threadNum;
        this.rowStart = rowStart;
        this.rowsForThisThread = rowsForThisThread;
        this.threadError = threadError;
        this.iteration = iteration;

    } // End Multithread()

    public void printGrid() {
        for (int i = 0; i < gridSize; i++) { // print out each row
            System.out.println(Arrays.toString(grid[i]));
        } // End for loop
    } // End printGrid()

    public void statistics(double threadError[], int iteration) {
        System.out.println("Statistics:");

        System.out.println("Total error: " + (threadError[0] + threadError[1] + threadError[2] + threadError[3]));
        System.out.println("Total number of iterations: " + iteration);

        // Calculate grid's average temperature
        int totalCount = 0;
        double sum = 0;
        for (int i = 1; i < gridSize - 1; i++) {
            for (int j = 1; j < gridSize - 1; j++) {
                totalCount++;
                sum = sum + grid[i][j];
            } // End fpr loop
        } // End fpr loop
        double average = sum / totalCount;
        System.out.println("Average grid temperature: " + average);

        System.out.println("Final array: ");
        printGrid();
    }

    public void printError() {
        System.out.println("threadError[0]" + threadError[0]);
        System.out.println("threadError[1]" + threadError[1]);
        System.out.println("threadError[2]" + threadError[2]);
        System.out.println("threadError[3]" + threadError[3]);
    }

    @Override
    public void run() {
        long startT = System.currentTimeMillis();

        do {
            // Increment iteration
            iteration++;
            int workingRow = rowStart;
            threadError[(threadNum)] = 0;
            // printError();

            for (int i = 1; i <= rowsForThisThread; i++) { // Iterate through grid rows
                for (int j = 1; j < gridSize - 1; j++) { // Iterate through grid columns

                    // Initialize variables used to calculate average
                    double sum = 0;
                    double newAverage = 0;
                    double prevAverage = grid[workingRow][j];

                    // Calculate sum of surrounding cells
                    sum = grid[workingRow - 1][j] + grid[workingRow + 1][j] + grid[workingRow][j - 1]
                            + grid[workingRow][j + 1];

                    // Calculate average of surrounding cells
                    newAverage = Math.round((sum / 4) * 10.0) / 10.0;

                    // Set average of surrounding cells as new cell value
                    grid[workingRow][j] = newAverage;

                    // Update new total error
                    double error = (newAverage - prevAverage);

                    threadError[(threadNum)] = threadError[threadNum] + error;
                    // error += Math.abs(newAverage - prevAverage);
                    currentError = threadError[threadNum];
                } // End for loop

                workingRow++;
                // printError();

            } // End for loop
            if (threadNum == 1) {
                nook = threadError[0];
            }
            // nook = (threadError[0] + threadError[1] + threadError[2] + threadError[3]);

            // System.out.println("threadError[0]" + threadError[0]);
            // System.out.println("threadError[1]" + threadError[1]);
            // System.out.println("threadError[2]" + threadError[2]);
            // System.out.println("threadError[3]" + threadError[3]);
            System.out.println("Final error for this iteration is " + nook);
            // printGrid();

        } while ((threadError[0] + threadError[1] + threadError[2] + threadError[3]) > 5); // End do loop
        // }

        // Only thread 1 will print statistics
        if (threadNum == 1) {
            statistics(threadError, iteration);
        }
        long endT = System.currentTimeMillis();
        long totalTime = endT - startT;
        System.out.println("Total time: " + totalTime);
    } // End run
} // End class