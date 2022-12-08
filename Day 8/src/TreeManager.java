import java.util.*;

public class TreeManager {

    private final List<Tree[]> treeGrid;

    public TreeManager() {
        treeGrid = new ArrayList<>();
    }

    public void processLineInput(String line) {
        treeGrid.add(Arrays.stream(line.split("")).mapToInt(Integer::parseInt).mapToObj(Tree::new).toArray(Tree[]::new));
    }

    public void setUpTreeGrid() {
        for (int i = 0; i < treeGrid.size(); i++) {
            for (int j = 0; j < treeGrid.get(i).length; j++) {
                Tree currentTree = treeGrid.get(i)[j];

                currentTree.setTopTree(getTreeOrNull(i - 1, j));
                currentTree.setBottomTree(getTreeOrNull(i + 1, j));
                currentTree.setLeftTree(getTreeOrNull(i, j - 1));
                currentTree.setRightTree(getTreeOrNull(i, j + 1));
            }
        }
    }

    private Tree getTreeOrNull(int rowIndex, int columnIndex) {

        if (rowIndex < 0 || columnIndex < 0 || rowIndex > treeGrid.size() - 1 || columnIndex > treeGrid.get(rowIndex).length - 1)
            return null;
        else
            return getTree(rowIndex, columnIndex);
    }

    public Tree getTree(int rowIndex, int columnIndex) {
        return treeGrid.get(rowIndex)[columnIndex];
    }

    public List<Tree> getVisibleTreesFromOutside() {
        List<Tree> result = new ArrayList<>();

        for (Tree[] treeRow : treeGrid) {
            for (Tree tree : treeRow) {
                for (Directions direction : Directions.values()) {
                    if (tree.isVisibleFromDirection(direction)) {
                        result.add(tree);
                        break;
                    }
                }
            }
        }

        return result;
    }

    public Map<Tree, Integer> getScenicScoresForTrees(List<Tree> base) {
        Map<Tree, Integer> result = new HashMap<>();

        base.forEach(tree -> {
            int score = 1;

            for (Directions direction : Directions.values()) {
                score *= tree.getViewingDistance(direction);
            }

            result.put(tree, score);
        });

        return result;
    }

}

