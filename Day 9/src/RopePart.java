import java.util.HashSet;
import java.util.Set;

public class RopePart {
    private         int         rowIndex;
    private         int         columnIndex;

    private         RopePart    nextPart;

    private final   RopePart    previousPart;
    private final   Set<String> visitedSpaces;

    public RopePart(int startRowIndex, int startColumnIndex, RopePart previousRopePart) {
        rowIndex        = startRowIndex;
        columnIndex     = startColumnIndex;
        previousPart    = previousRopePart;

        visitedSpaces   = new HashSet<>();
        addVisitedSpace(startRowIndex, startColumnIndex);
    }

    public void move(Directions direction) {
        switch (direction) {
            case Up         -> move(getRowIndex() - 1, getColumnIndex());
            case Down       -> move(getRowIndex() + 1, getColumnIndex());
            case Left       -> move(getRowIndex(), getColumnIndex() - 1);
            case Right      -> move(getRowIndex(), getColumnIndex() + 1);
        }
    }

    public void move(int newRowIndex, int newColumnIndex) {
        rowIndex    = newRowIndex;
        columnIndex = newColumnIndex;

        addVisitedSpace(rowIndex, columnIndex);
    }

    public void moveTowardsPreviousPart() {
        int rowDelta = previousPart.rowIndex    - this.rowIndex;
        int colDelta = previousPart.columnIndex - this.columnIndex;

        move(getRowIndex() + (int)Math.signum(rowDelta), getColumnIndex() + (int)Math.signum(colDelta));
    }

    private void addVisitedSpace(int rowIndex, int columnIndex) {
        visitedSpaces.add("r:" + rowIndex + ",c:" + columnIndex);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Set<String> getVisitedSpaces() {
        return visitedSpaces;
    }

    public void setNextPart(RopePart nextPart) {
        this.nextPart = nextPart;
    }

    public RopePart getPreviousPart() {
        return previousPart;
    }

    public RopePart getNextPart() {
        return nextPart;
    }

    public boolean isHead() {
        return getPreviousPart() == null;
    }

    public boolean isTail() {
        return getNextPart() == null;
    }

    @Override
    public String toString() {
        return (isHead()? "Head: " : isTail()? "Tail: " : "Part: ") + "r:" + rowIndex + ",c:" + columnIndex;
    }
}
