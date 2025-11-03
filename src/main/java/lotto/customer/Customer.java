package lotto.customer;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import lotto.lotto.Lotto;
import lotto.seller.LottoSeller;

public class Customer {
    private int amountPaid;
    private int[] counts;
    private List<Lotto> lottos;

    public Customer() {
        this.amountPaid = 0;
        this.counts = new int[5];
        this.lottos = new ArrayList<>();
    }

    public void dealWithLottoSeller(LottoSeller lottoSeller) {
        lottoSeller.startSaleProcess();
        while (lottoSeller.getValidPurchase()) {
            lottoSeller.receiveAndValidatePurchase(submitPurchase());
        }
    }

    public String submitPurchase() {
        return Console.readLine().trim();
    }
}
