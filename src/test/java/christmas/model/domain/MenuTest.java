package christmas.model.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static christmas.model.domain.Menu.*;
import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    Menu[] mainMenu = {MAIN_1, MAIN_2, MAIN_3, MAIN_4};
    Menu[] appeMenu = {APPETIZER_1, APPETIZER_2, APPETIZER_3};
    Menu[] drinkMenu = {DRINK_1, DRINK_2, DRINK_3};
    Menu[] dessertMenu = {DESSERT_1, DESSERT_2};


    @Test
    void getMenu() {
        String noMenu = "없음";
        String allMenus = "양송이수프,타파스,시저샐러드,티본스테이크,바비큐립,해산물파스타,크리스마스파스타," +
                "초코케이크,아이스크림,제로콜라,레드와인,샴페인";

        List<Menu> menus = new ArrayList<>();
        menus.add(Menu.getMenu(noMenu));
        for (String menu : allMenus.split(",")) {
            menus.add(Menu.getMenu(menu));
        }
        assertThat(menus).contains(values());
    }

    @Test
    void getPromotionMenuData() {
        Map<Promotion, Integer> promotions = Map.of(Promotion.DRINK_3, 1);
        assertThat(Menu.getPromotionMenus(promotions)).isEqualTo(Map.of("샴페인",1));
    }

    @Test
    void getPromotionBenefit() {
        String promotionMenu = "DRINK_3";
        int count = 1;
        int promotionBenefit = Menu.getPromotionBenefit(promotionMenu, count);
        assertThat(promotionBenefit).isEqualTo(25000);
    }

    @Test
    void getTotalOrderPrice() {
        Map<Menu, Integer> orderMenus = Map.of(APPETIZER_1, 2, APPETIZER_2, 3);
        int totalOrderPrice = Menu.getTotalOrderPrice(orderMenus);
        assertThat(totalOrderPrice).isEqualTo(6000*2 + 5500 *3);
    }

    @Test
    void getOrderMenus() {
        Map<Menu, Integer> orderMenus = Map.of(APPETIZER_1, 2, APPETIZER_2, 3);
        assertThat(Menu.getOrderMenus(orderMenus)).isEqualTo(Map.of("양송이수프",2,"타파스",3));
    }

    @Test
    void isMain() {
        for (Menu main : mainMenu) {
            assertThat(Menu.isMain(main)).isTrue();
        }
        for (Menu appe : appeMenu) {
            assertThat(Menu.isMain(appe)).isFalse();
        }
        for (Menu drink : drinkMenu) {
            assertThat(Menu.isMain(drink)).isFalse();
        }
        for (Menu dessert : dessertMenu) {
            assertThat(Menu.isMain(dessert)).isFalse();
        }
    }

    @Test
    void isDessert() {
        for (Menu main : mainMenu) {
            assertThat(Menu.isDessert(main)).isFalse();
        }
        for (Menu appe : appeMenu) {
            assertThat(Menu.isDessert(appe)).isFalse();
        }
        for (Menu drink : drinkMenu) {
            assertThat(Menu.isDessert(drink)).isFalse();
        }
        for (Menu dessert : dessertMenu) {
            assertThat(Menu.isDessert(dessert)).isTrue();
        }
    }

    @Test
    void isDrink() {
            assertThat(Menu.isDrink("양송이수프")).isFalse();
            assertThat(Menu.isDrink("시저샐러드")).isFalse();
            assertThat(Menu.isDrink("타파스")).isFalse();
            assertThat(Menu.isDrink("샴페인")).isTrue();
    }
}