public class Instruction {
    private final CPUInstructions instruction;
    private final Integer         argument;

    public Instruction(String line) {
        String[] splitLine = line.split(" ");

        // examples:
        // addx 15
        // noop
        // addx -3
        instruction = CPUInstructions.valueOf(splitLine[0]);

        if (!instruction.equals(CPUInstructions.noop))
            argument    = Integer.parseInt(splitLine[1]);
        else
            argument    = null;
    }

    public CPUInstructions getInstruction() {
        return instruction;
    }

    public Integer getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return getInstruction() + (getArgument() != null? " " + getArgument() : "");
    }
}
