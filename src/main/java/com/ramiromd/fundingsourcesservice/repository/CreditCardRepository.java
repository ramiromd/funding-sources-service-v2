package com.ramiromd.fundingsourcesservice.repository;

import com.ramiromd.fundingsourcesservice.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
