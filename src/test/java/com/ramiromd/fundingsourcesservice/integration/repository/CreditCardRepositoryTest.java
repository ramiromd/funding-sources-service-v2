package com.ramiromd.fundingsourcesservice.integration.repository;

import com.github.javafaker.Faker;
import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;
import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void it_can_persist_a_credit_card() {
        CreditCard aCreditCard = new CreditCard();
        aCreditCard.setUserId(UUID.randomUUID().toString());
        aCreditCard.setName("Test credit card");
        aCreditCard.setCardholderName("Ramiro Martínez D'Elía");
        aCreditCard.setBin("123456");
        aCreditCard.setLastFourDigits("1234");
        aCreditCard.setBrand(CardBrandEnum.VISA);
        aCreditCard.setExpirationDate("11/23");

        this.repository.save(aCreditCard);
        assertNotNull(aCreditCard.getId());
        assertNotNull(aCreditCard.getCreatedAt());
    }

    public void should_return_only_credit_cards() {
        // TODO: Implement when exists other funding sources ...
    }
}
