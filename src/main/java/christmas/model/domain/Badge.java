package christmas.model.domain;

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

    public static String getBadge(int eventPoint) {
        String badgeName = "없음";
        for (Badge value : Badge.values()) {
            if (value.eventPoint <= eventPoint) {
                badgeName = value.name;
            }
        }
        return badgeName;
    }
}
