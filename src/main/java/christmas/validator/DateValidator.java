package christmas.validator;

import christmas.constants.Messages;
import christmas.constants.Values;

public class DateValidator {
    public static void validate(String input) {
        if (isNotNumber(input) || isDateOutOfRange(Integer.parseInt(input)) || startsWithZero(input)) {
            throw new IllegalArgumentException(Messages.INVALID_DATE_MESSAGE);
        }
    }

    private static boolean isNotNumber(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static boolean isDateOutOfRange(int date) {
        return date < Values.MIN_DATE || date > Values.MAX_DATE;
    }

    private static boolean startsWithZero(String input) {
        return input.startsWith("0");
    }
}
