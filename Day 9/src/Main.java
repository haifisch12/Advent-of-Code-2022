import me.haifisch.AOC2022.AOC2022Console;
import me.haifisch.AOC2022.AOC2022Helper;
import me.haifisch.AOC2022.ConsoleColors;

import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        List<Instruction> instructions = new AOC2022Helper<Instruction>().getDataForCurrentDay(9, Instruction::new);

        Rope rope = new Rope(10, 0, 0);

        instructions.forEach(rope::doInstruction);

        AOC2022Console.printColorln("Visited spaces: " + rope.getSpacesVisitedByTail().size(), ConsoleColors.Green);
    }

}