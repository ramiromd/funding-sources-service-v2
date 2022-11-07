package com.ramiromd.fundingsourcesservice.integration.repository;

import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CreditCardRepositoryTest {

    @Autowired
    private CreditCardRepository repository;

    @Test
    public void should_return_an_empty_collection_when_repository_is_empty() {
        List<CreditCard> creditCards = this.repository.findAll();
        assertTrue(creditCards.isEmpty());
    }

    @Test
    public void should_thrown_an_exception_when_insert_an_empty_card() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            CreditCard aCard = new CreditCard();
            this.repository.save(aCard);
        });
    }
}
