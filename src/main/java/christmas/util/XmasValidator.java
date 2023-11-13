package christmas.util;

public class XmasValidator {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String ERROR_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MAX_DATE = 31;


    public static void date(String date) {
        int numberDate = checkNaturalNumber(date);
        if (numberDate > MAX_DATE){
            illegalState(ERROR_DATE);
        }
    }
    private static int checkNaturalNumber(String date) {
        if (!date.matches("^[1-9]\\d*$")) { // 자연수 정규식
            illegalArgument(ERROR_DATE);
        }
        return Integer.parseInt(date);
    }
    private static void illegalArgument(String errorMessage) {
        throw new IllegalArgumentException(ERROR_MESSAGE + errorMessage);
    }
    private static void illegalState(String errorMessage) {
        throw new IllegalStateException(ERROR_MESSAGE + errorMessage);
    }
}
