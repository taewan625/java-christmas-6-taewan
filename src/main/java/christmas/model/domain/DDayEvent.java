package christmas.model.domain;

public class DDay {
    private final String DURATION = "크리스마스 디데이 할인";
    private final int INIT_DISCOUNT = 1000;
    private final int INCREASE_PRICE = 100;
    private final int START_DATE = 1;
    private final int FINISH_DATE = 25;

    private int durationDiscount = 0;

    public int getDurationDiscount(int date) {
        durationDiscount = INIT_DISCOUNT + ((date - 1) * INCREASE_PRICE);
        return durationDiscount;
    }

    public boolean isDurationEvent(int date) {
        return START_DATE <= date && date <= FINISH_DATE;
    }

    public int applyDurationDiscount(int totalPrice) {
        return totalPrice - durationDiscount;
    }
}
