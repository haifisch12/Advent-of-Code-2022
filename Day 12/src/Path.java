import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Path {
    private final List<Node> path;

    public Path(Node startNode) {
        path = new ArrayList<>() {{add(startNode);}};
    }

    public void addNode(Node node) {
        path.add(node);
    }

    public int getPathStepSize() {
        // - 1 because the last entry does not require a step forward.
        return path.size() - 1;
    }

    @Override
    public String toString() {
        return path.stream().map(Node::toString).collect(Collectors.joining());
    }
}
