import me.haifisch.AOC2022.AOC2022Helper;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Instruction> instructions  = new AOC2022Helper<Instruction>().getDataForCurrentDay(5, Instruction::new);
        Map<Integer, CrateStack> stacks = createStacksFromInput(new AOC2022Helper<String>().getDataForCurrentDay("data_day5_stacks.txt", String::new));

        System.out.println("----- Original Stacks from low to high -----");
        stacks.forEach((key, value) -> System.out.printf("Stack %d: %s%n", key, value.toString()));

        // part 1
        //instructions.forEach(i -> i.doInstruction(stacks));

        //part 2
        instructions.forEach(i -> i.doInstructionMultiple(stacks));

        System.out.println("----- Reordered Stacks from low to high -----");
        stacks.forEach((key, value) -> System.out.printf("Stack %d: %s%n", key, value.toString()));
        System.out.println("Answer: " + stacks.values().stream().map(CrateStack::getStackTop).collect(Collectors.joining()).
                        replace("[", "").replace("]", ""));
    }

    private static Map<Integer, CrateStack> createStacksFromInput(List<String> input) {
        Map<Integer, CrateStack> result = new HashMap<>();

        Arrays.stream(input.get(input.size() - 1).split(" ")).filter(s -> !s.isEmpty()).
                        mapToInt(Integer::valueOf).forEach(s -> result.put(s, new CrateStack()));

        input.remove(result.size() - 1);

        input.forEach(i -> {
            int stackPosition = 1;
            String[] splitLine = i.split(" ");

            for (int j = 0; j < splitLine.length; j++) {
                if (!splitLine[j].isEmpty()){
                    result.get(stackPosition).addToStackBottom(splitLine[j]);
                }
                else {
                    // 4 spaces between empty stacks. (-1 cuz we are currently in one)
                    j += 3;
                }
                stackPosition++;
            }
        });

        return result;
    }
}