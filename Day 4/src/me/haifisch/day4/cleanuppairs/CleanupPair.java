package me.haifisch.day4.cleanuppairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CleanupPair {
    private List<Integer> firstElfAssignments;
    private List<Integer> secondElfAssignments;

    public CleanupPair(String line) {
        String[] ranges = line.split(",");

        // line format: 2-3,5-8 --- needed ranges are 2,3 and 5,6,7,8
        firstElfAssignments  = getAllIntegersInRange(ranges[0]);
        secondElfAssignments = getAllIntegersInRange(ranges[1]);
    }

    private List<Integer> getAllIntegersInRange(String range) {
        List<Integer> result = new ArrayList<>();

        int[] startAndEnd = Arrays.stream(range.split("-")).mapToInt(Integer::valueOf).toArray();

        for (int i = startAndEnd[0]; i <= startAndEnd[1]; i++) {
            result.add(i);
        }

        return result;
    }

    // does at least one of the assignments contain the other
    public boolean doAssignmentsOverlapFully() {
        return firstElfAssignments.containsAll(secondElfAssignments) || secondElfAssignments.containsAll(firstElfAssignments);
    }

    public boolean doAssignmentsOverall() {
        for (int number: firstElfAssignments) {
            if (secondElfAssignments.contains(number))
                return true;
        }

        return false;
    }
}
