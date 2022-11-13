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

    /**
     * Return the first 6 digits
     * @return String
     */
    public static String getBin(String aCreditCardNumber) {
        return  sanitize(aCreditCardNumber).substring(0, 6);
    }

    /**
     * Return the last 4 digits
     * @return String
     */
    public static String getLastFour(String aCreditCardNumber) {
        String sanitized = sanitize(aCreditCardNumber);
        String value = sanitized.substring(sanitized.length() - 4);
        return value;
    }
}
