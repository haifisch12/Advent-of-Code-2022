import me.haifisch.AOC2022.AOC2022Helper;

import java.awt.*;

public class Main {

    private static final int Y_LEVEL = 2000000;
    private static final int LIMIT  = 4000000;

    private static final boolean PRINT_INFORMATION  = false;

    public static void main(String[] args) {

        Grid grid = new Grid();

        AOC2022Helper.applyFunctionToDataForCurrentDay(15, grid::processLine);

        long x = System.nanoTime();
        grid.calculateSensorRanges();

        // part 1
        grid.calculateNoBeaconSpaces(Y_LEVEL);

        //grid.print(0, 20, 0, 20);

        System.out.println("Sum of points with no beacon: " + grid.getSumNoBeaconAtY(Y_LEVEL));

        // part 2
        Point result = grid.getPointOfDistressBeacon(0, LIMIT, PRINT_INFORMATION);

        System.out.println("Distress Beacon location: " + result.toString());

        long frequency = (long) result.x * LIMIT + result.y;
        System.out.println("Distress Beacon tuning frequency: " + frequency);

        long y = System.nanoTime();

        System.out.println("Elapsed: " + (double)(y - x) / 1_000_000 + "ms; or " + (double)(y - x) / 1_000_000_000  + "s");
    }
}