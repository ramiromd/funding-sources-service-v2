package com.ramiromd.fundingsourcesservice.integration.repository;

import com.ramiromd.fundingsourcesservice.entity.FundingSource;
import com.ramiromd.fundingsourcesservice.repository.FundingSourceRepository;
import com.ramiromd.fundingsourcesservice.util.seeder.SourceEntitySeeder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class FundingSourceRepositoryTest {

    @Autowired
    private FundingSourceRepository repository;

    @Autowired
    private SourceEntitySeeder sourceEntitySeeder;

    @Test
    public void should_return_an_empty_collection_when_repository_is_empty() {
        List<FundingSource> fundingSources = this.repository.findAll();
        assertTrue(fundingSources.isEmpty());
    }

    @Test
    public void should_return_all_funding_sources_when_repository_is_not_empty() {
        int creditCardCount = 10;
        int bankAccountCount = 6;
        int expectedCount = creditCardCount + bankAccountCount;

        this.sourceEntitySeeder.createManySources(creditCardCount, bankAccountCount);
        List<FundingSource> fundingSources = this.repository.findAll();

        assertEquals(expectedCount, fundingSources.size());
    }

}
