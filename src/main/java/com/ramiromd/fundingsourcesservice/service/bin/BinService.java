package com.ramiromd.fundingsourcesservice.service.bin;

import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BinService {

    protected ArrayList<BinChecker> checkers;

    BinService() {
        this.checkers = new ArrayList<>();
        this.initializeCheckers();

    }

    private void initializeCheckers() {
        this.checkers.add(new AmexChecker());
        this.checkers.add(new MasterCardChecker());
        this.checkers.add(new VisaChecker());
    }

    public CardBrandEnum guessCreditCardBrand(String aCreditCardNumber) {

        CardBrandEnum brand = CardBrandEnum.OTHER;

        for (BinChecker checker: this.checkers) {
            if (checker.check(aCreditCardNumber)) {
                brand = checker.getBrand();
            }
        }

        return brand;
    }
}
