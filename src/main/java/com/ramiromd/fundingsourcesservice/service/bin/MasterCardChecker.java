package com.ramiromd.fundingsourcesservice.service.bin;

import com.ramiromd.fundingsourcesservice.helper.CreditCardHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MasterCardChecker implements BinChecker {

    private static String REGEX = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";

    @Override
    public boolean check(String aCreditCardNumber) {
        String sanitized = CreditCardHelper.sanitize(aCreditCardNumber);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(sanitized);
        return matcher.find();
    }
}
