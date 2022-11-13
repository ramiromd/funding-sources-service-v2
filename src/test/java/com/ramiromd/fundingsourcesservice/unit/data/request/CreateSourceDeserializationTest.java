package com.ramiromd.fundingsourcesservice.unit.data.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;
import com.ramiromd.fundingsourcesservice.helper.ObjectMapperFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSourceDeserializationTest {

    @Test
    public void it_can_deserialize_a_credit_card_request() throws JsonProcessingException {
        ObjectMapper mapper = ObjectMapperFactory.getDefaultMapper();
        String payload = "{\"type\": \"credit_card\", " +
                "\"name\": \"My first credit card\", " +
                "\"number\": \"3428-3071-3218-6112\", " +
                "\"expirationDate\": \"11/26\", " +
                "\"cardholderName\": \"Ramiro Martinez D'Elía\"}";

        CreateCreditCardDto dto = mapper.readValue(payload, CreateCreditCardDto.class);
        assertEquals(dto.getType(), SourceType.CREDIT_CARD);
        assertEquals(dto.getName(), "My first credit card");
        assertEquals(dto.getNumber(), "3428-3071-3218-6112");
        assertEquals(dto.getExpirationDate(), "11/26");
        assertEquals(dto.getCardholderName(), "Ramiro Martinez D'Elía");
    }
}
