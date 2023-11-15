package christmas.model.domain;

import christmas.util.XmasConverter;

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

    public boolean isEventApplicable() {
        return totalOrderPrice >= EVENT_MIN_PRICE;
    }

    public Map<String, Integer> getDiscountDatas() {
        return Month.applyDiscountByDate(reservationDate).stream()
                .map(discount -> Discount.applyDiscount(discount, reservationDate, orderMenus))
                .flatMap(discountData -> discountData.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Promotion, Integer> getPromotionData() {
        return Promotion.isPromotion(totalOrderPrice);
    }

    public String getPredictPay(CustomerEvent customerEvent) {
        int predictPay = customerEvent.getPredictPay(totalOrderPrice);
        return XmasConverter.toWon(predictPay);
    }
}
