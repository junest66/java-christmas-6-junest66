package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Messages;
import christmas.utils.OrderParser;
import christmas.validator.DateValidator;
import christmas.validator.MenuOrderValidator;
import java.util.Map;
import java.util.function.Supplier;

public class InputView {
    public int getDecemberDay() {
        return getInput(() -> {
            System.out.println(Messages.EXPECTED_DECEMBER_VISIT_DATE_PROMPT);
            String input = Console.readLine();
            DateValidator.validate(input);
            return Integer.parseInt(input);
        });
    }

    public Map<String, Integer> getMenuOrder() {
        return getInput(() -> {
            System.out.println(Messages.REQUEST_MENU_SELECTION_AND_QUANTITY_PROMPT);
            String input = Console.readLine();
            MenuOrderValidator.validate(input);
            return OrderParser.parse(input);
        });
    }

    private <T> T getInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
