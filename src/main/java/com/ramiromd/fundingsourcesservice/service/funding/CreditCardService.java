package com.ramiromd.fundingsourcesservice.service.funding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;
import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.helper.CreditCardHelper;
import com.ramiromd.fundingsourcesservice.helper.ObjectMapperFactory;
import com.ramiromd.fundingsourcesservice.repository.CreditCardRepository;
import com.ramiromd.fundingsourcesservice.service.bin.BinService;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    final
    private CreditCardRepository repository;

    private final
    BinService binService;

    public CreditCardService(CreditCardRepository repository, BinService binService) {
        this.repository = repository;
        this.binService = binService;
    }

    public CreditCard create(CreateCreditCardDto dto, String userId) {
        ObjectMapper mapper = ObjectMapperFactory.getDefaultMapper();
        CreditCard card = mapper.convertValue(dto, CreditCard.class);

        // set custom values
        card.setUserId(userId);
        card.setBin(CreditCardHelper.getBin(dto.getNumber()));
        card.setLastFourDigits(CreditCardHelper.getLastFour(dto.getNumber()));
        card.setBrand(binService.guessCreditCardBrand(dto.getNumber()));

        this.repository.save(card);
        return card;
    }
}
