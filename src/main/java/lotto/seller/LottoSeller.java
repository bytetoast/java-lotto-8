package lotto.seller;

public class LottoSeller {
    private PurchaseStatus purchaseStatus;
    private int amountPaid;
    private int lottosCount;

    public LottoSeller() {
        this.purchaseStatus = PurchaseStatus.IDLE;
        this.amountPaid = 0;
        this.lottosCount = 0;
    }
}
