package christmas.controller;

import christmas.model.domain.CustomerEvent;
import christmas.model.domain.CustomerOrder;
import christmas.model.domain.Menu;
import christmas.model.domain.Promotion;
import christmas.util.XmasConverter;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class XmasController {
    public void run() {
        CustomerOrder customerOrder = book();
        int totalOrderPrice = customerOrder.getTotalOrderPrice();
        showCustomerOrder(customerOrder);
        showTotalOrderPrice(totalOrderPrice);
        CustomerEvent customerEvent = applyEvent(customerOrder);
        showCustomerEvent(customerEvent, totalOrderPrice);
    }

    private void showTotalOrderPrice(int totalOrderPrice) {
        OutputView.printStaticMessage(OutputView.ORDER_PRICE);
        OutputView.printStaticMessage(XmasConverter.toWon(totalOrderPrice));
    }

    private CustomerOrder book() {
        // todo. try-catch 잡기
        OutputView.printStaticMessage(OutputView.WELCOME_MESSAGE);
        OutputView.printStaticMessage(OutputView.QUEST_BOOKING_DATE);
        int reservationDate = Integer.parseInt(InputView.reserveDate());
        // todo. 검증기
        OutputView.printStaticMessage(OutputView.QUEST_ORDER);
        String orderMenu = InputView.orderMenu();
        // todo. 검증기
        Map<Menu, Integer> orderMenus = XmasConverter.StringToMap(orderMenu);
        return new CustomerOrder(reservationDate, orderMenus);
    }

    private void showCustomerOrder(CustomerOrder customerOrder) {
        String reservationFullDate = customerOrder.getReservationFullDate();
        OutputView.printReservationFullDate(reservationFullDate);
        List<String> orderMenus = customerOrder.getOrderMenus();
        OutputView.printOrderMenus(orderMenus);
    }

    private CustomerEvent applyEvent(CustomerOrder customerOrder) {
        Map<String, Integer> discountDatas = customerOrder.getDiscountDatas();
        Map<String, Integer> promotionDatas = customerOrder.getPromotionData();
        return new CustomerEvent(discountDatas, promotionDatas);
    }


/*
<총혜택 금액>
0원

<할인 후 예상 결제 금액>
8,500원

<12월 이벤트 배지>
없음

<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원

<총혜택 금액>
-31,246원

 <12월 이벤트 배지>
산타
*/

    public void showCustomerEvent(CustomerEvent customerEvent, int totalOrderPrice) {
        OutputView.printStaticMessage(OutputView.PROMOTION_MENU);

        String promotionValue = OutputView.NONE;
        if (Promotion.isPromotion(totalOrderPrice)) {
            String promotionMenuData = customerEvent.getPromotionData();
            promotionValue = promotionMenuData + OutputView.UNIT;
        }

        OutputView.printStaticMessage(promotionValue);
    }


}
