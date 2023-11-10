package christmas.model.domain;

public enum Badge {
    STAR(5_000),
    TREE(10_000),
    SANTA(20_000);

    private static final int MIN_EVENT_POINT = 5000;

    private final int eventPoint;

    Badge(int eventPoint) {
        this.eventPoint = eventPoint;
    }

    public static boolean isBadge(int eventPoint) {
        return eventPoint >= MIN_EVENT_POINT;
    }

    public static Badge getBadge(int eventPoint) {
        Badge getBadge = null;
        for (Badge value : Badge.values()) {
            if (value.eventPoint <= eventPoint) {
                getBadge = value;
            }
        }
        return getBadge;
    }
}
