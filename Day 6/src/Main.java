import me.haifisch.AOC2022.AOC2022Helper;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // .get(0) cause input is actually just one long line today.
        String rawInput = new AOC2022Helper<String>().getDataForCurrentDay(6, String::valueOf).get(0);

        // +1 because of 0 based index
        // part 1
        System.out.println("Processed characters until packet marker: " + (findEndIndexOfMarker(rawInput, 4) + 1));

        // part 2
        System.out.println("Processed characters until message marker: " + (findEndIndexOfMarker(rawInput, 14) + 1));
    }

    public static int findEndIndexOfMarker(String input, int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            // Makes sure only the most recent {length}th characters are in the string.
            if (sb.length() > length) {
                sb.deleteCharAt(0);
            }

            // Checks if desired length is reached and if all characters are unique.
            if (sb.length() >= length && allCharactersDifferent(sb)) {
                return i;
            }
        }

        // No start-of-packet marker was found
        return -1;
    }

    public static boolean allCharactersDifferent(StringBuilder sb) {
        Set<Character> uniqueChars = new HashSet<>();

        for (int i = 0; i < sb.length(); i++) {
            // Only adds to the size, if the Key is unique.
            uniqueChars.add(sb.charAt(i));
        }

        return uniqueChars.size() == sb.length();
    }

}