package christmas.model.domain;

import java.util.HashMap;
import java.util.Map;

public class WeekDayEvent {
    private final String WEEKDAY = "평일 할인";
    private final int DISCOUNT = 2023;

    public Map<String, Integer> getWeekdayDiscount(Map<Menu, Integer> orderMenu) {
        int weekDayDiscount = 0;
        for (Menu menu : orderMenu.keySet()) {
            weekDayDiscount += orderMenu.get(menu) * DISCOUNT;
        }
        return Map.of(WEEKDAY, weekDayDiscount);
    }
}
