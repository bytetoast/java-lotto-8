package lotto;

public class Application {
    public static void main(String[] args) {
        Customer customer = new Customer();
        LottoSeller lottoSeller = new LottoSeller();
        LottoMachine lottoMachine = new LottoMachine();
        Operator operator = new Operator();

        lottoSeller.startSaleProcess();
        while (lottoSeller.getValidPurchase()) {
            lottoSeller.receiveAndValidatePurchase(customer.submitPurchase());
        }
        customer.receiveLottos(lottoSeller.giveLottos());
        lottoMachine.startManipulateWinningNumbersProcess();
        while (lottoMachine.getValidWinningNumbers()) {
            lottoMachine.setWinningNumbers(operator.submitWinningNumbers());
        }
        lottoMachine.startManipulateBonusNumberProcess();
        while (lottoMachine.getValidBonusNumber()) {
            lottoMachine.setBonusNumber(operator.submitBonusNumber());
        }
        customer.summarize(lottoMachine.getWinningNumbersNumber(), lottoMachine.getBonusNumber(), lottoSeller.getAmountPaid());
    }
}
