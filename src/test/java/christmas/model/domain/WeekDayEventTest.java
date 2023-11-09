package christmas.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class WeekDayEventTest {
    WeekDayEvent weekDayEvent = new WeekDayEvent();
    Map<Menu, Integer> orderMenu = new HashMap<>();

    @BeforeEach
    void before() {
        orderMenu.put(Menu.DESSERT_1, 2);
        orderMenu.put(Menu.APPETIZER_2, 4);
        orderMenu.put(Menu.MAIN_4, 3);
    }

    @AfterEach
    void after() {
        orderMenu = null;
    }

    @Test
    @DisplayName("평일은 디저트만 할인이 된다.")
    void getWeekdayDiscount() {
        int weekdayDiscount = weekDayEvent.getWeekdayDiscount(orderMenu);
        Assertions.assertThat(weekdayDiscount).isEqualTo(2023 * 2);
    }

    @Test
    void applyWeekdayDiscount() {
        int totalPrice = 200_000;
        int weekdayDiscount = weekDayEvent.getWeekdayDiscount(orderMenu);
        int discountedAmount = weekDayEvent.applyWeekdayDiscount(totalPrice);
        Assertions.assertThat(discountedAmount).isEqualTo(totalPrice-weekdayDiscount);
    }


}