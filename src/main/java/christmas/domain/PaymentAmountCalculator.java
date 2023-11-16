package christmas.domain;

public class PaymentAmountCalculator {
    public int calculatePaymentAmount(Order order, BenefitResult benefitResult) {
        int totalBeforeDiscount = order.calculateTotalPriceBeforeDiscount();
        int totalDiscount = benefitResult.getTotalDiscountAmount();
        return totalBeforeDiscount + totalDiscount;
    }
}
