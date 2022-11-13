package com.ramiromd.fundingsourcesservice.service.bin;

import com.ramiromd.fundingsourcesservice.helper.CreditCardHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class AbstractChecker {

    public boolean check(String aCreditCardNumber) {
        String sanitized = CreditCardHelper.sanitize(aCreditCardNumber);
        Pattern pattern = Pattern.compile(this.getRegex());
        Matcher matcher = pattern.matcher(sanitized);
        return matcher.find();
    }

    abstract protected String getRegex();
}
