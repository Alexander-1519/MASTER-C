package com.ryhnik.repository;

import com.ryhnik.entity.MasterRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRoomRepository extends JpaRepository<MasterRoom, Long> {

    @Query("SELECT mr FROM MasterRoom mr WHERE mr.master.id = :masterId")
    List<MasterRoom> findAllByMasterId(Long masterId);
}
