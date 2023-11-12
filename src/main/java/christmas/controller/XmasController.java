package christmas.controller;

import christmas.model.domain.CustomerEvent;
import christmas.model.domain.CustomerOrder;
import christmas.model.domain.Menu;
import christmas.util.XmasConverter;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class XmasController {
    public void run() {
        CustomerOrder customerOrder = book();
        int totalOrderPrice = customerOrder.getTotalOrderPrice(); // todo. 없앨수 있으면 없애도록...
        showCustomerOrder(customerOrder);
        showTotalOrderPrice(totalOrderPrice);
        CustomerEvent customerEvent = applyEvent(customerOrder);
        showCustomerPromotion(customerEvent);
        showCustomerEvent(customerEvent);
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
-31,246원

 <12월 이벤트 배지>
산타
*/

    private void showCustomerPromotion(CustomerEvent customerEvent) {
        OutputView.printStaticMessage(OutputView.PROMOTION_MENU);

        String promotionValue = OutputView.NONE;

        if (customerEvent.isPromotionEventDatas()) {
            String promotionMenuData = customerEvent.getPromotionData();
            promotionValue = promotionMenuData + OutputView.UNIT;
        }

        OutputView.printStaticMessage(promotionValue);
    }

    private void showCustomerEvent(CustomerEvent customerEvent) {
        OutputView.printStaticMessage(OutputView.EVENT_HISTORY);
        if (!customerEvent.isDiscountEventDatas()) {
            OutputView.printStaticMessage(OutputView.NONE);
        }
        if (customerEvent.isDiscountEventDatas()) {
            List<String> discountDatas = customerEvent.getDiscountData();
            for (String discountData : discountDatas) {
                OutputView.printStaticMessage(XmasConverter.discountData(discountData));
            }
        }
    }
}
