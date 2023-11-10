package christmas.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Month {
    D_DAY(IntStream.rangeClosed(1, 25).boxed().collect(Collectors.toList())),
    WEEKDAY(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)),
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    STAR_DAY(List.of(3, 10, 17, 24, 25, 31));

    private final List<Integer> days;

    Month(List<Integer> days) {
        this.days = days;
    }

    public static List<String> applyDiscountByDate(int date) {
        List<String> applyDiscountTypes = new ArrayList<>();
        for(Month discountType : Month.values()) {
            if (isDiscountDate(date, discountType)){
                applyDiscountTypes.add(discountType.name());
            }
        }
        return applyDiscountTypes;
    }
    public static boolean isDiscountDate(int date, Month discountType) {
        return discountType.days.stream().anyMatch(day -> day == date);
    }

    public static boolean isDDay(int date) {
        return D_DAY.days.stream().anyMatch(day -> day == date);
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
