package com.ramiromd.fundingsourcesservice.service.bin;

import com.ramiromd.fundingsourcesservice.helper.CreditCardHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisaChecker implements BinChecker {

    private static String REGEX = "^4[0-9]{6,}$";

    @Override
    public boolean check(String aCreditCardNumber) {
        String sanitized = CreditCardHelper.sanitize(aCreditCardNumber);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(sanitized);
        return matcher.find();
    }
}
