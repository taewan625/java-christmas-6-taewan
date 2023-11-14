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

    private int getDate() {
        OutputView.printInitQuestion();
        String date = InputView.reserveDate();
        try {
            XmasValidator.date(date);
            return Integer.parseInt(date);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.print(e.getMessage());
            return getDate();
        }
    }

    private void showCustomerOrder(CustomerOrder customerOrder) {
        OutputView.printReservationFullDate(customerOrder.getReservationFullDate());
        OutputView.printOrderMenus(customerOrder.getOrderMenus());
        OutputView.printOrderMenusPrice(customerOrder.getTotalOrderPrice());
    }

    private CustomerEvent applyEvent(CustomerOrder customerOrder) {
        if (customerOrder.isEventApplicable()) {
            return new CustomerEvent(customerOrder.getDiscountDatas(), customerOrder.getPromotionData());
        }
        return new CustomerEvent();
    }

    private void showCustomerPromotion(CustomerEvent customerEvent) {
        List<String> promotionMenuData = customerEvent.getPromotionProducts();
        OutputView.showPromotionMenuData(promotionMenuData);
    }

    private void showCustomerEvent(CustomerEvent customerEvent) {
        List<String> benefitData = customerEvent.getBenefitData()
                .stream()
                .map(XmasConverter::benefitData).toList();

        OutputView.showEventBenefitData(benefitData);
        OutputView.showEventBenefitPrice(customerEvent.getTotalBenefit());
    }

    private static void showPayment(CustomerOrder customerOrder, CustomerEvent customerEvent) {
        String predictPay = customerOrder.getPredictPay(customerEvent);
        OutputView.print(OutputView.PAYMENT_PRICE + "\n" + predictPay);
    }

    private static void showBadge(CustomerEvent customerEvent) {
        OutputView.showBadge(customerEvent.getBadge());
    }
}
