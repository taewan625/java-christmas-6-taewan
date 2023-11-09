package christmas.model.domain;

import java.util.Map;

public class WeekendEvent {
    private final int DISCOUNT = 2023;
    private int weekendDiscount = 0;

    public int getWeekendDiscount(Map<Menu, Integer> orderMenu) {
        for (Menu menu : orderMenu.keySet()) {
            if (Menu.isMain(menu)) {
                weekendDiscount += orderMenu.get(menu) * DISCOUNT;
            }
        }
        return weekendDiscount;
    }

    public int applyWeekendDiscount(int totalPrice) {
        return totalPrice - weekendDiscount;
    }

}
