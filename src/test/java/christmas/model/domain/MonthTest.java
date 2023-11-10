package christmas.model.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class MonthTest {
    List<Integer> weekDay = List.of(3, 4, 5, 6, 7, 10, 11,
            12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31);
    List<Integer> weekend = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    List<Integer> starDay = List.of(3, 10, 17, 24, 25, 31);

    Set<Boolean> weekDays = new HashSet<>();
    Set<Boolean> weekends = new HashSet<>();
    Set<Boolean> starDays = new HashSet<>();

    @AfterEach
    void after() {
        weekDays = null;
        weekends = null;
        starDays = null;
    }

    @Test
    void isDDay() {
        for (int i = 1; i <= 25; i++) {
            assertThat(Month.isDDay(i)).isTrue();
        }
        for (int i = 26; i <= 31; i++) {
            assertThat(Month.isDDay(i)).isFalse();
        }
    }

    @Test
    void isWeekDay() {
        for (Integer day : weekDay) {
            weekDays.add(Month.isWeekDay(day));
        }
        for (Integer day : weekend) {
            weekends.add(Month.isWeekDay(day));
        }
        for (Integer day : starDay) {
            starDays.add(Month.isWeekDay(day));
        }

        assertThat(weekDays).contains(true);
        assertThat(weekends).contains(false);
        assertThat(starDays).contains(true);
    }

    @Test
    void isWeekend() {
        for (Integer day : weekDay) {
            weekDays.add(Month.isWeekend(day));
        }
        for (Integer day : weekend) {
            weekends.add(Month.isWeekend(day));
        }
        for (Integer day : starDay) {
            starDays.add(Month.isWeekend(day));
        }

        assertThat(weekDays).contains(false);
        assertThat(weekends).contains(true);
        assertThat(starDays).contains(false);
    }

    @Test
    void isStarDay() {
        for (Integer day : weekDay) {
            weekDays.add(Month.isStarDay(day));
        }
        for (Integer day : weekend) {
            weekends.add(Month.isStarDay(day));
        }
        for (Integer day : starDay) {
            starDays.add(Month.isStarDay(day));
        }

        assertThat(weekDays).contains(true, false);
        assertThat(weekends).contains(false);
        assertThat(starDays).contains(true);
    }
}