package com.ramiromd.fundingsourcesservice.service.bin;

import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;

public class AmexChecker extends AbstractChecker implements BinChecker {

    @Override
    protected String getRegex() {
        return "^3[47][0-9]{5,}$";
    }

    @Override
    public CardBrandEnum getBrand() {
        return CardBrandEnum.AMEX;
    }
}
