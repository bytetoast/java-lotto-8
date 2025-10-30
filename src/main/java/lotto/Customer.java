package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.text.NumberFormat;
import java.util.Set;

public class Customer {
    private List<Lotto> lottos;
    private int[] counts;
    private int amountPaid;
    private int[] prizes = {5000, 50000, 1500000, 30000000, 2000000000};
    private String[] matchingNumbers = {"3개", "4개", "5개", "5개 일치, 보너스 볼", "6개"};

    public Customer() {
        this.lottos = new ArrayList<>();
        this.counts = new int[5];
        this.amountPaid = 0;
    }

    public String submitPurchase() {
        return Console.readLine();
    }

    public void receiveLottos(List<Lotto> lottosReceived) {
        this.lottos = lottosReceived;
    }

    public void summarize(List<Integer> winningNumbers, int bonusNumber, int amountPaid) {
        System.out.println("당첨 통계\n---");
        int totalPrize = 0;
        float payoutRatio = 0;
        this.calculate(winningNumbers, bonusNumber);
        int i;
        for (i = 0; i < 5; i++) {
            NumberFormat formatter = NumberFormat.getInstance();
            totalPrize += this.counts[i]*this.prizes[i];
            System.out.println(matchingNumbers[i] + " 일치 (" + formatter.format(this.prizes[i]) + "원) - " + this.counts[i] + "개");
        }
        payoutRatio = (float) totalPrize /amountPaid*100;
        System.out.println("총 수익률은 "+ String.format("%.1f", payoutRatio) +"%입니다.");
    }

    public void calculate(List<Integer> winningNumbers, int bonusNumber) {
        Set<Integer> winningNumbersPool = new HashSet<>(winningNumbers);
        for (Lotto currLotto : this.lottos) {
            String matchResult = this.getMatchCount(winningNumbersPool, currLotto, bonusNumber);
            int matchCount = Integer.parseInt(matchResult.split(":")[0]);
            String matchesBonusNumber = matchResult.split(":")[1];
            this.determinePrize(matchCount, matchesBonusNumber);
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
