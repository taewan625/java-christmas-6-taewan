package christmas.model.domain;

import java.util.List;

public enum December {
    WEEKDAY(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)),
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    STAR_DAY(List.of(3, 10, 17, 24, 25, 31));

    private final List<Integer> days;

    December(List<Integer> days) {
        this.days = days;
    }


    public static boolean isWeekDay(int date) {
        return WEEKDAY.days.stream().anyMatch(day -> day == date);
    }


    public static boolean isWeekend(int date) {
        return WEEKEND.days.stream().anyMatch(day -> day == date);
    }

    public static boolean isStarDay(int date) {
        return STAR_DAY.days.stream().anyMatch(day -> day == date);
    }
}
