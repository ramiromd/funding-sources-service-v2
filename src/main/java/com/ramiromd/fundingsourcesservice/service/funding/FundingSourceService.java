package com.ramiromd.fundingsourcesservice.service.funding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.data.request.CreateCreditCardDto;
import com.ramiromd.fundingsourcesservice.data.response.FundingSourceCreatedDto;
import com.ramiromd.fundingsourcesservice.data.request.contract.CreateFundingSourceInterface;
import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.entity.FundingSource;
import com.ramiromd.fundingsourcesservice.helper.ObjectMapperFactory;
import com.ramiromd.fundingsourcesservice.repository.FundingSourceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FundingSourceService {

    final private FundingSourceRepository repository;

    final private CreditCardService creditCardService;

    public FundingSourceService(FundingSourceRepository repository, CreditCardService creditCardService) {
        this.repository = repository;
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

    public void delete(Long id, String userId) {

        FundingSource source;
        Optional<FundingSource> result = this.repository.findById(id);

        if (result.isEmpty()) {
            throw new EntityNotFoundException();
        }

        source = result.get();

        if (!source.getUserId().equals(userId)) {
            throw new SecurityException();
        }

        source.setDeletedAt(LocalDateTime.now());
        this.repository.save(source);
    }
}
