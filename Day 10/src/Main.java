import me.haifisch.AOC2022.AOC2022Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Instruction> instructions = new AOC2022Helper<Instruction>().getDataForCurrentDay(10, Instruction::new);

        Map<Integer, Integer> interestingSignals = new HashMap<>() {{
            put(20, 0);
            put(60, 0);
            put(100, 0);
            put(140, 0);
            put(180, 0);
            put(220, 0);
        }};

        CPU cpu = new CPU();

        // default start value is 1
        int x = 1;

        for (Instruction instruction: instructions)
            x = cpu.doInstruction(x, instruction, interestingSignals);

        int result = 0;

        for (Map.Entry<Integer, Integer> kvp : interestingSignals.entrySet())
            result += kvp.getKey() * kvp.getValue();

        System.out.println();
        System.out.println("Sum interesting signals: " + result);
    }

}