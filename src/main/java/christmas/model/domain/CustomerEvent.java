package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerEvent {
    private Map<String, Integer> discountEventDatas;
    private Map<String, Integer> promotionEventDatas;
    private int totalDiscountPrice;
    private int totalBenefitAmount;
    private Badge badge;

    public CustomerEvent(Map<String, Integer> discountEventDatas, Map<String, Integer> promotionEventDatas) {
        this.discountEventDatas = discountEventDatas;
        this.promotionEventDatas = promotionEventDatas;
        setTotalDiscountPrice();
        setTotalBenefitAmount();
        setBadge();
    }

    private void setTotalDiscountPrice() {
        totalDiscountPrice = sumEventValues(discountEventDatas);
    }

    private void setTotalBenefitAmount() {
        totalBenefitAmount = this.totalDiscountPrice + sumEventValues(promotionEventDatas);
    }

    private int sumEventValues(Map<String, Integer> datas) {
        return datas.values().stream().mapToInt(Integer::intValue).sum();
    }

    private void setBadge() {
        this.badge = Badge.getBadge(totalBenefitAmount);
    }

    public String getPromotionData() {
        String promotionEventDataKey = getPromotionEventDataKey();
        String promotionMenu = Menu.getPromotionMenu(promotionEventDataKey);
        Integer promotionMenuCount = promotionEventDatas.get(promotionEventDataKey);
        return promotionMenu + "\s" + promotionMenuCount;
    }

    private String getPromotionEventDataKey() {
        // promotion 종류가 1개기 때문에 next()만 호출
        String promotionEventDataKey = promotionEventDatas.keySet().iterator().next();
        return promotionEventDataKey;
    }

    public List<String> getDiscountData() {
        return discountEventDatas.entrySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public boolean isPromotionEventDatas() {
        return !promotionEventDatas.isEmpty();
    }

    public boolean isDiscountEventDatas() {
        return !discountEventDatas.isEmpty();
    }
}
