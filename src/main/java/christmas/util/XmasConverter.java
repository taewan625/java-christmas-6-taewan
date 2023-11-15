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
    private static final String FORMAT_SPACE = " ";

    private static final String MONTH = "12월 ";
    private static final String DATE_UNIT = "일";
    private static final String COUNT_UNIT = "개";

    private static final int MENU = 0;
    private static final int COUNT = 1;
    private static final int TYPE = 0;
    private static final int PRICE = 1;

    // 검증 methods
    public static Stream<String[]> splitOrderDatas(String orders) {
        return Arrays.stream(orders.split(FORMAT_COMMA))
                .map(order -> order.split(FORMAT_HYPHEN));
    }

    private static Stream<String> splitOrderDatas(String orders, int menu) {
        return Arrays.stream(orders.split(FORMAT_COMMA))
                .map(order -> order.split(FORMAT_HYPHEN)[menu]);
    }


    public static Set<Menu> orderMenus(String orders) {
        return splitOrderDatas(orders, MENU)
                .map(Menu::getMenu)
                .collect(Collectors.toSet());
    }

    public static List<String> orderMenusCounts(String orders) {
        return splitOrderDatas(orders, COUNT)
                .collect(Collectors.toList());
    }

    // 수행 methods
    public static Map<Menu, Integer> orders(String orders) {
        return splitOrderDatas(orders)
                .collect(Collectors.toMap(
                        orderData -> Menu.getMenu(orderData[MENU]),
                        orderData -> Integer.parseInt(orderData[COUNT])
                ));
    }

    public static String dateToFullDate(int date) {
        return MONTH + date + DATE_UNIT;
    }

    public static String toWon(int totalOrderPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return decimalFormat.format(totalOrderPrice);
    }

    public static String toMinusWon(int discountPrice) {
        if (discountPrice == 0) {
            return toWon(discountPrice);
        }
        DecimalFormat decimalFormat = new DecimalFormat("-#,###원");
        return decimalFormat.format(discountPrice);
    }

    public static String benefitData(String discountData) {
        String[] discountDatas = discountData.split(FORMAT_EQUAL);
        return discountDatas[TYPE]
                + FORMAT_COLON
                + FORMAT_SPACE
                + toMinusWon(Integer.parseInt(discountDatas[PRICE]));
    }

    public static List<String> menuData(Map<String, Integer> menus) {
        return menus.entrySet().stream()
                .map(menu -> menu.getKey()
                        + FORMAT_SPACE
                        + menu.getValue()
                        + COUNT_UNIT)
                .toList();
    }
}
