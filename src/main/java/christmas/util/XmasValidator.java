package christmas.util;

import christmas.model.domain.Menu;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class XmasValidator {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String ERROR_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String FORMAT_HYPHEN = "-";
    private static final String FORMAT_COMMA = ",";
    private static final int MAX_ORDER_COUNT = 20;
    private static final int MAX_DATE = 31;
    private static final int DATA_COUNT = 2;


    public static void formatCheck(Stream<String[]> orderDatas) {
        if (orderDatas.anyMatch(orderData -> orderData.length != DATA_COUNT)) {
            illegalState(ERROR_ORDER);
        }
    }

    public static void orderMenu(Set<Menu> orderMenus, List<String> orderMenusCounts) {
        orderMenusCounts.forEach(XmasValidator::orderMenusCount);
        maxOrderMenuCount(orderMenusCounts);

        if (orderMenus.size() != orderMenusCounts.size()) {
            illegalState(ERROR_ORDER);
        }
        if (orderMenus.stream().anyMatch(menu -> menu == Menu.NO_MENU)) {
            illegalState(ERROR_ORDER);
        }
        if (orderMenus.stream().allMatch(Menu::isDrink)) {
            illegalArgument(ERROR_ORDER);
        }
    }

    private static void maxOrderMenuCount(Collection<String> orderMenuCounts) {
        int allCount = orderMenuCounts.stream().mapToInt(Integer::parseInt).reduce(0, Integer::sum);
        if (allCount > XmasValidator.MAX_ORDER_COUNT) {
            illegalState(ERROR_ORDER);
        }
    }

    public static void date(String date) {
        naturalNumberMaxRange(date, MAX_DATE, ERROR_DATE);
    }

    private static void orderMenusCount(String count) {
        naturalNumberMaxRange(count, MAX_ORDER_COUNT, ERROR_ORDER);
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
