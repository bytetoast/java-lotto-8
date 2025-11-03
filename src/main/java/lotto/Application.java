package lotto;

import lotto.customer.Customer;
import lotto.machine.LottoMachine;
import lotto.operator.Operator;
import lotto.seller.LottoSeller;

public class Application {
    public static void main(String[] args) {
        Customer customer = new Customer();
        LottoSeller lottoSeller = new LottoSeller();
        LottoMachine lottoMachine = new LottoMachine();
        Operator operator = new Operator();

        customer.dealWithLottoSeller(lottoSeller);
        operator.manipulateLottoMachine(lottoMachine);
    }
}
