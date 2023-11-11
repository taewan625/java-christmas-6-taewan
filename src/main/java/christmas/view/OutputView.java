package christmas.view;

import java.util.List;

public class OutputView {

    public static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String QUEST_BOOKING_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String QUEST_ORDER = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String EVENT_PREVIEW_MESSAGE = "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String ORDER_MENU = "\n<주문 메뉴>";
    public static final String ORDER_PRICE = "\n<할인 전 총주문 금액>";
    public static final String PROMOTION_MENU = "\n<증정 메뉴>";
    public static final String EVENT_HISTORY = "\n<혜택 내역>";
    public static final String TOTAL_EVENT_PRICE = "\n<총혜택 금액>";
    public static final String PAYMENT_PRICE = "\n<할인 후 예상 결제 금액>";
    public static final String BADGE = "\n<12월 이벤트 배지>";
    public static final String NONE = "없음";
    public static final String UNIT = "개";


    public static void printStaticMessage(String msg) {
        System.out.println(msg);
    }

    public static void printReservationFullDate(String reservationFullDate) {
        System.out.println(reservationFullDate + EVENT_PREVIEW_MESSAGE);
    }

    public static void printOrderMenus(List<String> orderMenus) {
        System.out.println(ORDER_MENU);
        for (String orderMenu : orderMenus) {
            System.out.println(orderMenu + UNIT);
        }
    }
}
