import java.util.ArrayList;
import java.util.List;

public class Directory {

    private final Directory       parent;
    private final String          name;
    private final List<Directory> directories;
    private final List<File>      files;


    public Directory(String line, Directory parentDir) {
        parent = parentDir;
        name   = line.split(" ")[1];

        directories = new ArrayList<>();
        files       = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public List<File> getFiles() {
        return files;
    }

    public Directory getParent() {
        return parent;
    }

    public int getTotalSize() {
        return directories.stream().mapToInt(Directory::getTotalSize).sum() + files.stream().mapToInt(File::getSize).sum();
    }

    @Override
    public String toString() {
        return getName() + " - " + getTotalSize();
    }
}
