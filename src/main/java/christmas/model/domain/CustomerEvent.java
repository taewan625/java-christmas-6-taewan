package christmas.model.domain;

import christmas.util.XmasConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerEvent {
    private final Map<String, Integer> benefitDatas;
    private final Map<String, Integer> discountDatas; // 저장하지 않아도 되지만, 차후 변경사항에서 사용할 수도 있어서 분리
    private final List<Promotion> promotions;
    private final int totalDiscountPrice; // 할인 후 예상 결제 금액에 사용
    private final int totalBenefitAmount;
    private final String badge;

    public CustomerEvent(Map<String, Integer> discountDatas, List<Promotion> promotions) {
        this.discountDatas = discountDatas;
        this.promotions = promotions;
        this.benefitDatas = setBenefitDatas();
        this.totalDiscountPrice = setTotalDiscountPrice();
        this.totalBenefitAmount = setTotalBenefitAmount();
        this.badge = setBadge();
    }

    public int getPredictPay(int totalOrderPrice) {
        return totalOrderPrice - totalDiscountPrice;
    }

    private Map<String, Integer> setBenefitDatas() {
        Map<String,Integer> benefitDatas = new HashMap<>();
        for (Promotion promotion : promotions) {
            Map<String, Integer> promotionDatas = Promotion.setPromotionDatas(promotion);
            benefitDatas.putAll(promotionDatas);
        }
        benefitDatas.putAll(discountDatas);
        return benefitDatas;
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

    private String setBadge() {
        return Badge.getBadge(totalBenefitAmount);
    }

    public String getPromotionProduct() {
        // promotion 종류가 1개기 때문에 값을 바로 가져 옴
        return Promotion.getPromotionProduct(promotions.get(0));
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

    public boolean isPromotionDatas() {
        return !promotions.isEmpty();
    }

    public boolean isBenefitDatas() {
        return !benefitDatas.isEmpty();
    }

    public String getBadge() {
        return badge;
    }
}
