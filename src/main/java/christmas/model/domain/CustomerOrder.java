package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerOrder {
    private final int reservationDate;
    private final Map<Menu, Integer> orderMenus;
    private final int totalOrderPrice;
    private final int EVENT_MIN_PRICE = 10_000;

    public CustomerOrder(int reservationDate, Map<Menu, Integer> orderMenus) {
        this.reservationDate = reservationDate;
        this.orderMenus = orderMenus;
        this.totalOrderPrice = totalOrderPrice();
    }

    public String getReservationFullDate() {
        return XmasConverter.dateToFullDate(reservationDate);
    }

    public List<String> getOrderMenus() {
        return Menu.getOrderMenus(orderMenus);
    }

    public Map<String, Integer> getDiscountDatas() {
        return Month.applyDiscountByDate(reservationDate).stream()
                .map(discount -> Discount.applyDiscount(discount, reservationDate, orderMenus))
                .flatMap(discountData -> discountData.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Promotion> getPromotionData() {
        if (Promotion.isPromotion(totalOrderPrice)) {
            return List.of(Promotion.DRINK_3);
        }
        return new ArrayList<>();
    }

    private int totalOrderPrice() {
        return Menu.getTotalOrderPrice(orderMenus);
    }
    public String getTotalOrderPrice() {
        return XmasConverter.toWon(totalOrderPrice);
    }

    public String getPredictPay(CustomerEvent customerEvent) {
        int predictPay = customerEvent.getPredictPay(totalOrderPrice);
        return XmasConverter.toWon(predictPay);
    }

    public boolean isEventApplicable() {
        return totalOrderPrice >= EVENT_MIN_PRICE;
    }
}
