package christmas.model.domain;

import java.util.Map;

public enum Promotion {
    DRINK_3("증정 이벤트", 1);
    private static final int PROMOTION_LIMIT_PRICE = 120_000;
    private final String type;
    private final int count;

    Promotion(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public static boolean isPromotion(int totalPrice) {
        return totalPrice >= PROMOTION_LIMIT_PRICE;
    }

    public static Map<String, Integer> getPromotion() {
        return Map.of(DRINK_3.name(), DRINK_3.count);
    }
}
