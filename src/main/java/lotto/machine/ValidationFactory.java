package lotto.machine;

public class ValidationFactory {
    public int convertToInt(String numberInput) {
        int numberConverted = -1;
        final String errorMessage = "번호는 1이상 45이하의 정수여야 합니다.";

        try  {
            numberConverted = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (numberConverted < 1 || numberConverted > 45) {
            throw new IllegalArgumentException(errorMessage);
        }
        return numberConverted;
    }
}
