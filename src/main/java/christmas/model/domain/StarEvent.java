package christmas.model.domain;

public class StarEvent {
    private final int DISCOUNT = 1000;

    public int applyStarDayDiscount(int totalPrice) {
        return totalPrice - DISCOUNT;
    }

}
