package com.ramiromd.fundingsourcesservice.service.funding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;
import com.ramiromd.fundingsourcesservice.data.request.FundingSourceCreatedDto;
import com.ramiromd.fundingsourcesservice.data.request.contract.CreateFundingSourceInterface;
import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.helper.ObjectMapperFactory;
import org.springframework.stereotype.Service;

@Service
public class FundingSourceService {

    final private CreditCardService creditCardService;

    public FundingSourceService(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public FundingSourceCreatedDto create(CreateFundingSourceInterface dto, String userId) throws Exception {

        FundingSourceCreatedDto output;
        ObjectMapper mapper = ObjectMapperFactory.getDefaultMapper();

        switch (dto.getType()) {
            case SourceType.CREDIT_CARD:
                CreditCard creditCard = creditCardService.create((CreateCreditCardDto) dto, userId);
                output = mapper.convertValue(creditCard, FundingSourceCreatedDto.class);
                break;
            default:
                throw new Exception("Wrong funding source passed !");
        }

        return output;
    }
}
