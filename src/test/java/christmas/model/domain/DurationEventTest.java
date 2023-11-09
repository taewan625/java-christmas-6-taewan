package christmas.model.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DurationEventTest {
    DurationEvent durationEvent = new DurationEvent();

    @Test
    void getDurationDiscount() {
        int reservationDate = 1;
        int durationDiscount = durationEvent.getDurationDiscount(reservationDate);
        assertThat(durationDiscount).isEqualTo(1000);
    }

    @Test
    void isDurationEvent() {
        int reservationDate1 = 1;
        int reservationDate2 = 25;
        int reservationDate3 = 26;

        boolean durationEvent1 = durationEvent.isDurationEvent(reservationDate1);
        boolean durationEvent2 = durationEvent.isDurationEvent(reservationDate2);
        boolean durationEvent3 = durationEvent.isDurationEvent(reservationDate3);
        assertThat(durationEvent1).isTrue();
        assertThat(durationEvent2).isTrue();
        assertThat(durationEvent3).isFalse();
    }

    @Test
    void applyDurationDiscount() {
        int totalPrice = 11000;
        int date = 3;
        int durationDiscount = durationEvent.getDurationDiscount(date);
        int discountPrice = durationEvent.applyDurationDiscount(totalPrice);
        assertThat(discountPrice).isEqualTo(9800);
    }
}