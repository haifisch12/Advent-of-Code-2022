import me.haifisch.AOC2022.AOC2022Helper;
import me.haifisch.day4.cleanuppairs.CleanupPair;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        List<CleanupPair> pairs = new AOC2022Helper<CleanupPair>().getDataForCurrentDay(4, CleanupPair::new);

        AtomicInteger counter = new AtomicInteger();

        pairs.forEach(p -> {
            if (p.doAssignmentsOverlapFully()) counter.addAndGet(1);
        });

        System.out.println("Assignment pairs where one range fully contains the other: " + counter);

        counter.set(0);

        pairs.forEach(p -> {
            if (p.doAssignmentsOverall()) counter.addAndGet(1);
        });

        System.out.println("Assignment pairs where ranges overlap: " + counter);
    }
}