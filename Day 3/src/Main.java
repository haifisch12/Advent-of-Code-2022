import me.haifisch.AOC2022.AOC2022Helper;
import me.haifisch.day3.backpack.Backpack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int LOWER_CASE_PRIO_OFFSET = 96;
    private static final int UPPER_CASE_PRIO_OFFSET = 38;

    public static void main(String[] args) {
        List<Backpack> backpacks = new AOC2022Helper<Backpack>().getDataForCurrentDay(3, Backpack::new);

        // part 1
        AtomicInteger totalSharedPriority = new AtomicInteger();

        backpacks.forEach(b -> {
            b.getSharedChars().forEach(c -> totalSharedPriority.addAndGet(getPriorityOfChar(c)));
        });

        System.out.printf("Sum of priority of each backpacks shared items: %d.%n", totalSharedPriority.get());
        
        // part 2
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        alphabet       += alphabet.toUpperCase();

        Map<Character, Integer> groupsPerCharacter = new HashMap<>();

        List<Backpack> backpacksToCheck = new ArrayList<>();
        for (int i = 0; i < backpacks.size(); i++) {
            backpacksToCheck.add(backpacks.get(i));

            if (backpacksToCheck.size() == 3) {
                for (int j = 0; j < backpacksToCheck.get(0).getAllBackpackItems().length(); j++) {
                    char currentChar = backpacksToCheck.get(0).getAllBackpackItems().charAt(j);

                    if (backpacksToCheck.stream().allMatch(b -> b.containsChar(currentChar))) {
                        groupsPerCharacter.put(currentChar, groupsPerCharacter.containsKey(currentChar)? groupsPerCharacter.get(currentChar) + 1 : 1);
                        backpacksToCheck = new ArrayList<>();
                        break;
                    }
                }
            }
        }

        System.out.printf("We have %d item types with %d groups on average. One group consists of three backpacks." +
                        "Total group priority is %d.%n", groupsPerCharacter.size(),
                groupsPerCharacter.values().stream().mapToInt(Integer::valueOf).sum() / groupsPerCharacter.values().size(), // avg
                groupsPerCharacter.keySet().stream().mapToInt(c -> getPriorityOfChar(c) * groupsPerCharacter.get(c)).sum());
    }

    private static int getPriorityOfChar(Character character) {
        return Character.isUpperCase(character)? character - UPPER_CASE_PRIO_OFFSET : character - LOWER_CASE_PRIO_OFFSET;
    }
}