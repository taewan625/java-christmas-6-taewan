package christmas.model.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum Discount {
    D_DAY("크리스마스 디데이 할인", 1000),
    WEEKDAY("평일 할인", 2023),
    WEEKEND("주말 할인", 2023),
    STAR_DAY("특별 할인", 1000);

    private static final int INCREASE_PRICE = 100;
    private final String type;
    private final int discount;

    Discount(String type, int discount) {
        this.type = type;
        this.discount = discount;
    }

    public static Map<String, Integer> getDiscounts(int reservationDate, Map<Menu, Integer> orderMenus) {
        List<String> monthTypes = Month.applyDiscountByDate(reservationDate);

        return monthTypes.stream().map(Discount::valueOf)
                .map(discountType -> applyDiscount(discountType, reservationDate, orderMenus))
                .flatMap(discountData -> discountData.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map<String, Integer> applyDiscount(Discount discountType, int date, Map<Menu, Integer> orderMenu) {
        if (discountType == D_DAY) {
            return Discount.dDayDiscount(date, discountType);
        }
        if (discountType == WEEKDAY) {
            return Discount.menuTypeDiscount(orderMenu, discountType, Menu::isMain);
        }
        if (discountType == WEEKEND) {
            return Discount.menuTypeDiscount(orderMenu, discountType, Menu::isDessert);
        }
        return Discount.starDayDiscount(discountType);
    }

    private static Map<String, Integer> dDayDiscount(int date, Discount discount) {
        int dDayDiscount = discount.discount + ((date - 1) * INCREASE_PRICE);
        return Map.of(discount.type, dDayDiscount);
    }

    private static Map<String, Integer> menuTypeDiscount(Map<Menu, Integer> orderMenu, Discount discount, Predicate<Menu> menuType) {
        int subPartDiscount = orderMenu.keySet().stream()
                .filter(menuType)
                .mapToInt(menu -> orderMenu.get(menu) * discount.discount)
                .sum();
        return Map.of(discount.type, subPartDiscount);
    }

    private static Map<String, Integer> starDayDiscount(Discount discount) {
        return Map.of(discount.type, discount.discount);
    }

}
