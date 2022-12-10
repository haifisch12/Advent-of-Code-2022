public class Instruction {

    private final int stepSize;

    private final Directions direction;

    public Instruction(String line) {
        String[] splitLine = line.split(" ");

        stepSize = Integer.parseInt(splitLine[1]);

        switch (splitLine[0]) {
            case "U" -> direction = Directions.Up;
            case "D" -> direction = Directions.Down;
            case "R" -> direction = Directions.Right;
            case "L" -> direction = Directions.Left;
            default -> throw new IllegalArgumentException("Given input isn't compatible.");
        }
    }

    public int getStepSize() {
        return stepSize;
    }

    public Directions getDirection() {
        return direction;
    }
}
