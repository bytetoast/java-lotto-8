package lotto.seller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.lotto.Lotto;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LottoSeller {
    private PurchaseStatus purchaseStatus;
    private int amountPaid;
    private int lottosCount;
    private static final String paidInputRegex = "^[1-9][0-9]*000$";

    public LottoSeller() {
        this.purchaseStatus = PurchaseStatus.IDLE;
        this.amountPaid = 0;
        this.lottosCount = 0;
    }

    public void startSaleProcess() {
        this.purchaseStatus = PurchaseStatus.WAITING;
        System.out.println("구입금액을 입력해 주세요.");
    }

    public boolean getValidPurchase() {
        return this.purchaseStatus == PurchaseStatus.WAITING;
    }

    public void receiveAndValidatePurchase(String amountPaidInput) {
        try {
            matchesPattern(amountPaidInput);
            this.amountPaid = tryParsing(amountPaidInput);
            this.lottosCount = this.amountPaid/1000;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n구매금액을 다시 입력해주세요.");
            return;
        }
        this.purchaseStatus = PurchaseStatus.VALID;
        System.out.println("\n" + this.lottosCount + "개를 구매했습니다.");
    }

    public void matchesPattern(String amountPaidInput) {
        if (!Pattern.matches(paidInputRegex, amountPaidInput)) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1,000원으로 나누어 떨어지는 정수여야 합니다.");
        }
    }

    public int tryParsing(String amountPaidInput) {
        int amountPaidConverted;

        try {
            amountPaidConverted = Integer.parseInt(amountPaidInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1,000원으로 나누어 떨어지는 정수여야 합니다.");
        }
        return amountPaidConverted;
    }

    public List<Lotto> giveLottos() {
        List<Lotto> newLottos = new ArrayList<>();
        for (int index = 0; index < this.lottosCount; index++) {
            List<Integer> quickPick = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            System.out.println(quickPick);
            newLottos.add(new Lotto(quickPick));
        }
        this.purchaseStatus = PurchaseStatus.VALID;
        return newLottos;
    }

    public int getAmountPaid() {
        return this.amountPaid;
    }
}
