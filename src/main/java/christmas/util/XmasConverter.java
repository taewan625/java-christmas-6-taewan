package christmas.util;

import christmas.model.domain.Menu;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class XmasConverter {
    private static final String COMMA = ",";
    private static final String DASH = "-";

    private static final String MONTH = "12월 ";
    private static final String DATE_UNIT = "일";

    private static final int MENU = 0;
    private static final int COUNT = 1;
    private static final int TYPE = 0;
    private static final int PRICE = 1;
    public static int StringToInt(String string) {
        return Integer.parseInt(string);
    }

    public static Map<Menu, Integer> StringToMap(String orderMenu) {
        Map<Menu, Integer> orderMenus = new HashMap<>();
        String[] splitOrders = orderMenu.split(COMMA);
        for (String splitOrder : splitOrders) {
            String[] split = splitOrder.split(DASH);
            orderMenus.put(Menu.getMenu(split[MENU]), Integer.valueOf(split[COUNT])); // todo 분리필요: split망 역할. menu는 menu에서
        }
        return orderMenus;
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
        String[] discountDatas = discountData.split("=");
        return discountDatas[TYPE] + ": " + toMinusWon(Integer.parseInt(discountDatas[PRICE]));
    }
}
