import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonkeyParser {
    private int                 currentId;
    private final List<Long>    currentItems;
    private MathOperator        currentOperator;
    private Integer             currentOperationValue; // if null, use current item

    private MonkeyTest          currentTest;


    private int                 lineCount;

    private final List<Monkey>  monkeys;

    public MonkeyParser() {
        currentItems = new ArrayList<>();
        monkeys      = new ArrayList<>();

        lineCount    = 0;
    }

    public void processLine(String line) {

        switch (lineCount) {
            case 0  -> parseId(line);
            case 1  -> parseItems(line);
            case 2  -> parseOperation(line);
            case 3  -> parseTestOperation(line);
            case 4  -> currentTest.setTestPositiveId(parseTestValue(line));
            case 5  -> currentTest.setTestNegativeId(parseTestValue(line));
            case 6  -> addNewMonkey();
            default -> throw new RuntimeException("LineCount has impossible value");
        }

        lineCount++;
    }

    public void doneParsing() {
        addNewMonkey();
    }

    private void addNewMonkey() {
        lineCount = -1;
        monkeys.add(new Monkey(currentId, currentItems, currentOperator, currentOperationValue, currentTest));
    }

    private void parseId(String line) {
        currentId = Integer.parseInt(line.replace(":", "").split(" ")[1]);
    }

    private void parseItems(String line) {
        String[] splitLine = line.substring(line.indexOf(':') + 2).replace(",", "").split(" ");

        currentItems.clear();

        Arrays.stream(splitLine).mapToInt(Integer::parseInt).forEach(i -> currentItems.add((long) i));
    }

    private void parseOperation(String line) {
        String[] splitLine = line.substring(line.indexOf('=') + 2).split(" ");

        currentOperator         = MathOperator.valueOfSymbol(splitLine[1]);
        currentOperationValue   = splitLine[2].equals("old")? null : Integer.parseInt(splitLine[2]);
    }

    private void parseTestOperation(String line) {
        int value   = Integer.parseInt(line.split(" ")[5]);

        // always "divisible by" as of my current dataset.
        currentTest = new MonkeyTest(MathOperator.MODULO, value);
    }

    private int parseTestValue(String line) {
        return Integer.parseInt(String.valueOf(line.charAt(line.length() - 1)));
    }

    public List<Monkey> getMonkeys() {
        return monkeys;
    }
}
