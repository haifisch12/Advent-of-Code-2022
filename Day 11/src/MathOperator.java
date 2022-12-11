import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

enum MathOperator implements DoubleBinaryOperator {
    PLUS("+",       Double::sum),
    MINUS("-",      (a, b) -> a - b),
    MULTIPLY("*",   (a, b) -> a * b),
    DIVIDE("/",     (a, b) -> a / b),
    MODULO("%",     (a, b) -> a % b);

    private final String symbol;
    private final DoubleBinaryOperator binaryOperator;

    MathOperator(String symbol, DoubleBinaryOperator binaryOperator) {
        this.symbol         = symbol;
        this.binaryOperator = binaryOperator;
    }

    public String getSymbol() {
        return symbol;
    }

    public static MathOperator valueOfSymbol(String symbol) {
        return Arrays.stream(MathOperator.values()).filter(o -> o.getSymbol().equals(symbol)).findFirst().orElse(null);
    }

    @Override
    public double applyAsDouble(double left, double right) {
        return binaryOperator.applyAsDouble(left, right);
    }
}
