package lotto.customer;

import lotto.Lotto;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int amountPaid;
    private int[] counts;
    private List<Lotto> lottos;

    public Customer() {
        this.amountPaid = 0;
        this.counts = new int[5];
        this.lottos = new ArrayList<>();
    }


}
