import java.util.*;

public class DirectoryManager {

    private final Directory rootDirectory;
    private final List<Directory> directories;

    private Directory currentDirectory;

    public DirectoryManager() {
        directories = new ArrayList<>();

        // add root
        rootDirectory = new Directory("dir /", null);
        directories.add(rootDirectory);
    }

    public List<Directory> getDirectoriesWithMinimumSize(int size) {
        return directories.stream().filter(d -> d.getTotalSize() >= size).toList();
    }

    public List<Directory> getDirectoriesWithMaximumSize(int size) {
        return directories.stream().filter(d -> d.getTotalSize() <= size).toList();
    }

    public Directory findSmallestDirectoryWithSize(int size) {
        return getDirectoriesWithMinimumSize(size).stream().min(Comparator.comparingInt(Directory::getTotalSize)).get();
    }

    public void processInputLine(String line) {
        switch (line.charAt(0)) {
            case '$' -> processCommand(line);
            case 'd' ->  {
                Directory newDir = new Directory(line, currentDirectory);
                currentDirectory.getDirectories().add(newDir);
                directories.add(newDir);
            }
            case ' ' -> {
                return;
            }
            default -> currentDirectory.getFiles().add(new File(line));
        }
    }

    private void processCommand(String line) {
        String[] splitCommand = line.split(" ");

        // 0 -> $
        // 1 -> cd or ls
        // 2 -> parameter
        if (splitCommand[1].equals("cd")) {

            // extra rule for root directory. a bit ugly.
            if (splitCommand[2].equals("/")) {
                currentDirectory = directories.get(0);
                return;
            }

            if (splitCommand[2].equals("..")) {
                currentDirectory = currentDirectory.getParent();
            }
            else {
                // don't need .ifPresent() because the input will always be correct... hopefully :)
                Optional<Directory> subDir = currentDirectory.getDirectories().stream().filter(d -> d.getName().equals(splitCommand[2])).findFirst();

                if (subDir.isPresent()) {
                    currentDirectory = subDir.get();
                }
                else {
                    var myException = new RuntimeException("Test");
                    myException.printStackTrace();
                    throw myException;
                }
            }
        }

    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public Directory getRootDirectory() {
        return rootDirectory;
    }
}
