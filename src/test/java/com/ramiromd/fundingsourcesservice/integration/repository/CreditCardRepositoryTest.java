package com.ramiromd.fundingsourcesservice.integration.repository;

import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.entity.BankAccount;
import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.repository.CreditCardRepository;
import com.ramiromd.fundingsourcesservice.util.seeder.SourceEntitySeeder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CreditCardRepositoryTest {

    @Autowired
    private CreditCardRepository repository;

    @Autowired
    private SourceEntitySeeder sourceEntitySeeder;

    private CreditCard createDefaultCard() {
        CreditCard aCreditCard = new CreditCard();
        aCreditCard.setUserId(UUID.randomUUID().toString());
        aCreditCard.setName("Test credit card");
        aCreditCard.setCardholderName("Ramiro Martínez D'Elía");
        aCreditCard.setBin("123456");
        aCreditCard.setLastFourDigits("1234");
        aCreditCard.setBrand(CardBrandEnum.VISA);
        aCreditCard.setExpirationDate("11/23");
        return aCreditCard;
    }

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
        CreditCard aCreditCard = this.createDefaultCard();
        this.repository.save(aCreditCard);
        assertNotNull(aCreditCard.getId());
        assertNotNull(aCreditCard.getCreatedAt());
        assertEquals(SourceType.CREDIT_CARD, aCreditCard.getType());
    }

    @Test
    public void should_return_repository_size_when_is_not_empty() {
        int count = 10;

        for (int i = 0; i < count; i++) {
            CreditCard aCreditCard = this.createDefaultCard();
            this.repository.save(aCreditCard);
        }

        assertEquals(count, this.repository.count());
    }

    @Test
    public void should_return_only_credit_cards() {
        int expectedCount = 6;
        // Create 6 random credit cards & 2 random bank accounts
        this.sourceEntitySeeder.createManySources(expectedCount, 2);
        List<CreditCard> cards = this.repository.findAll();

        assertEquals(expectedCount, cards.size());
        for (CreditCard card: cards) {
            assertEquals(SourceType.CREDIT_CARD, card.getType());
        }
    }
}
