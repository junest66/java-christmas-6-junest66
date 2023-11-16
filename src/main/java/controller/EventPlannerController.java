package controller;

import christmas.DTO.BenefitResultDTO;
import christmas.DTO.OrderDTO;
import christmas.domain.BadgeCalculator;
import christmas.domain.BenefitCalculator;
import christmas.domain.BenefitResult;
import christmas.domain.EventsFactory;
import christmas.domain.Order;
import christmas.domain.PaymentAmountCalculator;
import christmas.domain.Waiter;
import christmas.domain.event.Event;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class EventPlannerController {

    private Order order;
    private OutputView outputView;
    private InputView inputView;
    private Waiter waiter;
    private BenefitCalculator benefitCalculator;
    private BenefitResult benefitResult;
    private PaymentAmountCalculator paymentCalculator;
    private BadgeCalculator badgeCalculator;

    public EventPlannerController() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.waiter = new Waiter();
        this.paymentCalculator = new PaymentAmountCalculator();
        this.badgeCalculator = new BadgeCalculator();
    }

    public void start() {
        outputView.printEventStartGreeting();
        order = processOrder();
        outputView.printOrderInformation(new OrderDTO(order));
        outputView.printTotalOrderAmountBeforeDiscount(order.calculateTotalPriceBeforeDiscount());
        List<Event> events = new EventsFactory(order).createEvents();
        benefitCalculator = new BenefitCalculator(events);
        benefitResult = benefitCalculator.calculateBenefit();
        outputView.printGiveawayMenu(benefitResult.getGiveawayMenu());
        outputView.printBenefitResult(new BenefitResultDTO(benefitResult));
        outputView.printTotalBenefitAmount(benefitResult.getTotalAllBenefitAmount());
        int paymentAmount = paymentCalculator.calculatePaymentAmount(order, benefitResult);
        outputView.printPaymentAmountAfterDiscount(paymentAmount);
        outputView.printBadgeResult(badgeCalculator.calculateBadge(benefitResult));
    }

    private Order processOrder() {
        int date = inputView.getDecemberDay();
        while (true) {
            Map<String, Integer> menuOrder = inputView.getMenuOrder();
            try {
                return waiter.takeOrder(date, menuOrder);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
