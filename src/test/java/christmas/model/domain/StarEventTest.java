package christmas.model.domain;

import christmas.model.domain.deplicated.StarEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

class StarEventTest {

    @Test
    void getWeekdayDiscount() {
        StarEvent starEvent = new StarEvent();
        Map<String, Integer> starDayDiscount = starEvent.getStarDayDiscount();
        Collection<Integer> values = starDayDiscount.values();
        Assertions.assertThat(values.contains(1000)).isTrue();

    }
}