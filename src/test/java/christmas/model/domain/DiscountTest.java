package christmas.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DiscountTest {
    @Test
    void Xmas_WeekDay_Star_Discount() {
        int bookDay = 3;
        Map<Menu, Integer> orderMenus = Map.of(Menu.MAIN_1, 3);
        Map<String, Integer> discounts = Discount.getDiscounts(bookDay, orderMenus);
        Assertions.assertThat(discounts).isEqualTo(
                Map.of("크리스마스 디데이 할인", 1200,
                        "평일 할인", 2023 * 3,
                        "특별 할인", 1000));
    }

    @Test
    void WeekDay_Discount() {
        int bookDay = 26;
        Map<Menu, Integer> orderMenus = Map.of(Menu.MAIN_1, 3);
        Map<String, Integer> discounts = Discount.getDiscounts(bookDay, orderMenus);
        Assertions.assertThat(discounts).isEqualTo(
                Map.of("평일 할인", 2023 * 3));
    }

    @Test
    void Xmas_Weekend_Discount() {
        int bookDay = 23;
        Map<Menu, Integer> orderMenus = Map.of(Menu.DESSERT_1, 3);
        Map<String, Integer> discounts = Discount.getDiscounts(bookDay, orderMenus);
        Assertions.assertThat(discounts).isEqualTo(
                Map.of("크리스마스 디데이 할인", 1000 + 100 * (bookDay - 1),
                        "주말 할인", 2023 * 3));
    }
}