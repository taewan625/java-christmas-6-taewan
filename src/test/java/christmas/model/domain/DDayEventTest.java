package christmas.model.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DDayTest {
    DDay DDay = new DDay();

    @Test
    void getDurationDiscount() {
        int reservationDate = 1;
        int durationDiscount = DDay.getDurationDiscount(reservationDate);
        assertThat(durationDiscount).isEqualTo(1000);
    }

    @Test
    void isDurationEvent() {
        int reservationDate1 = 1;
        int reservationDate2 = 25;
        int reservationDate3 = 26;

        boolean durationEvent1 = DDay.isDurationEvent(reservationDate1);
        boolean durationEvent2 = DDay.isDurationEvent(reservationDate2);
        boolean durationEvent3 = DDay.isDurationEvent(reservationDate3);
        assertThat(durationEvent1).isTrue();
        assertThat(durationEvent2).isTrue();
        assertThat(durationEvent3).isFalse();
    }

    @Test
    void applyDurationDiscount() {
        int totalPrice = 11000;
        int date = 3;
        int durationDiscount = DDay.getDurationDiscount(date);
        int discountPrice = DDay.applyDurationDiscount(totalPrice);
        assertThat(discountPrice).isEqualTo(9800);
    }
}