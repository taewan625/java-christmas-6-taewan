package christmas.util;

import christmas.model.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public class XmasValidator {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String ERROR_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String FORMAT_HYPHEN = "-";
    private static final String FORMAT_COMMA = ",";
    private static final int MENU = 0;
    private static final int COUNT = 1;
    private static final int MAX_ORDER = 20;
    private static final int MAX_DATE = 31;


    public static void orderMenu(String orders) {
        List<String> rawMenus = new ArrayList<>();
        List<String> counts = new ArrayList<>();

        for (String order : orders.split(FORMAT_COMMA)) {
            String[] orderData = order.split(FORMAT_HYPHEN);
            rawMenus.add(orderData[MENU]);
            counts.add(orderData[COUNT]);
        }
        //0.없는 형식
        if (rawMenus.size() != counts.size()) {
            illegalState(ERROR_ORDER);
        }
        //1.숫자, 1이상 20이하
        for (String rawCount : counts) {
            count(rawCount);
        }
        //2. 최대 20개
        int allCount = counts.stream().mapToInt(Integer::parseInt).reduce(0, Integer::sum);
        if (allCount > MAX_ORDER) {
            illegalState(ERROR_ORDER);
        }
        //4. 없는 메뉴
        List<Menu> menus = rawMenus.stream().map(Menu::getMenu).toList();
        //3. 음료만 주문
        boolean onlyDrink = rawMenus.stream().allMatch(menu -> Menu.isDrink(Menu.getMenu(menu)));
        if (onlyDrink) {
            illegalArgument(ERROR_ORDER);
        }
        //5. 중복
        if (menus.stream().distinct().count() != menus.size()) {
            illegalState(ERROR_ORDER);
        }
    }

    public static void date(String date) {
        naturalNumberMaxRange(date, MAX_DATE, ERROR_DATE);
    }

    private static void count(String count) {
        naturalNumberMaxRange(count, MAX_ORDER, ERROR_ORDER);
    }

    private static void naturalNumberMaxRange(String rawNumber, int max, String errorMsg) {
        int number = checkNaturalNumber(rawNumber, errorMsg);
        if (number >= max) {
            illegalState(errorMsg);
        }
    }

    private static int checkNaturalNumber(String number, String errorMsg) {
        if (!number.matches("^[1-9]\\d*$")) { // 자연수 정규식
            illegalArgument(errorMsg);
        }
        return Integer.parseInt(number);
    }

    private static void illegalArgument(String errorMessage) {
        throw new IllegalArgumentException(ERROR_MESSAGE + errorMessage);
    }

    private static void illegalState(String errorMessage) {
        throw new IllegalStateException(ERROR_MESSAGE + errorMessage);
    }
}
