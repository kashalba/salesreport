package com.kash.salesreport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SalesEntryRepository extends CrudRepository<SalesEntry, Long> {

    Page<SalesEntry> findAll(Pageable pageable);

}
