import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Grid {
    private final   Map<String, CellState>      cells;

    private         int                         yVoidStart;

    private         int                         yFloor;


    public Grid() {
        cells       = new HashMap<>();

        yVoidStart  = 0;
        yFloor      = 0;
    }

    public void addRocks(String line) {
        int[][] splitLine = Arrays.stream(line.replace("-> ", "").split(" ")).map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);


        for (int i = 0; i <= splitLine.length - 2; i++) {
            // splitLine[0] -> x --- splitLine[1] -> y
            int xDelta = splitLine[i + 1][0] - splitLine[i][0];
            int yDelta = splitLine[i + 1][1] - splitLine[i][1];

            for (int j = 0; j <= Math.abs(xDelta); j++) {
                addCellState(splitLine[i][0] + (j * (int)Math.signum(xDelta)), splitLine[i][1], CellState.Rock);
            }

            for (int j = 0; j <= Math.abs(yDelta); j++) {
                addCellState(splitLine[i][0], splitLine[i][1] + (j * (int)Math.signum(yDelta)), CellState.Rock);

                yVoidStart = Math.max(splitLine[i][1] + (j * (int)Math.signum(yDelta) + 1), yVoidStart);

                yFloor     = yVoidStart + 1;
            }
        }
    }

    public void dropSand() {
        CellState lastState = CellState.Air;

        while (!lastState.equals(CellState.Void)) {
            lastState = dropSand(500, 0);

            // part 2
            if (getCellState(500, 0).equals(CellState.Sand))
                break;
        }
    }

    public int getSandCount() {
        return cells.values().stream().filter(c -> c.equals(CellState.Sand)).toList().size();
    }

    private CellState dropSand(int x, int y) {
        // part 1
        //if (y > yVoidStart)
        //    return CellState.Void;

        // part 2
        if (y + 1 == yFloor) {
            addCellState(x, y, CellState.Sand);
            return CellState.Sand;
        }

        if (getCellState(x, y + 1).equals(CellState.Air)) {
            return dropSand(x, y + 1);
        }
        else if (getCellState(x - 1, y + 1).equals(CellState.Air)) {
            return dropSand(x - 1, y + 1);
        }
        else if (getCellState(x + 1, y + 1).equals(CellState.Air)) {
            return dropSand(x + 1, y + 1);
        }
        else {
            addCellState(x, y, CellState.Sand);
            return CellState.Sand;
        }
    }

    private String pointToString(int x, int y) {
        return String.format("%d,%d", x, y);
    }

    private void addCellState(int x, int y, CellState state) {
        cells.put(pointToString(x, y), state);
    }

    private CellState getCellState(int x, int y) {
        CellState result = cells.get(pointToString(x, y));

        return result == null? CellState.Air : result;
    }
}
