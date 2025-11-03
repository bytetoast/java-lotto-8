package lotto.machine;


public class LottoMachine {
    private WinningNumbersStatus winningNumbersStatus;

    public void startManipulateWinningNumbersProcess() {
        this.winningNumbersStatus = WinningNumbersStatus.WAITING;
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }
}
