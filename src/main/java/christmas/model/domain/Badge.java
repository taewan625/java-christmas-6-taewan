package christmas.model.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    STAR(5_000, "별"),
    TREE(10_000, "트리"),
    SANTA(20_000, "산타");

    private static final int MIN_EVENT_POINT = 5000;

    private final int eventPoint;
    private final String name;

    Badge(int eventPoint, String name) {
        this.eventPoint = eventPoint;
        this.name = name;
    }

    public static boolean isBadge(int eventPoint) {
        return eventPoint >= MIN_EVENT_POINT;
    }

    public static String getEventBadgeName(int eventPoint) {
        return Arrays.stream(values())
                .filter(badge -> badge.eventPoint <= eventPoint)
                .max(Comparator.comparingInt(badge -> badge.eventPoint))
                .map(badge -> badge.name)
                .orElse("");
    }
}
