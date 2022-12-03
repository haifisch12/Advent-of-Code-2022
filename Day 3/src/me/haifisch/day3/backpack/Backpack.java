package me.haifisch.day3.backpack;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private String allBackpackItems;
    private List<Character> firstCompartment;
    private List<Character> secondCompartment;
    private Character       groupBadge;

    public Backpack(String line) {
        allBackpackItems  = line;

        firstCompartment  = new ArrayList<>();
        secondCompartment = new ArrayList<>();

        int middle = line.length() / 2;

        // first half
        line.substring(0, middle).chars().forEach(c -> firstCompartment.add((char) c));

        // second half
        line.substring(middle).chars().forEach(c -> secondCompartment.add((char) c));
    }

    public List<Character> getSharedChars() {
        List<Character> result = new ArrayList<>();

        firstCompartment.forEach(c -> { if (secondCompartment.contains(c) && !result.contains(c)) result.add(c); });

        return result;
    }

    public boolean containsChar(char character) {
        return allBackpackItems.contains(String.valueOf(character));
    }

    public List<Character> getFirstCompartment() {
        return firstCompartment;
    }

    public List<Character> getSecondCompartment() {
        return secondCompartment;
    }

    public String getAllBackpackItems() {
        return allBackpackItems;
    }
}
