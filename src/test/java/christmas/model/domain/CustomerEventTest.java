package christmas.model.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static christmas.model.domain.Menu.*;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerEventTest {
    CustomerOrder customerOrder;
    CustomerOrder customerOrderNoEvent;
    CustomerEvent customerEvent;
    CustomerEvent customerEventNoEvent;

    @BeforeEach
    void before() {
        int reservationDate = 3;
        Map<Menu, Integer> orderMenus = Map.of(MAIN_1, 1, MAIN_2, 1, DESSERT_1, 2, DRINK_1, 1);
        Map<Menu, Integer> orderMenusNoEvent = Map.of(APPETIZER_2, 1, DRINK_1, 1);

        customerOrder = new CustomerOrder(reservationDate, orderMenus);
        customerOrderNoEvent = new CustomerOrder(reservationDate, orderMenusNoEvent);
        customerEvent = customerOrder.isEventApplicable();
        customerEventNoEvent = customerOrderNoEvent.isEventApplicable();
    }

    @AfterEach
    void after() {
        customerOrder = null;
        customerOrderNoEvent = null;
    }

    @Test
    void getPromotionMenus() {
        List<String> promotionMenus = customerEvent.getPromotionMenus();
        List<String> promotionMenusX = customerEventNoEvent.getPromotionMenus();

        assertThat(promotionMenus).isEqualTo(List.of("샴페인 1개"));
        assertThat(promotionMenusX).isEmpty();
    }

    @Test
    void getBenefitData() {
        List<String> benefitData = customerEvent.getBenefitData();
        assertThat(benefitData.size()).isEqualTo(4);
        assertThat(benefitData)
                .contains("크리스마스 디데이 할인=1200", "증정 이벤트=25000", "평일 할인=4046", "특별 할인=1000");
    }

    @Test
    void getTotalBenefit() {
        assertThat(customerEvent.getTotalBenefit()).isEqualTo("-31,246원");
        assertThat(customerEventNoEvent.getTotalBenefit()).isEqualTo("0원");
    }

    @Test
    void getBadge() {
        assertThat(customerEvent.getBadge()).isEqualTo("산타");
        assertThat(customerEventNoEvent.getBadge()).isEqualTo("");
    }
}