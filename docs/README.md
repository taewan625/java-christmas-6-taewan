# 1. 기능 요구사항

## 1.1. 메뉴

```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
<디저트>
초코케이크(15,000), 아이스크림(5,000)
<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

## 1.2. 목표

- 혜택, 이벤트
- 할인, 증정 계획 : 결제 핵심

## 1.3. 할인, 증정 조건

### 1.3.1 할인 : 달력

- 할인, 증정 기간 : 12/1 ~ 12/31
- 디데이 할인
    - 날짜 조건: **12/1 ~ 12/25** : 1000원 -> 이후 100원씩 증가
    - 할인 조건: 총 주문 금액에서 빼기
- 평일 할인
    - 날짜 조건: 일 월 화 수 목
    - 할인 조건: 디저트 메뉴 1개당 2023원 할인
- 주말 할인
    - 날짜 조건: 금 , 토
    - 할인 조건: 메인 메뉴 1개당 2023원 할인
- 특별 할인
    - 날짜 조건: 별이 있는 날짜  : 3, 10, 17, 24, 25, 31
    - 할인 조건: 총주문 금액 1000원 할인

### 1.3.2 증정 : 총주문 금액

- 증정 이벤트
    - 이벤트 기간: 12/1 ~ 12/31
    - 증정 조건: 총주문 금액이 12만원 이상 일때, 샴페인 1개 증정
    - 주문서에는 true일 경우 String과 int price를 줘야할텐데 이건 다음 단계 구현 윤곽 잡히면 그 때 메서드 생성

### 1.3.3. 이벤트 배지 : 총 혜택 금액

- 이벤트 point 조건 : 할인 받은 금액 + 증정받은 샴페인 가격
    - 종류 상수
        - 5천 원 이상: 별
        - 1만 원 이상: 트리
        - 2만 원 이상: 산타

### 1.4. 예외 : 모든 예외의 시작은 [ERROR]

1. input 중 날짜 : "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
    - 1 이상 31 이하의 숫자
2. input 중 주문 : "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    - 메뉴 형식이 예시와 다른 경우
    - 단일 메뉴의 개수는 1 이상 20 이하, 다중 메뉴의 총 개수는 20 이하
    - 중복 메뉴, 수량은 상관 X
    - 메뉴판에 없는 메뉴
    - 음료수만 주문시

### 1.5. 이벤트 출력 조건

- 원래 readme 참고하기
- 증정, 할인, 배지 String이 들어가야되는데 없는 경우 : "없음"
- 증정, 할인 가격 들어가는데 없는 경우 : 0원 있는 경우 -n원

# 2. 프로그래밍 요구 사항

## 2.1. 기본

- java 17, Java 코드 컨벤션 가이드 준수, test 다 통과, 기존 패키지 파일명 건들이지 말 것
- indent 2, 함수는 15 line 이내로, else X, switch/case X
- 단위 test 수행
- try-catch로 error 발생 시, 다시 시도하게 구현
- inputView, outputView class 구현

## 2.2. 주의 사항 - 검증 조건, 그냥 조건, 걍 조건

⭐️ 주문 input 들어올시, 초기 검증 조건

- 주문 불가 조건 : 음료만 주문 시

- ⭐️ 이벤트 적용 조건 : 총 주문 금액 10,000원 이상부터 **할인, 증정** 이벤트 적용
    - 3가지 경우의 수
        1. 이벤트가 없는 경우
        2. 할인 이벤트만 있는 경우
        3. 할인 증정 이벤트가 있는 경우

# 3. 구현 설계

## 3.1 기본 구현

### 3.1.1 view

- view
    - input
        - reserveDate()
        - orderMenu()
    - output
        - print()
        - printInitQuestion()
        - printReservationFullDate()
        - printOrderMenus()
        - printOrderMenusPrice()
        - showPromotionMenuData()
        - showEventBenefitData()
        - showEventBenefitPrice()
        - showBadge()
- controller
    - run()
        - book()
            - getDate()
            - getOrders()
        - showCustomerOrder()
        - showCustomerPromotion()
        - showCustomerEvent()
        - showPayment()
        - showBadge()
- util
    - validator
        - formatCheck()
        - orderMenu()
            - maxOrderMenuCount()
            - orderMenusCount()
                - naturalNumberMaxRange()
                    - checkNaturalNumber()
        - date()
    - converter
        - splitOrderDatas()
        - orderMenus()
        - orderMenusCounts()

        - dateToFullDate()
        - toWon()
        - toMinusWon()
        - benefitData()
        - menuData()

### 3.2. 도메인 구현

- Enum : 기본적으로 제공되는 값을 이용해야된느 도메인
    - Menu
        - getMenu() : 존재하는 메뉴 반환 없을 시, 메뉴 없음 반환
        - getTotalOrderPrice() : 주문한 메뉴 총 주문 금액
        - createOrderMenus() : {주문한 메뉴 상수, 개수} Map으로 반환
        - getOrderMenus() : 주문한 메뉴 이름 + 개수 format 된 값 반환
        - getPromotionMenus() : 증정 메뉴 이름 + 개수 format 된 값 반환
        - getPromotionBenefit() : 이벤트 혜택가 반환
        - isMain()
        - isDessert()
        - isDrink()
        - isNotMenu()
    - Month
        - applyDiscountByDate() : 예약 날짜의 할인 종류
            - isDiscountDate()
    - Promotion
        - isPromotion() : promotion이면 {Promotion, Integer 수량} Map 반환
        - setPromotionDatas() : CustomerEvent의 benefitDatas에 들어갈 데이터 생성
    - Discount
        - getDiscounts() : 예약일과 주문에 부합한 할인 정보 반환
            - applyDiscount(): Month를 통해 받아온 discountType을 하위 메서드에 적용
                - dDayDiscount() : 디데일 할인 적용
                - menuTypeDiscount() : 주중 메인 디시 할인 적용 & 주말 디저트 할인 적용
                - starDayDiscount() : 별표친날 할인 적용
    - Badge
        - isBadge()
        - getEventBadgeName() : 존재시 배지 반환, 없을 시 빈문자열 반환

- class : enum을 이용해서 값을 넣어야하는 도메인
    - CustomerOrder : reservationDate, orderMenus, totalOrderPrice
        - 초기화
            - totalOrderPrice()
        - 사용
            - getReservationFullDate()
            - getOrderMenus()
            - getTotalOrderPrice()
            - isEventApplicable()
            - getDiscountDatas()
            - getPromotionData()
            - getPredictPay()
    - CustomerEvent : benefitDatas, discountDatas, promotions, totalDiscountPrice, totalBenefitAmount, badge
        - 초기화
            - setBenefitDatas()
            - setTotalDiscountPrice()
            - setTotalBenefitAmount()
            - setBadge()
            - sumEventValues()
        - 사용
            - getPromotionMenus()
            - getBenefitData()
            - getTotalBenefit()
            - getPredictPay()
            - getBadge()

## 3.3. 핵심 기능 (우선적으로 구현할 것)

⭐️ 핵심 : 할인 로직, 증정품, 배너 지급
⭐️ 공통 : 적용가능 event 묶음

### 3.3.1. 핵심 구현기능 맥락 작성 (글)

1. 할인, 증정 조건 및 계산 로직 구현
2. data 저장 방식 구현 : 어느 도메인에 전체 data 저장할지, dto에는 어떻게 나눠서 값 전달할지
3. 모든 Month boolean 각각 돌면서 true명 해당 event 적용하고 반환값 domain에 저장하기
4. Month에 true인 객체 list 반환 -> 한번에 하고 싶은데 어떻게 해야할까?
    - discountEvent는 대체로 공통된부분이 많고 Month의 영향을 받는다.
    - promotion은 Month 영향을 안받으니 따로 적용
    - ps. todo 주문서에는 true일 경우 String과 int price를 줘야할텐데 이건 다음 단계 구현 윤곽 잡히면 그 때 메서드 생성
5. 이벤트 enum에서 반환하는 Map은 customerEvent에서 map.putAll() 이용해서 값 저장하는 메서드 생성하기
6. event에서 출력 관련 메서드 출력

### 3.3.2. 고민

1. 따로 분리해야 될까? - 이벤트별로 if 조건문까지 동일. 사전에 orderMenu를 한 곳에서 분리해주는게 좋지 않을까?
    ```
    public int getWeekendDiscount(Map<Menu, Integer> orderMenu){
         for(Menu menu:orderMenu.keySet()){
             if(Menu.isMain(menu)){
                 weekendDiscount+=orderMenu.get(menu)*DISCOUNT;
         }
         }
         return weekendDiscount;
     }
    ```
2. enum이여서 순서가 보장되긴 하지만 enum 상수가 많아지거나 잦은 변동으로 인한 위험을 감수해야한다. 그렇다고 index를 넣자니 코드가 복잡해진다.
   어떻게 하는게 좋은 방법일까?
    ```
   public static Badge getBadge(int eventPoint) {
   Badge getBadge = null;
   for (Badge value : Badge.values()) {
   if (value.eventPoint <= eventPoint) {
   getBadge = value;
   }
   }
   return getBadge;
   }
    ```

## 3.4 Unit Test

- [x] Badge
- [x] Month
- [x] Menu
- [x] Promotion
- [x] Discount
- [x] Converter
- [x] validator
- [x] CustomerEvent
- [x] CustomerOrder
