import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundStats {

    private final int roundNumber;
    Map<Monkey, List<Long>> stats;

    public RoundStats(int roundNumber, List<Monkey> allMonkeys) {
        this.roundNumber = roundNumber;
        this.stats       = new HashMap<>();

        allMonkeys.forEach(m -> stats.put(m, new ArrayList<>(m.getItems())));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Monkey, List<Long>> kvp : stats.entrySet()) {
            sb.append(kvp.getKey()).append(": ");

            kvp.getValue().forEach(v -> sb.append(v).append(", "));

            if (sb.lastIndexOf(",") >= 0)
                sb.replace(sb.lastIndexOf(","), sb.length() - 1, "");

            sb.append("%n");
        }

        return sb.toString();
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
