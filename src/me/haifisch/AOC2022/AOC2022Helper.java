package me.haifisch.AOC2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public final class AOC2022Helper<T> {
    public AOC2022Helper() { }

    public static Path getDataPath(String filename) {
        return Path.of(Paths.get("").toAbsolutePath() + "\\assets\\" + filename);
    }

    public List<T> getDataForCurrentDay(int currentDay, Function<String, T> myFunc) {
        return getDataForCurrentDay(String.format("data_day%d.txt", currentDay), myFunc);
    }

    public List<T> getDataForCurrentDay(String fileName, Function<String, T> myFunc) {
        List<T> result = new ArrayList<>();

        try {
            Files.lines(getDataPath(fileName)).forEach(l -> {
                result.add(myFunc.apply(l));
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }
}
