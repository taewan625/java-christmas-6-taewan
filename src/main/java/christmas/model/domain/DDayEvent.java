package christmas.model.domain;

import java.util.Map;

public class DDayEvent {
    private final String D_DAY = "크리스마스 디데이 할인";
    private final int INIT_DISCOUNT = 1000;
    private final int INCREASE_PRICE = 100;


    public Map<String, Integer> getDDayDiscount(int date) {
        int dDayDiscount = INIT_DISCOUNT + ((date - 1) * INCREASE_PRICE);
        return Map.of(D_DAY, dDayDiscount);
    }
}
