package christmas.domain;

import christmas.constants.MenuItem;
import christmas.constants.MenuCategory;
import java.util.Map;

public class Order {
    private final int date;
    private final Map<MenuItem, Integer> menu;

    public Order(int date, Map<MenuItem, Integer> menu) {
        this.date = date;
        this.menu = menu;
    }

    public int getMenuCategoryCount(MenuCategory menuCategory) {
        return menu.entrySet().stream()
                .filter(entry -> entry.getKey().getMenuCategory() == menuCategory)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int calculateTotalPriceBeforeDiscount() {
        return menu.entrySet().stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getDate() {
        return date;
    }

    public Map<MenuItem, Integer> getMenu() {
        return menu;
    }
}
