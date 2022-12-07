public class File {
    private final String name;
    private final int    size;

    public File(String line) {
        // example: "100347 rrrj.wzl"
        String[] splitLine = line.split(" ");

        size = Integer.parseInt(splitLine[0]);
        name = splitLine[1];
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
