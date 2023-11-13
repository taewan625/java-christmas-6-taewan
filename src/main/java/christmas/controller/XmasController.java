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
        int reservationDate = getDate();
        Map<Menu, Integer> orders = getOrders();
        return new CustomerOrder(reservationDate, orders);
    }

    private Map<Menu, Integer> getOrders() {
        OutputView.print(OutputView.QUEST_ORDER);
        String orders = InputView.orderMenu();
        try {
            XmasValidator.formatCheck(XmasConverter.getOrderDatas(orders));
            XmasValidator.orderMenu(XmasConverter.orderMenus(orders), XmasConverter.orderMenusCounts(orders));
            return XmasConverter.orders(orders);
        } catch (IllegalStateException | IllegalArgumentException exception) {
            OutputView.print(exception.getMessage());
            return getOrders();
        }
    }

    // hotFix : 재귀함수를 이용해서 올바른 값을 받아오기 위해서 catch에서 return 필수
    private int getDate() {
        OutputView.printInitQuestion();
        String date = InputView.reserveDate();
        try {
            XmasValidator.date(date);
            return Integer.parseInt(date);// 올바른 값 들어올 시 try에서 바로 반환
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.print(e.getMessage());
            return getDate(); // stack이 돌아와도 올바른 값이 유지
        }
    }

    private void showCustomerOrder(CustomerOrder customerOrder) {
        OutputView.printReservationFullDate(customerOrder.getReservationFullDate());
        OutputView.printOrderMenus(customerOrder.getOrderMenus());
        OutputView.printOrderMenusPrice(customerOrder.getTotalOrderPrice());
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
