package christmas.model.domain;

import java.util.HashMap;
import java.util.Map;

public class CustomerEvent {
    private Map<String, Integer> discountEventDatas;
    private Map<String, Integer> promotionEventDatas;
    private int totalDiscountPrice;
    private int totalBenefitAmount;
    private Badge badge;

    public CustomerEvent() {
        this.discountEventDatas = new HashMap<>();
        this.promotionEventDatas = new HashMap<>();
    }

    public void setEventData() {
        totalDiscountPrice = sumEventValues(discountEventDatas);
        totalBenefitAmount = totalDiscountPrice + sumEventValues(promotionEventDatas);
    }

    private int sumEventValues(Map<String, Integer> datas) {
        return datas.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void setBadge() {
        badge = Badge.getBadge(totalBenefitAmount);
    }
}
