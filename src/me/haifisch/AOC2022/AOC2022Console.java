package me.haifisch.AOC2022;

public final class AOC2022Console {

    private AOC2022Console() { }

    public static void printColor(String text, ConsoleColors color) {
        System.out.print(addColorToText(text, color));
    }

    public static void printColorln(String text, ConsoleColors color) {
        printColorf(text + "%n", color, color);
    }

    public static void printColorf(String text, ConsoleColors color, Object... args) {
        printColor(String.format(text, args), color);
    }

    private static String addColorToText(String text, ConsoleColors color) {
        return color.getAnsiCode() + text;
    }
}
