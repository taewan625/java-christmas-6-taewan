package christmas.model.domain.deplicated;

import java.util.Map;

public class StarEvent {
    private static final String STAR_DAY = "특별 할인";
    private final int DISCOUNT = 1000;

    public Map<String, Integer> getStarDayDiscount() {
        return Map.of(STAR_DAY, DISCOUNT);
    }
}
