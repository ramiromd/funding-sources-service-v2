package com.ramiromd.fundingsourcesservice.repository;

import com.ramiromd.fundingsourcesservice.entity.FundingSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundingSourceRepository extends JpaRepository<FundingSource, Long> {

    // TODO: Avoid save method ...

    List<FundingSource> findByDeletedAtIsNull();
}
