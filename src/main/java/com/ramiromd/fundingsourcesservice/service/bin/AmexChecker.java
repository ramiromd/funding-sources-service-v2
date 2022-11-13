package com.ramiromd.fundingsourcesservice.service.bin;

public class AmexChecker extends AbstractChecker implements BinChecker {

    @Override
    protected String getRegex() {
        return "^3[47][0-9]{5,}$";
    }
}
