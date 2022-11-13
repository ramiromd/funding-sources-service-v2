package com.ramiromd.fundingsourcesservice.service.bin;

import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;

public class VisaChecker extends AbstractChecker implements BinChecker {

    @Override
    protected String getRegex() {
        return "^4[0-9]{6,}$";
    }

    @Override
    public CardBrandEnum getBrand() {
        return CardBrandEnum.VISA;
    }
}
