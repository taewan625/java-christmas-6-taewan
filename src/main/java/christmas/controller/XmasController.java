package christmas.controller;

import christmas.model.domain.*;
import christmas.util.XmasConverter;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class XmasController {
    public void run() {
        CustomerOrder customerOrder = book();
        showCustomerOrder(customerOrder);
        CustomerEvent customerEvent = applyEvent(customerOrder);
        showCustomerPromotion(customerEvent);
        showCustomerEvent(customerEvent);
        OutputView.printStaticMessage(OutputView.PAYMENT_PRICE);
        String predictPay = customerOrder.getPredictPay(customerEvent);
        OutputView.printStaticMessage(predictPay);
        OutputView.printStaticMessage(OutputView.BADGE);
        System.out.println(customerEvent.getBadge());
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
        OutputView.printStaticMessage(OutputView.ORDER_PRICE);
        OutputView.printStaticMessage(customerOrder.getTotalOrderPrice());
    }

    private CustomerEvent applyEvent(CustomerOrder customerOrder) {
        Map<String, Integer> discountDatas = customerOrder.getDiscountDatas();
        List<Promotion> promotionDatas = customerOrder.getPromotionData();
        return new CustomerEvent(discountDatas, promotionDatas);
    }

    private void showCustomerPromotion(CustomerEvent customerEvent) {
        OutputView.printStaticMessage(OutputView.PROMOTION_MENU);

        String promotionValue = OutputView.NONE;

        if (customerEvent.isPromotionDatas()) {
            String promotionMenuData = customerEvent.getPromotionProduct();
            promotionValue = promotionMenuData + OutputView.UNIT;
        }

        OutputView.printStaticMessage(promotionValue);
    }

    private void showCustomerEvent(CustomerEvent customerEvent) {
        OutputView.printStaticMessage(OutputView.EVENT_HISTORY);
        if (!customerEvent.isBenefitDatas()) {
            OutputView.printStaticMessage(OutputView.NONE);
        }
        if (customerEvent.isBenefitDatas()) {
            List<String> benefitData = customerEvent.getBenefitData();
            benefitData.stream().map(XmasConverter::benefitData).forEach(OutputView::printStaticMessage);
        }
        OutputView.printStaticMessage(OutputView.TOTAL_EVENT_PRICE);
        OutputView.printStaticMessage(customerEvent.getTotalBenefit());
    }
}
