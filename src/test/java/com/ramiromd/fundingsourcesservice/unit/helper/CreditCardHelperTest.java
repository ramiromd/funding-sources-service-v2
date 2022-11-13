package com.ramiromd.fundingsourcesservice.unit.helper;

import com.ramiromd.fundingsourcesservice.helper.CreditCardHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class CreditCardHelperTest {

    @Test
    public void it_can_sanitize_a_credit_card() {
        String original = "4338-4049-5199-7724";
        String expected = "4338404951997724";
        String sanitized = CreditCardHelper.sanitize(original);

        assertEquals(expected, sanitized);
        // check if the original value is not modified ...
        assertNotEquals(original, sanitized);
    }

    @Test
    public void it_can_return_a_credit_card_bin() {
        String input = "4338-4049-5199-7724";
        String expected = "433840";
        assertEquals(expected, CreditCardHelper.getBin(input));
    }

    @Test
    public void it_can_return_last_four_digits() {
        String input = "4338-4049-5199-7724";
        String expected = "7724";
        assertEquals(expected, CreditCardHelper.getLastFour(input));
    }

}
