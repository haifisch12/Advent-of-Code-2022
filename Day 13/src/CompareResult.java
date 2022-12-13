public enum CompareResult {
    INORDER(true),
    NOTINORDER(false),
    EQUAL(false);

    private final boolean value;

    CompareResult(boolean value) {
        this.value = value;
    }

    public boolean getBooleanValue() {
        return value;
    }
}
