package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerEvent {
    private final Map<String, Integer> benefitDatas;
    private final Map<String, Integer> discountDatas; // 저장하지 않아도 되지만, 차후 변경사항에서 사용할 수도 있어서 분리
    private final List<Promotion> promotions;
    private final int totalDiscountPrice;
    private final int totalBenefitAmount;

    public CustomerEvent() {
        this.discountDatas = new HashMap<>();
        this.promotions = new ArrayList<>();
        this.benefitDatas = new HashMap<>();
        this.totalDiscountPrice = 0;
        this.totalBenefitAmount = 0;
    }
    public CustomerEvent(Map<String, Integer> discountDatas, List<Promotion> promotions) {
        this.discountDatas = discountDatas;
        this.promotions = promotions;
        this.benefitDatas = setBenefitDatas();
        this.totalDiscountPrice = setTotalDiscountPrice();
        this.totalBenefitAmount = setTotalBenefitAmount();
    }

    public int getPredictPay(int totalOrderPrice) {
        return totalOrderPrice - totalDiscountPrice;
    }

    private Map<String, Integer> setBenefitDatas() {
         return Stream.concat(promotions.stream().flatMap(
                                promotion -> Promotion.setPromotionDatas(promotion).entrySet().stream()),
                        discountDatas.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private int setTotalDiscountPrice() {
        return sumEventValues(discountDatas);
    }

    private int setTotalBenefitAmount() {
        return sumEventValues(benefitDatas);
    }

    private int sumEventValues(Map<String, Integer> datas) {
        return datas.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String getBadge() {
        return Badge.getEventBadgeName(totalBenefitAmount);
    }

    public List<String> getPromotionProducts() {
        return promotions.stream().map(Promotion::getPromotionProduct).toList();
    }

    public List<String> getBenefitData() {
        return benefitDatas.entrySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public String getTotalBenefit() {
        if (totalBenefitAmount == 0){
            return XmasConverter.toWon(totalBenefitAmount);
        }
        return XmasConverter.toMinusWon(totalBenefitAmount);
    }
}
