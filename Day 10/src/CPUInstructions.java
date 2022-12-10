public enum CPUInstructions {
    noop(1),
    addx(2);


    private final int cycles;

    private CPUInstructions(int cycles) {
        this.cycles = cycles;
    }

    public int getCycles() {
        return cycles;
    }
}
