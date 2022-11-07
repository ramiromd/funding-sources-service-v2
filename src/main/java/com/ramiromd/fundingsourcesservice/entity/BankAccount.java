package com.ramiromd.fundingsourcesservice.entity;

import com.ramiromd.fundingsourcesservice.data.common.SourceType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(SourceType.BANK_ACCOUNT)
public class BankAccount extends FundingSource {

    @Column(length = 100, nullable = false)
    private String owner;

    @Column(length = 64, nullable = false)
    private String bankName;

    @Column(length = 32, nullable = false)
    private String number;

    public String getType() {
        return SourceType.BANK_ACCOUNT;
    }
}
