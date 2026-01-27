package com.roman.repository;

import com.roman.model.Spool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpoolRepository extends JpaRepository<Spool, Long> {
    List<Spool> findAllByMaterial_Id(Long materialId);
}
