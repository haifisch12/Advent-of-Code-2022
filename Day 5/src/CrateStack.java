import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CrateStack {

    private final List<String> crates;

    public CrateStack() {
        crates = new ArrayList<>();
    }

    public void addToStackTop(String crate) {
        crates.add(crate);
    }

    public void addToStackTop(Collection<String> cratesToAdd) {
        crates.addAll(cratesToAdd);
    }

    public void addToStackBottom(String crate) {
        crates.add(0, crate);
    }

    public List<String> takeStackTop(int count) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            result.add(takeStackTop());
        }
        Collections.reverse(result);
        return result;
    }

    // gets and removes from top
    public String takeStackTop() {
        String item = getStackTop();
        //crates.remove(item);  <---- wrong. fuck reference, takes the first occurrence. >:(
        crates.remove(crates.size() - 1); // <--- right

        return item;
    }

    public String getStackTop() {
        return crates.get(crates.size() - 1);
    }

    @Override
    public String toString() {
        return String.join(" ", crates);
    }
}
