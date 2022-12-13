import com.google.gson.Gson;
import me.haifisch.AOC2022.AOC2022Helper;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final Gson g               = new Gson();

    private static final List<Object> DIVIDER = new ArrayList<>(){{
        add(g.fromJson("[[2]]", Object.class));
        add(g.fromJson("[[6]]", Object.class));
    }};

    public static void main(String[] args){



        List<PacketPair> pairs = new ArrayList<>();

        AtomicInteger currentLine = new AtomicInteger(0);
        AOC2022Helper.applyFunctionToDataForCurrentDay(13, line -> {
            currentLine.getAndIncrement();

            switch (currentLine.get()) {
                case 1 -> pairs.add(new PacketPair(g.fromJson(line, Object.class)));
                case 2 -> pairs.get(pairs.size() - 1).setRight(g.fromJson(line, Object.class));
                case 3 -> currentLine.set(0);
            }
        });

        List<Integer> correctPackages = new ArrayList<>();

        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).isRightOrder())
                correctPackages.add(i + 1);
        }

        System.out.print("Correct:");

        correctPackages.forEach(i -> System.out.printf(" %d ", i));

        System.out.println();

        System.out.println("Sum of those: " + correctPackages.stream().mapToInt(Integer::valueOf).sum());

        List<Object> allPackages = new ArrayList<>();

        pairs.forEach(p -> {
            allPackages.add(p.getLeft());
            allPackages.add(p.getRight());
        });

        allPackages.addAll(DIVIDER);

        allPackages.sort((l, r) -> {
            switch (PacketPair.compare(l, r)) {

                case INORDER -> {
                    return 1;
                }
                case NOTINORDER -> {
                    return -1;
                }
                case EQUAL -> {
                    return 0;
                }
                default -> throw new RuntimeException("bad sort");
            }
        });

        Collections.reverse(allPackages);

        List<Integer> dividerIndices = new ArrayList<>();

        for (int i = 0; i < allPackages.size(); i++) {
            Object p = allPackages.get(i);

            if (DIVIDER.contains(p))
                dividerIndices.add(i + 1);
        }

        System.out.print("Divider at: ");

        dividerIndices.forEach(d -> System.out.printf(" %d ", d));

        System.out.println();
        System.out.println("Decoder key: " + dividerIndices.stream().mapToInt(Integer::valueOf).reduce(1, (a, b) -> a*b));
    }
}