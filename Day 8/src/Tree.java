public class Tree {
    private Tree topTree;
    private Tree leftTree;
    private Tree rightTree;
    private Tree bottomTree;

    private final int  height;

    public Tree(int treeHeight) {
        height = treeHeight;
    }

    public boolean isEdgeTree() {
        return topTree == null || leftTree == null || rightTree == null || bottomTree == null;
    }

    public boolean isVisibleFromDirection(Directions direction) {
        if (isEdgeTree())
            return true;

        boolean result;
        Tree currentTree = getTreeFromDirection(direction);

        do {
            result = isEdgeTree() || getHeight() > currentTree.getHeight();
            if (result) {
                currentTree = currentTree.getTreeFromDirection(direction);
            }
            else break;
        } while (currentTree != null);

        return result;
    }

    public int getViewingDistance(Directions direction) {
        int counter = 0;

        Tree currentTree = getTreeFromDirection(direction);

        while (currentTree != null) {
            counter++;

            if (getHeight() > currentTree.getHeight()) {
                currentTree = currentTree.getTreeFromDirection(direction);
            } else {
                break;
            };
        }

        return counter;
    }

    private Tree getTreeFromDirection(Directions direction) {
        Tree result;

        switch (direction) {

            case Top -> result = getTopTree();
            case Left -> result = getLeftTree();
            case Right -> result = getRightTree();
            case Bottom -> result = getBottomTree();
            default -> throw new IllegalArgumentException("Direction " + direction + " is not defined.");
        }

        return result;
    }

    public Tree getTopTree() {
        return topTree;
    }

    public void setTopTree(Tree topTree) {
        this.topTree = topTree;
    }

    public Tree getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(Tree leftTree) {
        this.leftTree = leftTree;
    }

    public Tree getRightTree() {
        return rightTree;
    }

    public void setRightTree(Tree rightTree) {
        this.rightTree = rightTree;
    }

    public Tree getBottomTree() {
        return bottomTree;
    }

    public void setBottomTree(Tree bottomTree) {
        this.bottomTree = bottomTree;
    }

    public int getHeight() {
        return height;
    }
}
