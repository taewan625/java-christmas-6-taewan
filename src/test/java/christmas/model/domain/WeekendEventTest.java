package christmas.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class WeekendEventTest {
    WeekendEvent weekendEvent = new WeekendEvent();
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
    @DisplayName("main 만 적용 된다.")
    void getWeekendDiscount() {
        int weekendDiscount = weekendEvent.getWeekendDiscount(orderMenu);
        Assertions.assertThat(weekendDiscount).isEqualTo(2023 * 3);
    }

    @Test
    void applyWeekendDiscount() {
        int totalPrice = 200_000;
        int weekdayDiscount = weekendEvent.getWeekendDiscount(orderMenu);
        int discountedAmount = weekendEvent.applyWeekendDiscount(totalPrice);
        Assertions.assertThat(discountedAmount).isEqualTo(totalPrice-weekdayDiscount);
    }
}