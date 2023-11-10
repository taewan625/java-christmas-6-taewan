package christmas.model.domain;

import java.util.Map;

public class PromotionEvent {
    private final String PROMOTION = "샴페인 1개";
    private final int PROMOTION_PRICE = 25_000;
    private final int PROMOTION_LIMIT_PRICE = 120_000;

    public boolean isPromotionEvent(int totalPrice) {
        return totalPrice >= PROMOTION_LIMIT_PRICE;
    }

    public Map<String, Integer> getPromotion() {
        return Map.of(PROMOTION, PROMOTION_PRICE);
    }
}
