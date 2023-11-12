# 1. 기능 요구사항

## 주의 사항 - 검증 조건, 그냥 조건, 걍 조건

- 이벤트 적용 조건 : 총 주문 금액 10,000원 이상부터 **할인, 증정** 이벤트 적용
- 주문 불가 조건 : 음료만 주문 시
- 메뉴 최대 주문 개수 : 20개

## 개발 요청사항

### 입,출력

- 입력
    - 방문 날짜
    - 메뉴 선택
- 출력
    - 주문 메뉴
        - 순서 X
    - 할인 전 총주문 금액
    - 증정 메뉴
        - 있을 경우 : "샴페인 1개"
        - 없을 경우 : "없음"
    - 혜택 내역 (=할인 내역)
        - 적용된 이벤트 내역만 보여주기
        - 할인 내역 여러개 : 순서 상관없이 출력
        - 없을 경우 : "없음"
    - 총혜택 금액
        - 할인 금액의 합계 + 증정 메뉴의 가격
        - 없을 경우 : 0원
    - 할인 후 예상 결제 금액
        - 할인 전 총주문 금액 - 할인 금액
    - 12월 이벤트 배지
        - 있을 경우 : 해당 배지만 표시
        - 없을 경우 : 없음

### 도메인 필드, 도메인 비즈니스

### 유틸 - 검증, 포맷

- 검증
    - (입력) 방문 날짜 1~31 숫자만 들어감
    - (입력) 있는 메뉴만 작성, 개수 1개 이상, 작성 형식도 동일 해야함, 중복 메뉴 불가
- 포맷
    - (입력) 분리 - 메뉴 와 주문 수량

### enum

- ~~위치 : 도메인~~
    - ~~메뉴 및 가격~~
    - ~~배지~~
    - ~~날짜? 주말, 평일, 별표시 날짜, 디데이~~
- 위치 : 유틸
    - error 문구

## 3.2. 핵심 기능 (우선적으로 구현할 것)

⭐️ 핵심 : 할인 로직, 증정품, 배너 지급
⭐️ 공통 : 적용가능 event 묶음
⭐️ 중간 정리
~~- 고객의 **주문** 및 적용 가능 event -> event 적용 Map<"이벤트",할인금액> 반환받기~~

- 고객의 Map<>, 주문 -> 가격 관련 도메인에 적용
- 가격 적용 도메인 -> 배지

~~1. 할인, 증정 조건 및 계산 로직 구현~~

- ~~패키지 도메인 개발 - 조건에 맞는지, 도메인별 할인률 계산~~

~~2. data 저장 방식 구현 : 어느 도메인에 전체 data 저장할지, dto에는 어떻게 나눠서 값 전달할지~~

- ~~필요 data : 예약 방문 날짜, 주문 메뉴 및 수량~~
  ~~- 총 금액, 증정메뉴, 증정메뉴 가격, 디데이 할인, 평일 할인, 특별할인, 총혜택 금액, 할인 후 예상 금액, 배지~~

~~3. 이벤트 , 주문 결제금액 method 생성~~

~~4. boolean 확인 후, event 적용 - 구현방식
1. 모든 Month boolean 각각 돌면서 true명 해당 event 적용하고 반환값 domain에 저장하기
2. Month에 true인 객체 list 반환 -> 한번에 하고 싶은데 어떻게 해야할까?~~
⭐️ discountEvent는 대체로 공통된부분이 많고 Month의 영향을 받는다. promotion은 Month 영향을 안받으니 따로 적용
~~공통된 변수명들을 enum으로 생성하자 `DDAY("크리스마스 데데이 할인", DISCOUNT)` INCREASE_PRICE 공통된게 아니니 제외~~
~~그리고 메서드들은하나의 클래스로 모으자. 메서드만 있는 static class, 여기에 INCREASE_PRICE 상수 개별로 넣기~~
~~비슷한 메서드들은 오버로딩 할것.~~
~~마지막으로 통합 메서드를 만든다. 해당 조건문일때 내부 discount 메서드들이 작동되도록 조건문에는 enum이 쓰일것
2번을 적용하게 되면 orderMenu map과 date 2개만 넘기면 된다. 넘겨줄때 order method는 main과 dessert만 가도록 분리 메서드 생성필요
분리 method는 util에서 아니 customer orderList 있는 도메인에서 수행
완전 한번에 해결은 안되지만 최대한 수행, 개별로 값을 반환하므로 이벤트 적용 Map 저장 method는 해당 도메인에서 수행~~
~~ps. todo 주문서에는 true일 경우 String과 int price를 줘야할텐데 이건 다음 단계 구현 윤곽 잡히면 그 때 메서드 생성
ex) Map <String eventType, Map<String promotion, Integer price>>~~
~~5. 이벤트 enum에서 반환하는 Map은 customerEvent에서 map.putAll() 이용해서 값 저장하는 메서드 생성하기~~

