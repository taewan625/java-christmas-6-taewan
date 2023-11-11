package christmas.model.domain;

import java.util.Map;

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

    public static Map<String, Integer> applyDiscount(String discountType, int date, Map<Menu, Integer> orderMenu) {
        Discount discount = Discount.valueOf(discountType);
        if (discount == D_DAY) {
            return Discount.dDayDiscount(date, discount);
        }
        if (discount == WEEKDAY) {
            return Discount.mainDishDiscount(orderMenu, discount);
        }
        if (discount == WEEKEND) {
            return Discount.dessertDiscount(orderMenu, discount);
        }
        return Discount.starDayDiscount(discount);
    }

    public static Map<String, Integer> dDayDiscount(int date, Discount discount) {
        int dDayDiscount = discount.discount + ((date - 1) * INCREASE_PRICE);
        return Map.of(discount.type, dDayDiscount);
    }

    public static Map<String, Integer> mainDishDiscount(Map<Menu, Integer> orderMenu, Discount discount) {
        int mainDishDiscount = 0;
        for (Menu menu : orderMenu.keySet()) {
            if (Menu.isMain(menu)) {
                mainDishDiscount += orderMenu.get(menu) * discount.discount;
            }
        }
        return Map.of(discount.type, mainDishDiscount);
    }

    public static Map<String, Integer> dessertDiscount(Map<Menu, Integer> orderMenu, Discount discount) {
        int dessertDiscount = 0;
        for (Menu menu : orderMenu.keySet()) {
            if (Menu.isDessert(menu)) {
                dessertDiscount += orderMenu.get(menu) * discount.discount;
            }
        }
        return Map.of(discount.type, dessertDiscount);
    }

    public static Map<String, Integer> starDayDiscount(Discount discount) {
        return Map.of(discount.type, discount.discount);
    }

}
