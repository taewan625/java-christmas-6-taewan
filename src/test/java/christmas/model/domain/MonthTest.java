package christmas.model.domain;

import christmas.model.domain.December;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class DecemberTest {
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
    void isWeekDay() {
        for (Integer day : weekDay) {
            weekDays.add(December.isWeekDay(day));
        }
        for (Integer day : weekend) {
            weekends.add(December.isWeekDay(day));
        }
        for (Integer day : starDay) {
            starDays.add(December.isWeekDay(day));
        }

        assertThat(weekDays).contains(true);
        assertThat(weekends).contains(false);
        assertThat(starDays).contains(true);
    }

    @Test
    void isWeekend() {
        for (Integer day : weekDay) {
            weekDays.add(December.isWeekend(day));
        }
        for (Integer day : weekend) {
            weekends.add(December.isWeekend(day));
        }
        for (Integer day : starDay) {
            starDays.add(December.isWeekend(day));
        }

        assertThat(weekDays).contains(false);
        assertThat(weekends).contains(true);
        assertThat(starDays).contains(false);
    }

    @Test
    void isStarDay() {
        for (Integer day : weekDay) {
            weekDays.add(December.isStarDay(day));
        }
        for (Integer day : weekend) {
            weekends.add(December.isStarDay(day));
        }
        for (Integer day : starDay) {
            starDays.add(December.isStarDay(day));
        }

        assertThat(weekDays).contains(true, false);
        assertThat(weekends).contains(false);
        assertThat(starDays).contains(true);
    }
}