package com.ramiromd.fundingsourcesservice.integration.service.bin;

import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;
import com.ramiromd.fundingsourcesservice.service.bin.BinService;
import com.ramiromd.fundingsourcesservice.util.helper.CreditCardNumbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BinServiceTest {

    @Autowired
    private BinService binService;

    @ParameterizedTest
    @MethodSource("creditCardNumbersProvider")
    public void it_can_guess_a_credit_card_brand(
            String aCreditCardNumber,
            CardBrandEnum expectedBrand
    ) {
        assertEquals(this.binService.guessCreditCardBrand(aCreditCardNumber), expectedBrand);
    }

    public static Stream<Arguments> creditCardNumbersProvider() {
        return Stream.of(
                Arguments.of(CreditCardNumbers.getOneAmexNumber(), CardBrandEnum.AMEX),
                Arguments.of(CreditCardNumbers.getOneVisaNumber(), CardBrandEnum.VISA),
                Arguments.of(CreditCardNumbers.getOneMasterNumber(), CardBrandEnum.MASTER),
                Arguments.of(CreditCardNumbers.getOneJcbNumber(), CardBrandEnum.OTHER),
                Arguments.of(CreditCardNumbers.getOneDiscoverNumber(), CardBrandEnum.OTHER)
        );
    }
}
