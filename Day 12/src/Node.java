public class Node {
    private final int       row;
    private final int       col;
    private final int       height;

    private final boolean   isStart;
    private final boolean   isEnd;

    public Node(int row, int col, char heightAsChar) {
        this.row    = row;
        this.col    = col;

        this.isStart    = heightAsChar == 'S';
        this.isEnd      = heightAsChar == 'E';

        this.height = parseHeight(heightAsChar);
    }

    private int parseHeight(char heightAsChar) {
        int result;

        switch (heightAsChar) {
            case 'S' -> result = 'a';
            case 'E' -> result = 'z';
            default  -> result = heightAsChar;
        }

        return result;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getHeight() {
        return height;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", getRow(), getCol()) + (isStart()? " (S)" : isEnd()? " (E)" : "");
    }
}
