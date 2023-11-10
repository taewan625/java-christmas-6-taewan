package christmas.model.domain.deplicated;

import christmas.model.domain.Menu;

import java.util.Map;

public class WeekendEvent {
    private final String WEEKEND = "주말 할인";
    private final int DISCOUNT = 2023;

    public Map<String, Integer> getWeekendDiscount(Map<Menu, Integer> orderMenu) {
         int weekendDiscount = 0;
        for (Menu menu : orderMenu.keySet()) {
            weekendDiscount += orderMenu.get(menu) * DISCOUNT;
        }
        return Map.of(WEEKEND, weekendDiscount);
    }


}
