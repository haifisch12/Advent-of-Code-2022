import java.util.Map;

public class Instruction {

    private final int moveCount;
    private final int moveFrom;
    private final int moveTo;

    public Instruction(String line) {
        String[] splitLine = line.split(" ");

        moveCount = Integer.parseInt(splitLine[1]);
        moveFrom  = Integer.parseInt(splitLine[3]);
        moveTo    = Integer.parseInt(splitLine[5]);
    }

    public void doInstruction(Map<Integer, CrateStack> stacks) {
        // could also move the entire list and just reverse it instead of doing one by one.
        for (int i = 0; i < moveCount; i++) {
            stacks.get(moveTo).addToStackTop(stacks.get(moveFrom).takeStackTop());
        }
    }

    // can move multiple at once. noice.
    public void doInstructionMultiple(Map<Integer, CrateStack> stacks) {
        stacks.get(moveTo).addToStackTop(stacks.get(moveFrom).takeStackTop(moveCount));
    }

    @Override
    public String toString() {
        return "move " + moveCount + " from " + moveFrom + " to " + moveTo;
    }

    public int getMoveFrom() {
        return moveFrom;
    }

    public int getMoveTo() {
        return moveTo;
    }
}
