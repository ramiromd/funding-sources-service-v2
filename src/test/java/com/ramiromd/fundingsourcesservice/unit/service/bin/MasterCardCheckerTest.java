package com.ramiromd.fundingsourcesservice.unit.service.bin;

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
public class MasterCardCheckerTest {

    @Test
    public void should_return_true_when_a_master_card_is_passed() {
        MasterCardChecker checker = new MasterCardChecker();
        List<String> masterCardNumbers = CreditCardNumbers.getMasterCardNumbers();

        for (String number: masterCardNumbers) {
            assertTrue(checker.check(number));

        }
    }

    @Test
    public void should_return_false_when_a_master_card_is_not_passed() {
        MasterCardChecker checker = new MasterCardChecker();
        List<String> nonMasterNumbers = new ArrayList<String>();

        nonMasterNumbers.addAll(CreditCardNumbers.getAmexNumbers());
        nonMasterNumbers.addAll(CreditCardNumbers.getVisaNumbers());

        for (String number: nonMasterNumbers) {
            assertFalse(checker.check(number), number);

        }
    }
}
