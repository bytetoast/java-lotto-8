package lotto.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        checkForDuplicates(numbers);

        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public void checkForDuplicates(List<Integer> numbers) {
        Set<Integer> added = new HashSet<>();
        for (Integer number : numbers) {
            if (!added.add(number)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호에는 중복이 없어야 합니다.");
            }
        }
    }
}
