package christmas.model.domain;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DDayEventTest {
    DDayEvent DDayEvent = new DDayEvent();

    @Test
    void getDDayDiscount() {
        int reservationDate = 1;
        Map<String, Integer> dDayDiscount = DDayEvent.getDDayDiscount(reservationDate);
        Collection<Integer> values = dDayDiscount.values();
        assertThat(values.contains(1000)).isTrue();
    }
}