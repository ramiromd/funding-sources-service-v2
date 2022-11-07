package com.ramiromd.fundingsourcesservice.integration.repository;

import com.github.javafaker.Faker;
import com.ramiromd.fundingsourcesservice.entity.BankAccount;
import com.ramiromd.fundingsourcesservice.repository.BankAccountRepository;
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
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository repository;

    private BankAccount createDefaultAccount() {
        Faker faker  = new Faker();
        BankAccount aBankAccount = new BankAccount();

        aBankAccount.setUserId(UUID.randomUUID().toString());
        aBankAccount.setName("Test bank account");
        aBankAccount.setBankName(faker.company().name());
        aBankAccount.setNumber(faker.finance().iban());
        aBankAccount.setOwner(faker.name().fullName());
        return aBankAccount;
    }

    @Test
    public void should_return_an_empty_collection_when_repository_is_empty() {
        List<BankAccount> bankAccount = this.repository.findAll();
        assertTrue(bankAccount.isEmpty());
    }

    @Test
    public void should_thrown_an_exception_when_insert_an_empty_card() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            BankAccount anAccount = new BankAccount();
            this.repository.save(anAccount);
        });
    }

    @Test
    public void it_can_persist_a_credit_card() {
        BankAccount anAccount = this.createDefaultAccount();
        this.repository.save(anAccount);
        assertNotNull(anAccount.getId());
        assertNotNull(anAccount.getCreatedAt());
    }

    @Test
    public void should_return_repository_size_when_is_not_empty() {
        int count = 10;

        for (int i = 0; i < count; i++) {
            BankAccount anAccount = this.createDefaultAccount();
            this.repository.save(anAccount);
        }

        assertEquals(count, this.repository.count());
    }

    public void should_return_only_credit_cards() {
        // TODO: Implement when exists other funding sources ...
    }
}
