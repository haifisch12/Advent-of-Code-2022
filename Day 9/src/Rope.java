import me.haifisch.AOC2022.AOC2022Console;
import me.haifisch.AOC2022.ConsoleColors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Rope {

    private final RopePart head;
    private final RopePart tail;

    public Rope(int length, int startRowIndex, int startColumnIndex) {
        if (length < 2)
            throw new IllegalArgumentException("Rope is too short!");

        head = new RopePart(startRowIndex, startColumnIndex, null);

        RopePart currentPart = head;

        for (int i = 0; i < length - 1; i++) {
            currentPart.setNextPart(new RopePart(startRowIndex, startColumnIndex, currentPart));
            currentPart = currentPart.getNextPart();
        }

        tail = currentPart;
    }

    public void doInstruction(Instruction instruction) {
        for (int i = 0; i < instruction.getStepSize(); i++) {

            int previousRow     = Integer.MIN_VALUE;
            int previousColumn  = Integer.MIN_VALUE;

            RopePart currentPart = head;

            do {
                if (currentPart.isHead())
                    currentPart.move(instruction.getDirection());
                else if (getLargestDistance(currentPart, currentPart.getPreviousPart()) > 1) {
                    currentPart.moveTowardsPreviousPart();
                }

                currentPart     = currentPart.getNextPart();
            } while (currentPart != null);

            // uncomment for visualisation
            // AOC2022Console.printColorln(instruction.getDirection() + " - " + instruction.getStepSize(), ConsoleColors.Black);
            // print();
        }
    }

    // prints the board. might need to change grid size based on inputs.
    private void print() {
        List<RopePart> allParts = new ArrayList<>();

        var currentPart = head;
        do {
            allParts.add(currentPart);
            currentPart = currentPart.getNextPart();
        } while (currentPart != null);

        for (int i = -15; i < 15; i++) {
            for (int j = -15; j < 15; j++) {
                int finalI = i;
                int finalJ = j;

                RopePart part = allParts.stream().filter(r -> r.getRowIndex() == finalI && r.getColumnIndex() == finalJ).findFirst().orElse(null);

                String symbol = ".";

                if (i == 0 && j == 0)
                    symbol = "s";

                if (part != null) {
                    if (part.isHead())
                        symbol = "h";
                    else if (part.isTail())
                        symbol = "t";
                    else if (!symbol.equals("s"))
                        symbol = "p";
                }

                if (j == 14)
                    AOC2022Console.printColorln(symbol, ConsoleColors.Cyan);
                else
                    AOC2022Console.printColor(symbol, ConsoleColors.Cyan);

            }
        }
    }


    private int getLargestDistance(RopePart previousPart, RopePart currentPart) {
        int rowDistance     = previousPart.getRowIndex()      - currentPart.getRowIndex();
        int colDistance     = previousPart.getColumnIndex()   - currentPart.getColumnIndex();
        int diagDistance    = (int)Math.sqrt(Math.pow(rowDistance, 2) + Math.pow(colDistance, 2));

        return Math.max(diagDistance, Math.max(rowDistance, colDistance));
    }

    public Set<String> getSpacesVisitedByTail() {
        return tail.getVisitedSpaces();
    }
}
