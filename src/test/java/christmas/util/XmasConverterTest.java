package christmas.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class XmasConverterTest {

    String orders = "타파스-1,제로콜라-1";
    List<String[]> orderDatas = XmasConverter.splitOrderDatas(orders);

    @Test
    void splitOrderDatas() {
        for (String[] orderData : orderDatas) {
            int size = orderData.length;
            assertThat(size).isEqualTo(2);
        }
    }

    @Test
    void orderMenus() {
        assertThat(XmasConverter.orderMenus(orderDatas))
                .isEqualTo(Set.of("타파스", "제로콜라"));
    }

    @Test
    void orderMenusCounts() {
        assertThat(XmasConverter.orderMenusCounts(orderDatas))
                .isEqualTo(List.of("1", "1"));
    }

    @Test
    void dateToFullDate() {
        int date = 3;
        assertThat(XmasConverter.dateToFullDate(date))
                .isEqualTo("12월 3일");
    }

    @Test
    void toWon() {
        int money = 10_000;

        assertThat(XmasConverter.toWon(money))
                .isEqualTo("10,000원");
    }

    @Test
    void toMinusWon() {
        int money = 10_000;
        int zero = 0;

        assertThat(XmasConverter.toMinusWon(money))
                .isEqualTo("-10,000원");
        assertThat(XmasConverter.toMinusWon(zero))
                .isEqualTo("0원");
    }

    @Test
    void benefitData() {
        String discountData = "주말할인=10000";
        assertThat(XmasConverter.benefitData(discountData)).isEqualTo("주말할인: -10,000원");
    }

    @Test
    void menuData() {
        Map<String, Integer> menus = Map.of("샴폐인",2, "제로콜라",4);
        List<String> menuDatas = XmasConverter.menuData(menus);

        assertThat(menuDatas.size()).isEqualTo(2);
        assertThat(menuDatas).contains("샴폐인 2개", "제로콜라 4개");
    }
}