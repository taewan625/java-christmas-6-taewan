package christmas.model.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.model.domain.Menu.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerOrderTest {
    CustomerOrder customerOrder;
    CustomerOrder customerOrderNoEvent;
    CustomerEvent customerEvent;
    CustomerEvent customerEventNoEvent;
    @BeforeEach
    void before() {
        int reservationDate = 3;
        Map<Menu, Integer> orderMenus = Map.of(MAIN_1, 1, MAIN_2, 1, DESSERT_1,2, DRINK_1, 1);
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
    void getReservationFullDate() {
        assertThat(customerOrder.getReservationFullDate())
                .isEqualTo("12월 3일");
    }

    @Test
    void getOrderMenus() {
        assertThat(customerOrder.getOrderMenus())
                .contains("제로콜라 1개", "티본스테이크 1개", "초코케이크 2개", "바비큐립 1개");
    }

    @Test
    void getTotalOrderPrice() {
        assertThat(customerOrder.getTotalOrderPrice())
                .isEqualTo("142,000원");
    }

    @Test
    void isEventApplicable_Can_make_Object() {

        assertNotNull(customerEvent);
        assertNotNull(customerEventNoEvent);
    }

    @Test
    void getPredictPay() {
        assertThat(customerOrder.getPredictPay(customerEvent))
                .isEqualTo("135,754원");
        assertThat(customerOrderNoEvent.getPredictPay(customerEventNoEvent))
                .isEqualTo("8,500원");
    }
}