package christmas.constants;

import java.util.Arrays;
import java.util.List;

public class Values {
    public static final String NOT_EXIST = "없음";
    public static final String GIVEAWAY_EVENT_ITEMS = "샴페인 1개";
    public static final String ITEM_SEPARATOR = ",";
    public static final String MENU_QUANTITY_SEPARATOR = "-";
    public static final String MENU_ITEM_PATTERN = "[가-힣a-zA-Z]+-\\d+";
    public static final String FORMATTED_CURRENCY_AMOUNT = "%,d원";
    public static final String MENU_ITEM_FORMAT = "%s %d개";
    public static final String BENEFIT_DETAILS = "%s: %s";

    public static final List<Integer> starDay = Arrays.asList(3, 10, 17, 24, 25, 31);
    public static final int MINIMUM_TOTAL_ORDER_AMOUNT_FOR_EVENT = 10000;
    public static final int MINIMUM_TOTAL_ORDER_AMOUNT_FOR_GIVEAWAY_EVENT = 120000;
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;
    public static final int MINIMUM_DATE_FOR_EVENT = 1;
    public static final int MAXIMUM_DATE_FOR_EVENT = 31;
    public static final int MINIMUM_MENU_QUANTITY = 1;
    public static final int MAXIMUM_TOTAL_QUANTITY = 20;
    public static final int MAXIMUM_DATE_FOR_CHRISTMAS_EVENT = 25;
    public static final int CHRISTMAS_EVENT_START_DISCOUNT = 1000;
    public static final int CHRISTMAS_EVENT_DAILY_ADDITION = 100;
    public static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    public static final int WEEKEND_DISCOUNT_AMOUNT = 2023;
    public static final int SPECIAL_EVENT_DISCOUNT_AMOUNT = 1000;
    public static final int DAYS_IN_WEEK = 7;
    public static final int WEEKEND_DAY_FRIDAY = 1;
    public static final int WEEKEND_DAY_SATURDAY = 2;
}
