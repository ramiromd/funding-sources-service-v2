package com.ramiromd.fundingsourcesservice.repository;

import com.ramiromd.fundingsourcesservice.entity.FundingSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingSourceRepository extends JpaRepository<FundingSource, Long> {

    // TODO: Avoid save method ...
}
