import me.haifisch.AOC2022.AOC2022Helper;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DirectoryManager manager = new DirectoryManager();

        AOC2022Helper.applyFunctionToDataForCurrentDay(7, manager::processInputLine);


        System.out.println("Dir count: " + manager.getDirectories().size());
        System.out.println("Dir with max 100.000: " + manager.getDirectoriesWithMaximumSize(100000).size());
        System.out.println("Dir with max 100.000 sum: " + manager.getDirectoriesWithMaximumSize(100000).stream().mapToInt(Directory::getTotalSize).sum());

        // 70000000 -> total disk space available to the filesystem
        // 30000000 -> update size
        int needed = 30000000 - (70000000 - manager.getRootDirectory().getTotalSize());

        Directory directoryToDelete = manager.findSmallestDirectoryWithSize(needed);

        System.out.println("Dir to delete: " + directoryToDelete.getName());
        System.out.println("Dir to delete size: " + directoryToDelete.getTotalSize());
    }
}