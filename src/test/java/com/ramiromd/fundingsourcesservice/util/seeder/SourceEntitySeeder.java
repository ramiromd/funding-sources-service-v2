package com.ramiromd.fundingsourcesservice.util.seeder;

import com.github.javafaker.Faker;
import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;
import com.ramiromd.fundingsourcesservice.entity.BankAccount;
import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import com.ramiromd.fundingsourcesservice.repository.BankAccountRepository;
import com.ramiromd.fundingsourcesservice.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SourceEntitySeeder {

    private final Faker faker;

    @Autowired
    private CreditCardRepository creditCards;

    @Autowired
    private BankAccountRepository bankAccounts;

    public SourceEntitySeeder() {
        this.faker = new Faker();
    }

    public void createManySources(int creditCardCount, int bankAccountCount) {
        this.createManyRandomCreditCards(creditCardCount);
        this.createManyRandomBankAccounts(bankAccountCount);
    }

    public List<CreditCard> createManyRandomCreditCards(int count) {

        ArrayList<CreditCard> list = new ArrayList<>();

        for(int i = 0; i < count; i++) {

            String number = this.faker.finance().creditCard();

            CreditCard aCreditCard = new CreditCard();
            aCreditCard.setUserId(UUID.randomUUID().toString());
            aCreditCard.setName(this.faker.rockBand().name());
            aCreditCard.setCardholderName(this.faker.name().fullName());
            aCreditCard.setBin(number.substring(0, 6));
            aCreditCard.setLastFourDigits(number.substring(number.length()-4));
            aCreditCard.setBrand(CardBrandEnum.VISA);
            aCreditCard.setExpirationDate("22/11");

            this.creditCards.save(aCreditCard);
            list.add(aCreditCard);
        }

        return list;
    }

    public List<BankAccount> createManyRandomBankAccounts(int count) {

        ArrayList<BankAccount> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            BankAccount account = new BankAccount();

            account.setUserId(UUID.randomUUID().toString());
            account.setName(this.faker.rockBand().name());
            account.setOwner(this.faker.name().fullName());
            account.setBankName(this.faker.company().name());
            account.setNumber(this.faker.finance().iban("AE"));

            this.bankAccounts.save(account);
            list.add(account);
        }

        return list;
    }
}
