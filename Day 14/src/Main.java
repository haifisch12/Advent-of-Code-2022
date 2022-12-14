import me.haifisch.AOC2022.AOC2022Helper;

public class Main {
    public static void main(String[] args) {

        Grid grid = new Grid();

        AOC2022Helper.applyFunctionToDataForCurrentDay(14, grid::addRocks);

        grid.dropSand();

        System.out.println("Sand count: " + grid.getSandCount());
    }
}