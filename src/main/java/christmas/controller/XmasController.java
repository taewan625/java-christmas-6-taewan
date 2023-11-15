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
        CustomerEvent customerEvent = customerOrder.isEventApplicable(); // event 적용유무 결정 가능
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

    private Map<Menu, Integer> getOrders() {
        OutputView.print(OutputView.QUEST_ORDER);
        String orders = InputView.orderMenu();
        try {
            List<String[]> orderDatas = XmasValidator.formatCheck(XmasConverter.splitOrderDatas(orders));
            XmasValidator.orderMenu(XmasConverter.orderMenus(orderDatas), XmasConverter.orderMenusCounts(orderDatas));
            return Menu.createOrderMenus(orderDatas);
        } catch (IllegalStateException | IllegalArgumentException exception) {
            OutputView.print(exception.getMessage());
            return getOrders();
        }
    }

    private void showCustomerOrder(CustomerOrder customerOrder) {
        OutputView.printReservationFullDate(customerOrder.getReservationFullDate());
        OutputView.printOrderMenus(customerOrder.getOrderMenus());
        OutputView.printOrderMenusPrice(customerOrder.getTotalOrderPrice());
    }

    private void showCustomerPromotion(CustomerEvent customerEvent) {
        OutputView.showPromotionMenuData(customerEvent.getPromotionMenus());
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
