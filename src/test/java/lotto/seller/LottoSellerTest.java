package lotto.seller;

import lotto.lotto.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoSellerTest {

    @Test
    void receiveAndValidatePurchase() {

    }

    @Test
    void MatchesPatternNonIntegerTest() {
        assertThatThrownBy(() -> {
            new LottoSeller().matchesPattern("A1000");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void matchesPatternIndivisibleTest() {
        assertThatThrownBy(() -> {
            new LottoSeller().matchesPattern("2009");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void tryParsingTest() {
        assertThatThrownBy(() -> {
            new LottoSeller().tryParsing("abcd000");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}