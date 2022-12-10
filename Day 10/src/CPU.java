import java.util.Map;

public class CPU {

    private static final int CRT_WIDTH  = 40;

    private int currentCycle;

    public CPU() {
        currentCycle = 0;
    }

    public int doInstruction(int x, Instruction instruction, Map<Integer, Integer> interestingSignals) {
        for (int i = 0; i < instruction.getInstruction().getCycles(); i++) {
            // next CRT-Screen line
            if (currentCycle % CRT_WIDTH == 0)
                System.out.println();

            currentCycle++;

            if (interestingSignals.containsKey(currentCycle)) {
                interestingSignals.put(currentCycle, x);
            }

            drawCRTPixel(x);
        }

        if (instruction.getInstruction().equals(CPUInstructions.addx))
            x += instruction.getArgument();

        return x;
    }

    private void drawCRTPixel(int x) {
        int  cycle  = currentCycle % CRT_WIDTH;
        char symbol = '.';

        if (x == cycle || x == cycle - 1 || x == cycle - 2)
            symbol = '#';

        System.out.print(symbol);
    }
}
