package christmas.model.domain;

import java.util.Map;

public class WeekDayEvent {
    private final int DISCOUNT = 2023;
    private int weekDayDiscount = 0;

    public int getWeekdayDiscount(Map<Menu, Integer> orderMenu) {
        for (Menu menu : orderMenu.keySet()) {
            if (Menu.isDessert(menu)) {
                weekDayDiscount += orderMenu.get(menu) * DISCOUNT;
            }
        }
        return weekDayDiscount;
    }

    public int applyWeekdayDiscount(int totalPrice) {
        return totalPrice - weekDayDiscount;
    }
}
