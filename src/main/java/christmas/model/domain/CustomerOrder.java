package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerOrder {
    private int reservationDate;
    private Map<Menu, Integer> orderMenus;

    public CustomerOrder(int reservationDate, Map<Menu, Integer> orderMenus) {
        this.reservationDate = reservationDate;
        this.orderMenus = orderMenus;
    }

    public String getReservationFullDate() {
        return XmasConverter.dateToFullDate(reservationDate);
    }

    public List<String> getOrderMenus() {
        return Menu.getOrderMenus(orderMenus);
    }

    public Map<String, Integer> getDiscountDatas() {
        Map<String, Integer> discountDatas = new HashMap<>();
        List<String> discounts = Month.applyDiscountByDate(reservationDate);
        for (String discount : discounts) {
            Map<String, Integer> discountData = Discount.applyDiscount(discount, reservationDate, orderMenus);
            discountDatas.putAll(discountData);
        }
        return discountDatas;
    }

    public Map<String, Integer> getPromotionData() {
        if (Promotion.isPromotion(getTotalOrderPrice())) {
            return Promotion.getPromotion();
        }
        return new HashMap<>(); // todo. 나중에 없음 같은거 고민 필요
    }

    public int getTotalOrderPrice() {
        return Menu.getTotalOrderPrice(orderMenus);
    }
}
