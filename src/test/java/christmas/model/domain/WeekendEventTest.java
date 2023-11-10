package christmas.model.domain;

import christmas.model.domain.deplicated.WeekendEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendEventTest {
    WeekendEvent weekendEvent = new WeekendEvent();
    Map<Menu, Integer> orderMenu = new HashMap<>();

    @BeforeEach
    void before() {
        orderMenu.put(Menu.MAIN_4, 3);
    }

    @AfterEach
    void after() {
        orderMenu = null;
    }
    @Test
    @DisplayName("main 만 적용 된다.")
    void getWeekendDiscount() {
        Map<String, Integer> weekendDiscount = weekendEvent.getWeekendDiscount(orderMenu);
        Collection<Integer> values = weekendDiscount.values();

        assertThat(values.contains(2023 * 3)).isTrue();
    }
}