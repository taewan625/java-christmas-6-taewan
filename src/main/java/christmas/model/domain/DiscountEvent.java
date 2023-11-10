package christmas.model.domain;

import java.util.Map;

public enum DiscountEvent {
    D_DAY("크리스마스 디데이 할인", 1000),
    WEEKDAY("평일 할인", 2023),
    WEEKEND("주말 할인", 2023),
    STAR_DAY("특별 할인", 1000);

    private static final int INCREASE_PRICE = 100;
    private final String type;
    private final int discount;

    DiscountEvent(String type, int discount) {
        this.type = type;
        this.discount = discount;
    }

    public static DiscountEvent getDiscountEvent(String discountType) {
        return DiscountEvent.valueOf(discountType);
    }

    public static Map<String, Integer> chooseDiscount(DiscountEvent discountEvent, int date,
                                                      Map<Menu, Integer> orderMenu) {
        if (discountEvent == D_DAY) {
            return DiscountEvent.dDayDiscount(date, discountEvent);
        }
        if (discountEvent == WEEKDAY) {
            return DiscountEvent.mainDishDiscount(orderMenu, discountEvent);
        }
        if (discountEvent == WEEKEND) {
            return DiscountEvent.dessertDiscount(orderMenu, discountEvent);
        }
        return DiscountEvent.starDayDiscount(discountEvent);
    }

    public static Map<String, Integer> dDayDiscount(int date, DiscountEvent discountEvent) {
        int dDayDiscount = discountEvent.discount + ((date - 1) * INCREASE_PRICE);
        return Map.of(discountEvent.type, dDayDiscount);
    }

    public static Map<String, Integer> mainDishDiscount(Map<Menu, Integer> orderMenu, DiscountEvent discountEvent) {
        int mainDishDiscount = 0;
        for (Menu menu : orderMenu.keySet()) {
            if (Menu.isMain(menu)) {
                mainDishDiscount += orderMenu.get(menu) * discountEvent.discount;
            }
        }
        return Map.of(discountEvent.type, mainDishDiscount);
    }

    public static Map<String, Integer> dessertDiscount(Map<Menu, Integer> orderMenu, DiscountEvent discountEvent) {
        int dessertDiscount = 0;
        for (Menu menu : orderMenu.keySet()) {
            if (Menu.isDessert(menu)) {
                dessertDiscount += orderMenu.get(menu) * discountEvent.discount;
            }
        }
        return Map.of(discountEvent.type, dessertDiscount);
    }

    public static Map<String, Integer> starDayDiscount(DiscountEvent discountEvent) {
        return Map.of(discountEvent.type, discountEvent.discount);
    }

}
