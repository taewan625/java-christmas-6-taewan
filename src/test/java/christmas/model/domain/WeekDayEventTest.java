package christmas.model.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekDayEventTest {
    WeekDayEvent weekDayEvent = new WeekDayEvent();
    Map<Menu, Integer> orderMenu = new HashMap<>();

    @BeforeEach
    void before() {
        orderMenu.put(Menu.DESSERT_1, 2);
    }

    @AfterEach
    void after() {
        orderMenu = null;
    }

    @Test
    @DisplayName("평일은 디저트만 할인이 된다.")
    void getWeekdayDiscount() {
        Map<String, Integer> weekdayDiscount = weekDayEvent.getWeekdayDiscount(orderMenu);
        Collection<Integer> values = weekdayDiscount.values();

        assertThat(values.contains(2023 * 2)).isTrue();
    }


}