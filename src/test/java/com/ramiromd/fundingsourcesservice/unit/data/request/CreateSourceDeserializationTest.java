package com.ramiromd.fundingsourcesservice.unit.data.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;
import com.ramiromd.fundingsourcesservice.helper.ObjectMapperFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSourceDeserializationTest {

    @Test
    public void it_can_deserialize_a_credit_card_request() throws IOException {
        ObjectMapper mapper = ObjectMapperFactory.getDefaultMapper();
        File input = new File("src/test/resources/stub/create_credit_card_request_1.json");

        CreateCreditCardDto dto = mapper.readValue(input, CreateCreditCardDto.class);
        assertEquals(dto.getType(), SourceType.CREDIT_CARD);
        assertEquals(dto.getName(), "My first credit card");
        assertEquals(dto.getNumber(), "3428-3071-3218-6112");
        assertEquals(dto.getExpirationDate(), "11/26");
        assertEquals(dto.getCardholderName(), "Ramiro Martinez D'Elía");
    }
}
