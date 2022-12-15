import java.awt.*;
import java.util.*;
import java.util.List;

public class Grid {

    private final Map<Point, PositionType> positions;

    private final Map<Point, Integer>      allSensorRanges;

    public Grid() {
        positions       = new HashMap<>();

        allSensorRanges = new HashMap<>();
    }

    public void processLine(String line) {
        String[] splitString = line.split(":");

        processPoint(splitString[0], PositionType.Sensor);
        processPoint(splitString[1], PositionType.Beacon);
    }

    private void processPoint(String line, PositionType type) {
        String[] splitString = line.split(",");

        int x = Integer.parseInt(splitString[0].substring(splitString[0].indexOf("x") + 2));
        int y = Integer.parseInt(splitString[1].substring(splitString[1].indexOf("y") + 2));

        positions.put(new Point(x, y), type);
    }

    private PositionType getTypeOnPosition(int x, int y) {
        PositionType result = positions.get(new Point(x, y));

        return result == null? PositionType.Undetermined : result;
    }

    public List<Point> getPositionOfType(PositionType type) {
        List<Point> result = new ArrayList<>();

        for (Map.Entry<Point, PositionType> kvp : positions.entrySet()) {
            if (kvp.getValue().equals(type))
                result.add(kvp.getKey());
        }

        return result;
    }

    public void calculateSensorRanges() {
        getPositionOfType(PositionType.Sensor).forEach(s -> allSensorRanges.put(s, getDistanceToNearestBeacon(s)));
    }

    public void calculateNoBeaconSpaces(int yLevel) {
        getPositionOfType(PositionType.Sensor).forEach(sensor -> {
            int distance = allSensorRanges.get(sensor);

            for (int y = 0; y <= distance; y++) {
                boolean isMinus = sensor.y - y == yLevel;
                boolean isPlus  = sensor.y + y == yLevel;

                if (!isMinus && !isPlus) continue;

                for (int x = 0; x <= distance; x++) {
                    if (x + y > distance) continue;

                    if (isMinus) {
                        setNotBeacon(sensor.x - x, sensor.y - y);
                        setNotBeacon(sensor.x + x, sensor.y - y);
                    }
                    else {
                        setNotBeacon(sensor.x + x, sensor.y + y);
                        setNotBeacon(sensor.x - x, sensor.y + y);
                    }
                }
            }
        });
    }

    private void setNotBeacon(int x, int y) {
        if (getTypeOnPosition(x, y).equals(PositionType.Undetermined)) {
            positions.putIfAbsent(new Point(x, y), PositionType.NoBeacon);
        }
    }

    private int getDistanceToNearestBeacon(Point sensor) {
        int minDistance = Integer.MAX_VALUE;

        for (Point beacon : getPositionOfType(PositionType.Beacon)) {
            minDistance = Math.min(minDistance, calculateDistanceBetweenPoints(sensor, beacon));
        }

        return minDistance;
    }

    private int calculateDistanceBetweenPoints(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public int getSumNoBeaconAtY(int y) {
        List<Point> valid = new ArrayList<>();

        for (Map.Entry<Point, PositionType> kvp : positions.entrySet()) {
            if ((kvp.getValue().equals(PositionType.NoBeacon) || kvp.getValue().equals(PositionType.Sensor)) && kvp.getKey().y == y) {
                valid.add(kvp.getKey());
            }
        }

        return valid.size();
    }

    public Point getPointOfDistressBeacon(int lowerLimit, int higherLimit, boolean print) {
        for (int y = lowerLimit; y < higherLimit; y++) {

            if (print)
                System.out.println("Big iteration: " + y + " / " + higherLimit);

            for (int x = lowerLimit; x < higherLimit; x++) {
                Map.Entry<Point, Integer> range = isPointInRangeOfAnySensor(new Point(x, y));

                if (range == null)
                    return new Point(x, y);
                else {
                    // skip next "few" (thousand) cells, if already in range of a sensor.
                    x = range.getKey().x + range.getValue() - Math.abs(range.getKey().y - y);
                }
            }
        }
        return new Point(0, 0);
    }



    private Map.Entry<Point, Integer> isPointInRangeOfAnySensor(Point point) {
        // Map<Point, Integer> - Integer == Range
        for (Map.Entry<Point, Integer> kvp : allSensorRanges.entrySet()) {
            if (calculateDistanceBetweenPoints(point, kvp.getKey()) <= kvp.getValue())
                return kvp;
        }

        return null;
    }

    public void print(int xMin, int xMax, int yMin, int yMax) {
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                PositionType current = getTypeOnPosition(x, y);

                switch (current) {
                    case Beacon         -> System.out.print("B");
                    case Sensor         -> System.out.print("S");
                    case NoBeacon       -> System.out.print("#");
                    case Undetermined   -> System.out.print(".");
                }
            }

            System.out.println();
        }
    }
}
