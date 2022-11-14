package com.ramiromd.fundingsourcesservice.integration.service.funding;

import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;
import com.ramiromd.fundingsourcesservice.data.response.FundingSourceCreatedDto;
import com.ramiromd.fundingsourcesservice.data.request.contract.CreateFundingSourceInterface;
import com.ramiromd.fundingsourcesservice.helper.ObjectMapperFactory;
import com.ramiromd.fundingsourcesservice.service.funding.FundingSourceService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class FundingSourceServiceTest {

    @Autowired
    FundingSourceService service;

    @ParameterizedTest
    @MethodSource("creationDataProvider")
    public void should_return_generic_source_when_a_specific_source_is_created(
            String serializedDataFile,
            String sourceName,
            String specificType
    ) throws Exception {
        FundingSourceCreatedDto sourceDto = prepareAndCreateSource(serializedDataFile, specificType);

        assertNotNull(sourceDto.getId());
        assertEquals(sourceName, sourceDto.getName());
        assertEquals(specificType, sourceDto.getType());
        assertNotNull(sourceDto.getCreatedAt());
    }

    private FundingSourceCreatedDto prepareAndCreateSource(
            String serializedDataFile,
            String specificType
    ) throws Exception {

        File input = new File(serializedDataFile);
        String userId = UUID.randomUUID().toString();
        CreateFundingSourceInterface creationDto;

        if (specificType.equals(SourceType.CREDIT_CARD)) {
            creationDto = ObjectMapperFactory.getDefaultMapper().readValue(input, CreateCreditCardDto.class);
            return service.create(creationDto, userId);
        }

        return new FundingSourceCreatedDto();
    }

    public static Stream<Arguments> creationDataProvider() {
        return Stream.of(
                Arguments.of(
                        "src/test/resources/stub/create_credit_card_request_1.json",
                        "My first credit card",
                        SourceType.CREDIT_CARD
                )
        );
    }
}
