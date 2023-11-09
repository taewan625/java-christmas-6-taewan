package christmas.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PromotionEventTest {

    @Test
    void isPromotionEvent() {
        PromotionEvent promotionEvent = new PromotionEvent();
        Assertions.assertThat(promotionEvent.isPromotionEvent(120_000)).isTrue();
        Assertions.assertThat(promotionEvent.isPromotionEvent(240_000)).isTrue();
        Assertions.assertThat(promotionEvent.isPromotionEvent(119_999)).isFalse();
        Assertions.assertThat(promotionEvent.isPromotionEvent(100_000)).isFalse();
    }
}