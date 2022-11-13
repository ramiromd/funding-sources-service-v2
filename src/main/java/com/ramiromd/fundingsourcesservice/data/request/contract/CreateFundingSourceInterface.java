package com.ramiromd.fundingsourcesservice.data.request.contract;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        // Discriminator: A nivel de JSON. Luego, no lo tiene en cuenta para convertir a objeto.
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateCreditCardDto.class, name = SourceType.CREDIT_CARD),
})
public interface CreateFundingSourceInterface {

    String getType();
}
