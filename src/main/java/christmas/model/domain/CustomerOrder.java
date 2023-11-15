package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.List;
import java.util.Map;

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

    // 초기화
    private int totalOrderPrice() {
        return Menu.getTotalOrderPrice(orderMenus);
    }

    // 사용
    public String getReservationFullDate() {
        return XmasConverter.dateToFullDate(reservationDate);
    }

    public List<String> getOrderMenus() {
        return XmasConverter.menuData(Menu.getOrderMenus(orderMenus));
    }

    public String getTotalOrderPrice() {
        return XmasConverter.toWon(totalOrderPrice);
    }

    public CustomerEvent isEventApplicable() {
        if (totalOrderPrice >= EVENT_MIN_PRICE) {
            return new CustomerEvent(getDiscountDatas(), getPromotionData());
        }
        return new CustomerEvent();
    }

    private Map<String, Integer> getDiscountDatas() {
        return Discount.getDiscounts(reservationDate, orderMenus);
    }

    private Map<Promotion, Integer> getPromotionData() {
        return Promotion.isPromotion(totalOrderPrice);
    }

    public String getPredictPay(CustomerEvent customerEvent) {
        int predictPay = customerEvent.getPredictPay(totalOrderPrice);
        return XmasConverter.toWon(predictPay);
    }
}
