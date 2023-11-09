package christmas.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    Menu[] mainMenu = {Menu.MAIN_1, Menu.MAIN_2, Menu.MAIN_3, Menu.MAIN_4};
    Menu[] appeMenu = {Menu.APPETIZER_1, Menu.APPETIZER_2, Menu.APPETIZER_3};
    Menu[] drinkMenu = {Menu.DRINK_1, Menu.DRINK_2, Menu.DRINK_3};
    Menu[] dessertMenu = {Menu.DESSERT_1, Menu.DESSERT_2};

    @Test
    void isMain() {
        for (Menu main : mainMenu) {
            Assertions.assertThat(Menu.isMain(main)).isTrue();
        }
        for (Menu appe : appeMenu) {
            Assertions.assertThat(Menu.isMain(appe)).isFalse();
        }
        for (Menu drink : drinkMenu) {
            Assertions.assertThat(Menu.isMain(drink)).isFalse();
        }
        for (Menu dessert : dessertMenu) {
            Assertions.assertThat(Menu.isMain(dessert)).isFalse();
        }
    }

    @Test
    void isDessert() {
        for (Menu main : mainMenu) {
            Assertions.assertThat(Menu.isDessert(main)).isFalse();
        }
        for (Menu appe : appeMenu) {
            Assertions.assertThat(Menu.isDessert(appe)).isFalse();
        }
        for (Menu drink : drinkMenu) {
            Assertions.assertThat(Menu.isDessert(drink)).isFalse();
        }
        for (Menu dessert : dessertMenu) {
            Assertions.assertThat(Menu.isDessert(dessert)).isTrue();
        }
    }
}