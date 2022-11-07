package com.ramiromd.fundingsourcesservice.entity;

import com.ramiromd.fundingsourcesservice.data.common.CardBrandEnum;
import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(SourceType.CREDIT_CARD)
public class CreditCard extends FundingSource {

    @Column(length = 100, nullable = false)
    private String cardholderName;

    @Column(nullable = false)
    private String bin;

    @Column(length = 16, nullable = false)
    private CardBrandEnum brand;

    @Column(nullable = false)
    private String lastFourDigits;

    @Column(length = 8, nullable = false)
    private String expirationDate;
}