6. event에서 출력 관련 메서드 출력
7. CustomerPayment domain method들은 출력용으로만 사용되므로 절차적 개발 시 필요 메서드 추가하기
   -> 연습해야되므로 우선 필요한 매개변수를 상상해서 메서드 만들고 난 후 절차적 개발 시 수정하기
8. 이후 절차적 로직 다 수행
9. 세세한 부분 : 검증, 조건 부분 수행 및 전체적 unit test 수행

~~dto: totalOrderCount는 한 3곳에서 제각각 사용되므로 혼자있으면 method내부에 iv로 받고 여러개들이 이지경이 되면 dto로 받기~~

## 고민

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

- - -
다한 것
- - -

# 2. 프로그래밍 요구 사항

- java 17, Java 코드 컨벤션 가이드 준수, test 다 통과, 기존 패키지 파일명 건들이지 말 것
- indent 2, 함수는 15 line 이내로, else X, switch/case X
- 단위 test 수행
- try-catch로 error 발생 시, 다시 시도하게 구현
- inputView, outputView class 구현

# 3. 구현 설계

## 3.1. 패키지 구성

- domain
    - Menu(Enum)
    - DurationEvent
    - WeekDayEvent
    - WeekendEvent
    - StarEvent
    - PromotionEvent
    - December(Enum)
    - Customer
    - InfoDto : 방문일, 선택메뉴
    - EventDto : 증정 메뉴, 혜택 내역, 총 혜택 금액, 배지
    - PriceDto : 할인 전, 할인 후
- util
    - XmasValidator
    - ErrorComment (Enum)
- view
    - input
        - reserveDate() : 방문날짜
        - order() : 음식 선택
    - output
        - showWelcome() : 인삿말
        - askReserveDate() : 방문날짜 묻기
        - askOrder() : 주문
        - showOrderFormHeader() : 주문서 인삿말
        - showOrderForm : 주문, 할인내용들 -> 분리 필요
- controller

## ~~메뉴~~

~~<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
<디저트>
초코케이크(15,000), 아이스크림(5,000)
<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)~~

## ~~달력~~

## ~~목표~~

- ~~혜택, 이벤트~~

## ~~할인, 증정 계획 : 결제 핵심~~

### 할인, 증정 조건

- ~~할인, 증정 기간 : 12/1 ~ 12/31~~

- ~~디데이 할인~~
    - ~~날짜 조건: **12/1 ~ 12/25** : 1000원 -> 이후 100원씩 증가~~
    - ~~할인 조건: 총 주문 금액에서 빼기~~
- ~~평일 할인~~
    - ~~날짜 조건: 일 월 화 수 목~~
    - ~~할인 조건: 디저트 메뉴 1개당 2023원 할인~~
- ~~주말 할인~~
    - ~~날짜 조건: 금 , 토~~
    - ~~할인 조건: 메인 메뉴 1개당 2023원 할인~~
- ~~특별 할인~~
    - ~~날짜 조건: 별이 있는 날짜  : 3, 10, 17, 24, 25, 31~~
    - ~~할인 조건: 총주문 금액 1000원 할인~~

- 증정 이벤트
    - ~~이벤트 기간: 12/1 ~ 12/31~~
    - ~~증정 조건: 총주문 금액이 12만원 이상 일때, 샴페인 1개 증정~~
    - ~~주문서에는 true일 경우 String과 int price를 줘야할텐데 이건 다음 단계 구현 윤곽 잡히면 그 때 메서드 생성~~

### ~~이벤트 배지 부여~~

- ~~새해 이벤트 참여시, 새해 선물 증정예정~~
- ~~이벤트 point 조건 : 할인 받은 금액 + 증정받은 샴페인 가격~~
    - ~~종류 상수~~
        - ~~5천 원 이상: 별~~
        - ~~1만 원 이상: 트리~~
        - ~~2만 원 이상: 산타~~
