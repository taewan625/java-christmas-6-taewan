package christmas.model.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {

    @Test
    void isBadge() {
        int falseEventPrice = 4_999;
        int trueEventPrice = 5_000;

        assertThat(Badge.isBadge(falseEventPrice)).isFalse();
        assertThat(Badge.isBadge(trueEventPrice)).isTrue();

    }
    @Test
    void getBadge() {
        int starPointMin = 5000;
        int starPointMax = 9_999;
        int treePointMin = 10_000;
        int treePointMax = 19_999;
        int santaPointMin = 20_000;
        for (int i = 0; i < 100; i++) {
            assertThat(Badge.getBadge(starPointMin)).isSameAs(Badge.STAR);
            assertThat(Badge.getBadge(starPointMax)).isSameAs(Badge.STAR);
            assertThat(Badge.getBadge(treePointMin)).isSameAs(Badge.TREE);
            assertThat(Badge.getBadge(treePointMax)).isSameAs(Badge.TREE);
            assertThat(Badge.getBadge(santaPointMin)).isSameAs(Badge.SANTA);
        }
    }
}