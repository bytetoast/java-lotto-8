package lotto.customer;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.text.NumberFormat;
import java.util.Set;
import lotto.lotto.Lotto;
import lotto.seller.LottoSeller;

public class Customer {
    private static final int[] prizes = {5000, 50000, 1500000, 30000000, 2000000000};
    private static final String[] matchingNumbers = {"3개", "4개", "5개", "5개 일치, 보너스 볼", "6개"};

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
        receiveLottos(lottoSeller.giveLottos());
    }

    public String submitPurchase() {
        return Console.readLine().trim();
    }

    public void receiveLottos(List<Lotto> lottosReceived) {
        this.lottos = lottosReceived;
    }

    public void summarize(List<Integer> winningNumbers, int bonusNumber, int amountPaid) {
        System.out.println("당첨 통계\n---");
        int totalPrize = 0;

        calculate(winningNumbers, bonusNumber);
        int index;
        for (index = 0; index < 5; index++) {
            NumberFormat formatter = NumberFormat.getInstance();
            totalPrize += this.counts[index]*this.prizes[index];
            System.out.println(matchingNumbers[index] + " 일치 (" + formatter.format(this.prizes[index]) + "원) - " + this.counts[index] + "개");
        }
    }

    public void calculate(List<Integer> winningNumbers, int bonusNumber) {
        Set<Integer> winningNumbersPool = new HashSet<>(winningNumbers);
        for (Lotto currLotto : this.lottos) {
            String matchResult = getMatchCount(winningNumbersPool, currLotto, bonusNumber);
            int matchCount = Integer.parseInt(matchResult.split(":")[0]);
            String matchesBonusNumber = matchResult.split(":")[1];
            determinePrize(matchCount, matchesBonusNumber);
        }
    }

    public String getMatchCount(Set set, Lotto lotto, int bonusNumber) {
        int count = 0;
        boolean matchesBonusNumber = false;
        for (int number : lotto.getNumbers()) {
            if (set.contains(number)) {
                count += 1;
            }
            if (number == bonusNumber) {
                matchesBonusNumber = true;
            }
        }
        return count + ":" + String.valueOf(matchesBonusNumber);
    }

    public void determinePrize(int count, String matchesBonusNumber) {
        if (count == 3) {
            this.counts[0]++;
        }
        if (count == 4) {
            this.counts[1]++;
        }
        if (count == 5 && matchesBonusNumber.equals("false")) {
            this.counts[2]++;
        }
        if (count == 5 && matchesBonusNumber.equals("true")) {
            this.counts[3]++;
        }
        if (count == 6) {
            this.counts[4]++;
        }
    }
}
