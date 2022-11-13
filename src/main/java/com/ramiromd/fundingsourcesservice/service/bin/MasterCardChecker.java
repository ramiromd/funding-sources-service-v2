package com.ramiromd.fundingsourcesservice.service.bin;


import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;

public class MasterCardChecker extends AbstractChecker implements BinChecker {

    @Override
    protected String getRegex() {
        return "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";
    }

    @Override
    public CardBrandEnum getBrand() {
        return CardBrandEnum.MASTER;
    }
}
