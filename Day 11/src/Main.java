import me.haifisch.AOC2022.AOC2022Console;
import me.haifisch.AOC2022.AOC2022Helper;
import me.haifisch.AOC2022.ConsoleColors;

import java.util.*;

public class Main {

    private final static int        NUMBER_OF_ROUNDS    = 10_000;
    private final static boolean    IS_PART_ONE         = false;

    public static void main(String[] args) {
        MonkeyParser parser = new MonkeyParser();

        AOC2022Helper.applyFunctionToDataForCurrentDay(11, parser::processLine);

        parser.doneParsing();

        List<Monkey> monkeys   = parser.getMonkeys();
        List<RoundStats> stats = new ArrayList<>();

        stats.add(new RoundStats(0, monkeys));

        // multiply all "test divisors" of them monkeys.
        long modulo = monkeys.stream().mapToInt(Monkey::getTestValue).reduce(1, (a, b) -> a * b);

        for (int i = 1; i <= NUMBER_OF_ROUNDS; i++) {
            AOC2022Console.printColorf("--------Round %d--------%n",
                    ConsoleColors.Purple, i);

            monkeys.forEach(m -> m.inspectItems(monkeys, IS_PART_ONE, modulo));

            stats.add(new RoundStats(i, monkeys));
        }

        stats.forEach(s -> {
            AOC2022Console.printColorf("--------Round %d--------%n", ConsoleColors.Purple, s.getRoundNumber());
            AOC2022Console.printColorf(s.toString(), ConsoleColors.Blue);
        });

        AOC2022Console.printColorln("--------Activity--------", ConsoleColors.Purple);

        monkeys.forEach(m -> AOC2022Console.printColorf("%s inspected items %d times.%n", ConsoleColors.Blue, m.toString(), m.getActivity()));

        AOC2022Console.printColorf("--------Monkey business level after %d rounds--------%n", ConsoleColors.Purple, NUMBER_OF_ROUNDS);

        Monkey monkey1 = Collections.max(monkeys, Comparator.comparingLong(Monkey::getActivity));
        monkeys.remove(monkey1);

        Monkey monkey2 = Collections.max(monkeys, Comparator.comparingLong(Monkey::getActivity));

        long monkeyBusiness = monkey1.getActivity() * monkey2.getActivity();

        AOC2022Console.printColorln(String.valueOf(monkeyBusiness), ConsoleColors.Red);
    }
}