package com.ryhnik.service;

import com.ryhnik.entity.Maintenance;
import com.ryhnik.entity.Master;
import com.ryhnik.entity.MasterRoom;
import com.ryhnik.exception.Code;
import com.ryhnik.exception.ExceptionBuilder;
import com.ryhnik.exception.MasterClubException;
import com.ryhnik.exception.NoSuchMasterRoomException;
import com.ryhnik.repository.MaintenanceRepository;
import com.ryhnik.repository.MasterRepository;
import com.ryhnik.repository.MasterRoomRepository;
import com.ryhnik.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final UserRepository userRepository;
    private final MasterRepository masterRepository;
    private final MasterRoomRepository masterRoomRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository,
                              UserRepository userRepository,
                              MasterRepository masterRepository,
                              MasterRoomRepository masterRoomRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.userRepository = userRepository;
        this.masterRepository = masterRepository;
        this.masterRoomRepository = masterRoomRepository;
    }

    public Maintenance create(String username, Long masterId, Long roomId, Maintenance maintenance) {
        Master master = masterRepository.findByUsernameAndMasterId(username, masterId)
                .orElseThrow(() -> ExceptionBuilder.builder(Code.MAINTENANCE_EXCEPTION)
                        .withMessage("Can't find master with username = " + username)
                        .build(MasterClubException.class));

        MasterRoom masterRoom = masterRoomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchMasterRoomException(roomId));

        maintenance.setMasterRoom(masterRoom);
        return maintenanceRepository.save(maintenance);
    }

    public void deleteById(Long masterId, Long maintenanceId) {
        boolean existsByIds = maintenanceRepository.existsByMasterIdAndMaintenanceId(masterId, maintenanceId);
        if (!existsByIds) {
            throw ExceptionBuilder.builder(Code.MAINTENANCE_EXCEPTION)
                    .withMessage("Can't find maintenance on this master")
                    .build(MasterClubException.class);
        }

        maintenanceRepository.deleteById(maintenanceId);
    }

    public Page<Maintenance> findAll(Long masterId, Long roomId, Pageable pageable) {
        return maintenanceRepository.findAll(masterId, roomId, pageable);
    }
}
