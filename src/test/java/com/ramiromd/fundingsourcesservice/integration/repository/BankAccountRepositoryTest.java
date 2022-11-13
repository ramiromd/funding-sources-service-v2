package com.ramiromd.fundingsourcesservice.integration.repository;

import com.github.javafaker.Faker;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import com.ramiromd.fundingsourcesservice.entity.BankAccount;
import com.ramiromd.fundingsourcesservice.repository.BankAccountRepository;
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
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository repository;

    @Autowired
    private SourceEntitySeeder sourceEntitySeeder;

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
    public void should_thrown_an_exception_when_insert_an_empty_account() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            BankAccount anAccount = new BankAccount();
            this.repository.save(anAccount);
        });
    }

    @Test
    public void it_can_persist_a_bank_account() {
        BankAccount anAccount = this.createDefaultAccount();
        this.repository.save(anAccount);
        assertNotNull(anAccount.getId());
        assertNotNull(anAccount.getCreatedAt());
        assertEquals(SourceType.BANK_ACCOUNT, anAccount.getType());
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

    @Test
    public void should_return_only_bank_accounts() {
        int expectedCount = 10;
        // Create 4 random credit cards & 10 random bank accounts
        this.sourceEntitySeeder.createManySources(4, expectedCount);
        List<BankAccount> accounts = this.repository.findAll();

        assertEquals(expectedCount, accounts.size());
        for (BankAccount account: accounts) {
            assertEquals(SourceType.BANK_ACCOUNT, account.getType());
        }
    }
}
