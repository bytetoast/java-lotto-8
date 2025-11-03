package lotto;

import lotto.customer.Customer;
import lotto.seller.LottoSeller;

public class Application {
    public static void main(String[] args) {
        Customer customer = new Customer();
        LottoSeller lottoSeller = new LottoSeller();

        customer.dealWithLottoSeller(lottoSeller);
    }
}
