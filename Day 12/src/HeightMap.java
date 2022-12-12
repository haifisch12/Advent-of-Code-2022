import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HeightMap {

    private int                     currentRow;
    private final Map<String, Node> nodes;


    public HeightMap() {
        currentRow  = 0;
        nodes       = new HashMap<>();
    }

    public void processInputLine(String line) {
        for (int i = 0; i < line.length(); i++) {
            nodes.put(getStringFromRowAndCol(currentRow, i), new Node(currentRow, i, line.charAt(i)));
        }

        currentRow++;
    }

    private String getStringFromRowAndCol(int row, int col) {
        // used to create/get key from dictionary.
        // cuz dictionary do be performance king.
        return row + "," + col;
    }

    private Node getNodeAt(int row, int col) {
        return nodes.get(getStringFromRowAndCol(row, col));
    }

    private List<Node> getValidNextNodes(Node node) {
        List<Node> result = new ArrayList<>();

        result.add(getNodeAt(node.getRow() + 1, node.getCol()));
        result.add(getNodeAt(node.getRow() - 1, node.getCol()));
        result.add(getNodeAt(node.getRow(), node.getCol() + 1));
        result.add(getNodeAt(node.getRow(), node.getCol() - 1));

        return result.stream().filter(Objects::nonNull).filter(n -> node.getHeight() - n.getHeight() >= -1).toList();
    }

    public Node getStart() {
        return nodes.values().stream().filter(Node::isStart).findFirst().orElseThrow();
    }

    public Path findShortestPath(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        Map<Node, Node> previous = new HashMap<>();

        Set<Node>       visited  = new HashSet<>();
        visited.add(start);

        while (queue.size() > 0) {
            Node current = queue.poll();

            for (Node node : getValidNextNodes(current).stream().filter(n -> !visited.contains(n)).toList()) {
                if (node.isEnd()) {
                    return reconstructPath(start, node, current, previous);
                }
                else {
                    visited.add(node);
                    queue.add(node);
                    previous.put(node, current);
                }
            }
        }

        return null;
    }

    public Path findShortestPathForEveryA(boolean printIterations) {
        List<Path> paths = new ArrayList<>();

        AtomicInteger count     = new AtomicInteger();
        List<Node> startPoints  = nodes.values().stream().filter(n -> n.getHeight() == (int)'a').toList();

        startPoints.forEach(n ->  {
            count.getAndIncrement();

            if (printIterations)
                System.out.println("Part 2: Iteration: " + count.get() + " / " + startPoints.size());

            Path result = findShortestPath(n);
            if (result != null)
                paths.add(result);
        });

        return paths.stream().min(Comparator.comparingInt(Path::getPathStepSize)).get();
    }

    private Path reconstructPath(Node start, Node endNode, Node current, Map<Node, Node> previous) {
        Path newPath = new Path(endNode);
        newPath.addNode(current);

        do {
            current = previous.get(current);
            newPath.addNode(current);
        } while (current != start);

        return newPath;
    }
}
