import me.haifisch.AOC2022.AOC2022Helper;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        TreeManager manager = new TreeManager();

        AOC2022Helper.applyFunctionToDataForCurrentDay(8, manager::processLineInput);
        manager.setUpTreeGrid();

        List<Tree> visibleTrees = manager.getVisibleTreesFromOutside();

        System.out.println("Trees visible from outside: " + visibleTrees.size());

        Map<Tree, Integer> scenicScores = manager.getScenicScoresForTrees(visibleTrees);

        Integer highestScenicScore = scenicScores.values().stream().max(Integer::compareTo).orElse(null);

        System.out.println("Highest scenic score: " + highestScenicScore);
    }
}