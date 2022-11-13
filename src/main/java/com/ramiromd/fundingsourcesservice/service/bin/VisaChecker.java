package com.ramiromd.fundingsourcesservice.service.bin;

public class VisaChecker extends AbstractChecker implements BinChecker {

    @Override
    protected String getRegex() {
        return "^4[0-9]{6,}$";
    }
}
