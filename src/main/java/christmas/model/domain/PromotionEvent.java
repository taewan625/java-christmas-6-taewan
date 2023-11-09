package christmas.model.domain;

public class PromotionEvent {
    private final String PROMOTION = "샴페인 1개";
    private final int PROMOTION_PRICE = 120_000;

    public boolean isPromotionEvent(int totalPrice) {
        return totalPrice >= PROMOTION_PRICE;
    }

    // todo 주문서에는 true일 경우 String과 int price를 줘야할텐데 이건 다음 단계 구현 윤곽 잡히면 그 때 메서드 생성
    //  ex) Map <String eventType, Map<String promotion, Integer price>>
}
