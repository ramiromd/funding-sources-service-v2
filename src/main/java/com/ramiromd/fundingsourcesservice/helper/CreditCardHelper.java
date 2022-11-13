package com.ramiromd.fundingsourcesservice.helper;

public class CreditCardHelper {

    /**
     * Clean all non digits from aCreditCardNumber
     * @return String
     */
    public static String sanitize(String aCreditCardNumber) {
        String pattern = "\\D";
        return aCreditCardNumber.replaceAll(pattern, "");
    }
}
