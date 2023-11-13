package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.*;

public enum Menu {
    NO_MENU("없는 메뉴", "noMenu", 0),
    APPETIZER_1("양송이수프", "appetizer", 6_000),
    APPETIZER_2("타파스", "appetizer", 5_500),
    APPETIZER_3("시저샐러드", "appetizer", 8_000),
    MAIN_1("티본스테이크", "main", 55_000),
    MAIN_2("바비큐립", "main", 54_000),
    MAIN_3("해산물파스타", "main", 35_000),
    MAIN_4("크리스마스파스타", "main", 25_000),
    DESSERT_1("초코케이크", "dessert", 15_000),
    DESSERT_2("아이스크림", "dessert", 5_000),
    DRINK_1("제로콜라", "drink", 3_000),
    DRINK_2("레드와인", "drink", 60_000),
    DRINK_3("샴페인", "drink", 25_000);

    private static final String MAIN = "main";
    private static final String DESERT = "dessert";
    private static final String DRINK = "drink";

    private final String name;
    private final String type;
    private final int price;

    Menu(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public static Menu getMenu(String orderMenu) {
        return Arrays.stream(Menu.values())
                .filter(menu -> Objects.equals(menu.name, orderMenu))
                .findFirst()
                .orElse(NO_MENU);
    }

    public static String getMenuName(String promotion, int count) {
        return Menu.valueOf(promotion).name + " " + count;
    }

    public static int getMenuPrice(String promotion, int count) {
        return Menu.valueOf(promotion).price * count;
    }

    public static List<String> getOrderMenus(Map<Menu, Integer> orderMenus) {
        return orderMenus.keySet().stream()
                .map(menu -> XmasConverter.menuData(menu.name, orderMenus.get(menu)))
                .toList();
    }

    public static int getTotalOrderPrice(Map<Menu, Integer> orderMenus) {
        return orderMenus.keySet().stream()
                .map(menu -> menu.price * orderMenus.get(menu))
                .reduce(0, Integer::sum);
    }

    public static boolean isMain(Menu orderMenu) {
        return Objects.equals(orderMenu.type, Menu.MAIN);
    }

    public static boolean isDessert(Menu orderMenu) {
        return Objects.equals(orderMenu.type, Menu.DESERT);
    }

    public static boolean isDrink(Menu orderMenu) {
        return Objects.equals(orderMenu.type, Menu.DRINK);
    }
}
