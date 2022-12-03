import me.haifisch.AOC2022.AOC2022Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        Path myPath = AOC2022Helper.getDataPathForCurrentDay(1);

        ArrayList<Integer> elfData = new ArrayList<>();
        elfData.add(0);

        Files.lines(myPath).forEach(line -> {
            if (line.isEmpty()) {
                elfData.add(0);
            } else {
                elfData.set(elfData.size() - 1, elfData.get(elfData.size() - 1) + Integer.parseInt(line));
            }
        });

        Integer temp;
        Integer maxCalories = elfData.stream().max(Integer::compare).get();
        Integer maxTopThree = maxCalories;

        // loops brother
        elfData.remove(maxCalories);
        temp = elfData.stream().max(Integer::compare).get();
        maxTopThree += temp;
        elfData.remove(temp);
        maxTopThree += elfData.stream().max(Integer::compare).get();

        System.out.printf("We have %d elves.%n", elfData.size() + 2); // +2 cuz i removed two :)
        System.out.printf("The elf with most calories has %d calories.%n", maxCalories);

        System.out.printf("Top three have %d%n", maxTopThree);
    }
}