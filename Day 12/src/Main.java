import me.haifisch.AOC2022.AOC2022Helper;

public class Main {
    public static void main(String[] args) {
        HeightMap map = new HeightMap();

        AOC2022Helper.applyFunctionToDataForCurrentDay(12, map::processInputLine);

        Path result = map.findShortestPath(map.getStart());

        System.out.println("Part 1: Shortest path: " + result.getPathStepSize());

        System.out.println("Starting part 2...");

        result = map.findShortestPathForEveryA(false);

        System.out.println("Part 2: Shortest path: " + result.getPathStepSize());
    }
}