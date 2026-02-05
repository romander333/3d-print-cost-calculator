package com.roman.repository;

import com.roman.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long> {
    @Query(value = "SELECT * FROM printers WHERE id = :printerId", nativeQuery = true)
    Printer findIncludeDeletedById(@Param("printerId") Long printerId);
}
