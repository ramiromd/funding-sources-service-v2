package com.ramiromd.fundingsourcesservice.service.bin;

import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;

/**
 * @see "https://stackoverflow.com/a/72801"
 */
public interface BinChecker {

    boolean check(String creditCardNumber);

    CardBrandEnum getBrand();
}
