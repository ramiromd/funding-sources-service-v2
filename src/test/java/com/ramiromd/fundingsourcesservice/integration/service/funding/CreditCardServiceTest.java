package com.ramiromd.fundingsourcesservice.integration.service.funding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;
import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.helper.ObjectMapperFactory;
import com.ramiromd.fundingsourcesservice.repository.CreditCardRepository;
import com.ramiromd.fundingsourcesservice.service.funding.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CreditCardServiceTest {

    @Autowired
    CreditCardService service;

    @Autowired
    CreditCardRepository repository;

    @Test
    public void it_can_create_a_credit_card() throws IOException {
        CreditCard card;
        ObjectMapper mapper = ObjectMapperFactory.getDefaultMapper();
        File input = new File("src/test/resources/stub/create_credit_card_request_1.json");
        CreateCreditCardDto dto = mapper.readValue(input, CreateCreditCardDto.class);

        card = service.create(dto, UUID.randomUUID().toString());
        assertNotNull(card.getId());
        assertEquals(1, repository.count());
    }
}
