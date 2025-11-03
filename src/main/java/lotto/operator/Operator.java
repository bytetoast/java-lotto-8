package lotto.operator;

import camp.nextstep.edu.missionutils.Console;
import lotto.machine.LottoMachine;

public class Operator {
    public void manipulateLottoMachine(LottoMachine lottoMachine) {
        lottoMachine.startManipulateWinningNumbersProcess();
        while (lottoMachine.getValidWinningNumbers()) {
            lottoMachine.setWinningNumbers(submitWinningNumbers());
        }
        lottoMachine.startManipulateBonusNumberProcess();
        while (lottoMachine.getValidBonusNumber()) {
            lottoMachine.setBonusNumber(submitBonusNumber());
        }
    }

    public String submitWinningNumbers() {
        return Console.readLine().trim();
    }

    public String submitBonusNumber() {
        return Console.readLine().trim();
    }
}
