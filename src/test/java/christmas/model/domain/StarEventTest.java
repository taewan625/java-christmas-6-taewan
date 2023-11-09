package christmas.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StarEventTest {

    @Test
    void applyStarDayDiscount() {
        StarEvent starEvent = new StarEvent();
        int totalPrice = 10000;
        int discountedAmount = starEvent.applyStarDayDiscount(totalPrice);
        Assertions.assertThat(discountedAmount).isEqualTo(9000);

    }
}