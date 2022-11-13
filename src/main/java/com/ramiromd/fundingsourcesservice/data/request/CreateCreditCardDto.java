package com.ramiromd.fundingsourcesservice.data.request;

import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.data.request.contract.CreateFundingSourceInterface;
import lombok.Data;

@Data
public class CreateCreditCardDto extends CreateFundingSourceDto implements CreateFundingSourceInterface {

    private String number;

    private String cardholderName;

    private String expirationDate;

    @Override
    public String getType() {
        return SourceType.CREDIT_CARD;
    }
}
