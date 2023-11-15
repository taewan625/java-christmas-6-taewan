package christmas.model.domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.model.domain.Promotion.DRINK_3;
import static christmas.model.domain.Promotion.isPromotion;
import static org.assertj.core.api.Assertions.assertThat;

class PromotionTest {

    @Test
    void isPromotionEvent() {
        assertThat(isPromotion(120_000)).isEqualTo(Map.of(DRINK_3, 1));
        assertThat(isPromotion(240_000)).isEqualTo(Map.of(DRINK_3, 1));
        assertThat(isPromotion(119_999)).isEmpty();
        assertThat(isPromotion(100_000)).isEmpty();
    }

    @Test
    void setPromotionDatas() {
        assertThat(Promotion.setPromotionDatas(Map.of(DRINK_3, 1)))
                .isEqualTo(Map.of("증정 이벤트", 25000));
    }
}