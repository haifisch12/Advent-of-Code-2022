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

    public static Path getDataPathForCurrentDay(int currentDay) {
        return Path.of(Paths.get("").toAbsolutePath() + String.format("\\assets\\data_day%d.txt", currentDay));
    }

    public List<T> getDataForCurrentDay(int currentDay, Function<String, T> myFunc) {
        List<T> result = new ArrayList<>();

        try {
            Files.lines(getDataPathForCurrentDay(currentDay)).forEach(l -> {
                result.add(myFunc.apply(l));
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }
}
