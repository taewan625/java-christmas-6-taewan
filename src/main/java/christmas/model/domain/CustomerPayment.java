package christmas.model.domain;

public class CustomerPayment {
    private int totalOrderPrice;
    private int totalPaymentPrice;

    public CustomerPayment(int totalOrderPrice, int totalPaymentPrice) {
        this.totalOrderPrice = totalOrderPrice;
        this.totalPaymentPrice = totalPaymentPrice;
    }

    public int calculateTotalPaymentPrice(int discountPrice) {
        totalPaymentPrice -= discountPrice;
        return totalPaymentPrice;
    }
}
