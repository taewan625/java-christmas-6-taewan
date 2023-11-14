package christmas.util;

import christmas.model.domain.Menu;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XmasConverter {
    private static final String FORMAT_HYPHEN = "-";
    private static final String FORMAT_COMMA = ",";
    private static final String FORMAT_EQUAL = "=";
    private static final String FORMAT_COLON = ":";
    private static final String SPACE = " ";

    private static final String MONTH = "12월 ";
    private static final String DATE_UNIT = "일";
    private static final String COUNT_UNIT = "개";

    private static final int MENU = 0;
    private static final int COUNT = 1;
    private static final int TYPE = 0;
    private static final int PRICE = 1;

    public static Map<Menu, Integer> orders(String orders) {
        return getOrderDatas(orders)
                .collect(Collectors.toMap(
                        orderData -> Menu.getMenu(orderData[MENU]),
                        orderData -> Integer.parseInt(orderData[COUNT])
                ));
    }

    public static Stream<String[]> getOrderDatas(String orders) {
        return Arrays.stream(orders.split(FORMAT_COMMA))
                .map(order -> order.split(FORMAT_HYPHEN));
    }

    public static Set<Menu> orderMenus(String orders) {
        return getOrderDatas(orders, MENU)
                .map(Menu::getMenu)
                .collect(Collectors.toSet());
    }

    public static List<String> orderMenusCounts(String orders) {
        return getOrderDatas(orders, COUNT)
                .collect(Collectors.toList());
    }

    private static Stream<String> getOrderDatas(String orders, int menu) {
        return Arrays.stream(orders.split(FORMAT_COMMA))
                .map(order -> order.split(FORMAT_HYPHEN)[menu]);
    }

    public static String dateToFullDate(int date) {
        return MONTH + date + DATE_UNIT;
    }

    public static String toWon(int totalOrderPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return decimalFormat.format(totalOrderPrice);
    }

    public static String toMinusWon(int discountPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("-#,###원");
        return decimalFormat.format(discountPrice);
    }

    public static String benefitData(String discountData) {
        String[] discountDatas = discountData.split(FORMAT_EQUAL);
        return discountDatas[TYPE] + FORMAT_COLON + SPACE + toMinusWon(Integer.parseInt(discountDatas[PRICE]));
    }

    public static String menuData(String name, Integer count) {
        return name + SPACE + count + COUNT_UNIT;
    }
}
