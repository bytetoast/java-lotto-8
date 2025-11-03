package lotto.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class LottoMachine {
    private WinningNumbersStatus winningNumbersStatus;
    private List<Integer> winningNumbers;
    private ValidationFactory factory;
    private BonusNumberStatus bonusNumberStatus;

    public void startManipulateWinningNumbersProcess() {
        this.winningNumbersStatus = WinningNumbersStatus.WAITING;
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public boolean getValidWinningNumbers() {
        return this.winningNumbersStatus == WinningNumbersStatus.WAITING;
    }

    public void setWinningNumbers(String winningNumbersInput) {
        List<Integer> newWinningNumbers;

        try {
            matches(winningNumbersInput);
            newWinningNumbers = parseWinningNumbers(winningNumbersInput);
            validateSize(newWinningNumbers.size());
            checkForDuplicates(newWinningNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage() + "\n당첨 번호를 다시 입력하세요.");
            return;
        }
        this.winningNumbers = newWinningNumbers;
        this.winningNumbersStatus = WinningNumbersStatus.VALID;
    }

    public void matches(String winningNumbersInput) {
        final String sixWinningNumbersRegex = "^[0-9]+,[0-9]+,[0-9]+,[0-9]+,[0-9]+,[0-9]+$";

        if (!Pattern.matches(sixWinningNumbersRegex, winningNumbersInput)) {
            throw new IllegalArgumentException("당첨 번호는 쉼표(,)로 구분된 6개의 정수여야 합니다.");
        }
    }

    public List<Integer> parseWinningNumbers(String winningNumbersInput) {
        List<Integer> newWinningNumbers = new ArrayList<>();

        for (String winningNumber : winningNumbersInput.split(",")) {
            int convertedNumber = this.factory.convertToInt(winningNumber);
            newWinningNumbers.add(convertedNumber);
        }
        return newWinningNumbers;
    }

    public void validateSize(int size) {
        if (size != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    public void checkForDuplicates(List<Integer> numbers) {
        Set<Integer> added = new HashSet<>();

        for (Integer number : numbers) {
            if (!added.add(number)) {
                throw new IllegalArgumentException("로또 번호에는 중복이 없어야 합니다.");
            }
        }
    }

    public void startManipulateBonusNumberProcess() {
        this.bonusNumberStatus = BonusNumberStatus.WAITING;
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }
}
