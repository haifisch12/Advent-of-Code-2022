import me.haifisch.AOC2022.AOC2022Console;
import me.haifisch.AOC2022.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    private final String TAB    = "   ";


    private final   int             id;
    private final   List<Long>      items;
    private final   MathOperator    operator;
    private final   Integer         operationValue; // if null, use current item

    private final   MonkeyTest      test;

    private         long            activity;

    public Monkey(int id, List<Long> items, MathOperator operator, Integer operationValue, MonkeyTest test) {
        this.id             = id;
        this.items          = new ArrayList<>(items);
        this.operator       = operator;
        this.operationValue = operationValue;
        this.test           = test;
        this.activity       = 0;
    }

    public void inspectItems(List<Monkey> allMonkeys, boolean isPartOne, long modulo) {
        AOC2022Console.printColorln("Monkey " + id, ConsoleColors.Cyan);

        if (!isPartOne)
            reduceItemWorryLevels(modulo);

        while (items.size() > 0) {
            activity++;

            long currentItem = items.get(0);
            AOC2022Console.printColorf(TAB + "Monkey inspects an item with a worry level of %d.%n", ConsoleColors.Cyan, currentItem);

            long actualOperationValue   = operationValue == null? currentItem : operationValue;
            currentItem                 = (long)operator.applyAsDouble(currentItem, actualOperationValue);

            AOC2022Console.printColorf(TAB + TAB + "Worry level is multiplied by %d to %d.%n",
                    ConsoleColors.Green, actualOperationValue, currentItem);


            if (isPartOne) {
                currentItem                 = (int)MathOperator.DIVIDE.applyAsDouble(currentItem, 3);

                AOC2022Console.printColorf(TAB + TAB + "Monkey gets bored with item. Worry level is divided by 3 to %d.%n",
                        ConsoleColors.Red, currentItem);
            }

            items.set(0, currentItem);

            testItem(0, allMonkeys);
        }
    }

    private void reduceItemWorryLevels(long modulo) {
        // reduce each item (worry level) by applying the modulo to the item.
        List<Long> modifiedItems = items.stream().map(i -> i % modulo).toList();

        items.clear();
        items.addAll(modifiedItems);
    }

    private void testItem(int itemIndex, List<Monkey> allMonkeys) {
        int testResult      = test.getTestResult(items.get(itemIndex));
        Monkey targetMonkey = allMonkeys.stream().filter(m -> m.getId() == testResult).findFirst().orElse(null);

        if (targetMonkey == null)
            throw new IllegalArgumentException("Couldn't find monkey with ID " + testResult);

        throwItem(itemIndex, targetMonkey);
    }

    private void throwItem(int itemIndex, Monkey targetMonkey) {
        AOC2022Console.printColorf(TAB + TAB + "Item with worry level %d is thrown to monkey %d.%n",
                ConsoleColors.Red, items.get(itemIndex), targetMonkey.getId());

        targetMonkey.receiveItem(items.get(itemIndex));

        items.remove(itemIndex);
    }

    private void receiveItem(long itemValue) {
        items.add(itemValue);
    }

    private int getId() {
        return id;
    }

    public List<Long> getItems() {
        return items;
    }

    public long getActivity() {
        return activity;
    }

    public int getTestValue() {
        return test.getOperationValue();
    }

    @Override
    public String toString() {
        return "Monkey " + id;
    }
}
