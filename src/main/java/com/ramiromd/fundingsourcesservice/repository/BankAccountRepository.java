package com.ramiromd.fundingsourcesservice.repository;

import com.ramiromd.fundingsourcesservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
