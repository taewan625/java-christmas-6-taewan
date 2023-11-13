package christmas.controller;

import christmas.model.domain.*;
import christmas.util.XmasConverter;
import christmas.util.XmasValidator;
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
        showPayment(customerOrder, customerEvent);
        showBadge(customerEvent);
    }

    private CustomerOrder book() {
        // date
        OutputView.printInitQuestion();
        String date = InputView.reserveDate();
        Map<Menu, Integer> orderMenus = null;
        checkDate(date);
        int reservationDate = XmasConverter.StringToInt(date);
        // menu
        OutputView.print(OutputView.QUEST_ORDER);
        String orderMenu = InputView.orderMenu();
        // 검증 - 최대 20개 주문, 음료만 주문 X, 기본 숫자, 중복 안됨
        try {
            XmasValidator.orderMenu(orderMenu);
        } catch (IllegalStateException | IllegalArgumentException exception) {
            OutputView.print(exception.getMessage());
            book();
        }
        orderMenus = XmasConverter.StringToMap(orderMenu);
        return new CustomerOrder(reservationDate, orderMenus);
    }

    private void checkDate(String date) {
        try {
            XmasValidator.date(date);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.print(e.getMessage());
            book();
        }
    }

    private void showCustomerOrder(CustomerOrder customerOrder) {
        OutputView.printReservationFullDate(customerOrder.getReservationFullDate());
        OutputView.printOrderMenus(customerOrder.getOrderMenus());
        OutputView.print(OutputView.ORDER_PRICE);
        OutputView.print(customerOrder.getTotalOrderPrice());
    }

    private CustomerEvent applyEvent(CustomerOrder customerOrder) {
        Map<String, Integer> discountDatas = customerOrder.getDiscountDatas();
        List<Promotion> promotionDatas = customerOrder.getPromotionData();
        return new CustomerEvent(discountDatas, promotionDatas);
    }

    private void showCustomerPromotion(CustomerEvent customerEvent) {
        OutputView.print(OutputView.PROMOTION_MENU);

        String promotionValue = OutputView.NONE;

        if (customerEvent.isPromotionDatas()) {
            String promotionMenuData = customerEvent.getPromotionProduct();
            promotionValue = promotionMenuData + OutputView.UNIT;
        }

        OutputView.print(promotionValue);
    }

    private void showCustomerEvent(CustomerEvent customerEvent) {
        OutputView.print(OutputView.EVENT_HISTORY);
        if (!customerEvent.isBenefitDatas()) {
            OutputView.print(OutputView.NONE);
        }
        if (customerEvent.isBenefitDatas()) {
            List<String> benefitData = customerEvent.getBenefitData();
            benefitData.stream().map(XmasConverter::benefitData).forEach(OutputView::print);
        }
        OutputView.print(OutputView.TOTAL_EVENT_PRICE);
        OutputView.print(customerEvent.getTotalBenefit());
    }

    private static void showBadge(CustomerEvent customerEvent) {
        OutputView.print(OutputView.BADGE);
        System.out.println(customerEvent.getBadge());
    }

    private static void showPayment(CustomerOrder customerOrder, CustomerEvent customerEvent) {
        OutputView.print(OutputView.PAYMENT_PRICE);
        String predictPay = customerOrder.getPredictPay(customerEvent);
        OutputView.print(predictPay);
    }
}
