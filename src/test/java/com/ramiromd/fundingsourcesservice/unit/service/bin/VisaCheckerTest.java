package com.ramiromd.fundingsourcesservice.unit.service.bin;

import com.ramiromd.fundingsourcesservice.service.bin.VisaChecker;
import com.ramiromd.fundingsourcesservice.util.helper.CreditCardNumbers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class VisaCheckerTest {

    @Test
    public void should_return_true_when_a_visa_card_is_passed() {
        VisaChecker checker = new VisaChecker();
        List<String> visaNumbers = CreditCardNumbers.getVisaNumbers();

        for (String number: visaNumbers) {
            assertTrue(checker.check(number));

        }
    }

    @Test
    public void should_return_false_when_a_visa_card_is_not_passed() {
        VisaChecker checker = new VisaChecker();
        List<String> masterCardNumbers = CreditCardNumbers.getMasterCardNumbers();
        List<String> amexNumbers = CreditCardNumbers.getAmexNumbers();
        List<String> nonVisaNumbers = new ArrayList<String>();
        nonVisaNumbers.addAll(amexNumbers);
        nonVisaNumbers.addAll(masterCardNumbers);

        for (String number: nonVisaNumbers) {
            assertFalse(checker.check(number));

        }
    }
}
