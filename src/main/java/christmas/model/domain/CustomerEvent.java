package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerEvent {
    private final Map<String, Integer> benefitDatas;

    // key를 Discount로 받아도 되지만 Discount를 받아서 사용하는 목적이 없기 때문에 정제된 값을 받는 것이 초기화 시, 유용
    private final Map<String, Integer> discountDatas;

    private final Map<Promotion, Integer> promotions;
    private final int totalDiscountPrice;
    private final int totalBenefitAmount;
    private final String badge;

    public CustomerEvent() {
        this.discountDatas = new HashMap<>();
        this.promotions = new HashMap<>();
        this.benefitDatas = new HashMap<>();
        this.totalDiscountPrice = 0;
        this.totalBenefitAmount = 0;
        this.badge = "";
    }

    public CustomerEvent(Map<String, Integer> discountDatas, Map<Promotion, Integer> promotions) {
        this.discountDatas = discountDatas;
        this.promotions = promotions;
        this.benefitDatas = setBenefitDatas();
        this.totalDiscountPrice = setTotalDiscountPrice();
        this.totalBenefitAmount = setTotalBenefitAmount();
        this.badge = setBadge();
    }

    // 초기화
    private Map<String, Integer> setBenefitDatas() {
        return Stream.concat(Promotion.setPromotionDatas(promotions).entrySet().stream(),
                        discountDatas.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private int setTotalDiscountPrice() {
        return sumEventValues(discountDatas);
    }

    private int setTotalBenefitAmount() {
        return sumEventValues(benefitDatas);
    }

    private String setBadge() {
        return Badge.getEventBadgeName(totalBenefitAmount);
    }

    private int sumEventValues(Map<String, Integer> datas) {
        return datas.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    // controller 이용
    public List<String> getPromotionMenus() {
        return XmasConverter.menuData(Menu.getPromotionMenus(promotions));
    }

    public List<String> getBenefitData() {
        return benefitDatas.entrySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public String getTotalBenefit() {
        return XmasConverter.toMinusWon(totalBenefitAmount);
    }

    public int getPredictPay(int totalOrderPrice) {
        return totalOrderPrice - totalDiscountPrice;
    }

    public String getBadge() {
        return this.badge;
    }
}
