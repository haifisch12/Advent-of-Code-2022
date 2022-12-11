import me.haifisch.AOC2022.AOC2022Console;
import me.haifisch.AOC2022.ConsoleColors;

public class MonkeyTest {
    private final String TAB    = "   ";

    private final MathOperator  operator;

    private final int           operationValue;
    private int                 testPositiveId;
    private int                 testNegativeId;

    public MonkeyTest(MathOperator operator, int operationValue) {
        this.operator           = operator;
        this.operationValue     = operationValue;
        this.testPositiveId     = 0;
        this.testNegativeId     = 0;
    }

    public int getTestResult(long value) {
        boolean testResult  = operator.applyAsDouble(value, operationValue) == 0;
        int     resultValue = testResult? testPositiveId : testNegativeId;

        AOC2022Console.printColorf(TAB + TAB + "Current worry level is%s divisible by %d.%n",
                testResult? ConsoleColors.Green : ConsoleColors.Red, testResult? "" : " NOT", resultValue);

        return resultValue;
    }

    public void setTestPositiveId(int testPositiveId) {
        this.testPositiveId = testPositiveId;
    }

    public void setTestNegativeId(int testNegativeId) {
        this.testNegativeId = testNegativeId;
    }

    public int getOperationValue() {
        return operationValue;
    }
}
