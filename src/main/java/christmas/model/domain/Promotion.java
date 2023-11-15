package christmas.model.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum Promotion {
    DRINK_3("증정 이벤트", 1);
    private static final int PROMOTION_LIMIT_PRICE = 120_000;
    private final String type;
    private final int count;

    Promotion(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public static Map<Promotion, Integer> isPromotion(int totalPrice) {
        if (totalPrice >= PROMOTION_LIMIT_PRICE) {
            return Map.of(DRINK_3, DRINK_3.count);
        }
        return new HashMap<>();
    }

    public static Map<String, Integer> setPromotionDatas(Map<Promotion, Integer> promotions) {
        return promotions.entrySet().stream()
                .collect(Collectors.toMap(
                        promotion -> promotion.getKey().type,
                        promotion -> Menu.getPromotionBenefit(
                                promotion.getKey().name(),
                                promotion.getValue())
                ));
    }
}