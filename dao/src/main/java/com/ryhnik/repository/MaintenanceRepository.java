package com.ryhnik.repository;

import com.ryhnik.entity.Maintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query("SELECT case when count(m)>0 then true else false end FROM Maintenance m " +
            "WHERE m.id = :maintenanceId " +
            "AND m.masterRoom.master.id = :masterId")
    boolean existsByMasterIdAndMaintenanceId(Long masterId, Long maintenanceId);

    @Query("SELECT m From Maintenance m WHERE m.masterRoom.master.id = :masterId AND m.masterRoom.id = :roomId")
    Page<Maintenance> findAll(Long masterId, Long roomId, Pageable pageable);
}
