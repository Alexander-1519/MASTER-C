package com.ryhnik.repository;

import com.ryhnik.entity.MasterCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterCategoryRepository extends JpaRepository<MasterCategory, Long> {

    @Query("SELECT c FROM MasterCategory c WHERE lower(c.name) LIKE lower(concat('%', :name, '%'))")
    List<MasterCategory> findAll(String name);
}
