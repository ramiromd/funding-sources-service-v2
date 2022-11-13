package com.ramiromd.fundingsourcesservice.unit.service.bin;

import com.ramiromd.fundingsourcesservice.service.bin.AmexChecker;
import com.ramiromd.fundingsourcesservice.service.bin.MasterCardChecker;
import com.ramiromd.fundingsourcesservice.service.bin.VisaChecker;
import com.ramiromd.fundingsourcesservice.util.helper.CreditCardNumbers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AmexCheckerTest {

    @Test
    public void should_return_true_when_a_amex_card_is_passed() {
        AmexChecker checker = new AmexChecker();
        List<String> amexNumbers = CreditCardNumbers.getAmexNumbers();

        for (String number: amexNumbers) {
            assertTrue(checker.check(number));

        }
    }

    @Test
    public void should_return_false_when_an_amex_card_is_not_passed() {
        AmexChecker checker = new AmexChecker();
        List<String> nonAmexNumbers = new ArrayList<String>();

        nonAmexNumbers.addAll(CreditCardNumbers.getMasterCardNumbers());
        nonAmexNumbers.addAll(CreditCardNumbers.getVisaNumbers());

        for (String number: nonAmexNumbers) {
            assertFalse(checker.check(number), number);

        }
    }
}
