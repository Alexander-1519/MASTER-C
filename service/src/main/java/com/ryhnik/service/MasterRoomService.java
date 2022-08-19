package com.ryhnik.service;

import com.ryhnik.dto.master.room.MasterRoomUpdateDto;
import com.ryhnik.entity.Master;
import com.ryhnik.entity.MasterRoom;
import com.ryhnik.exception.NoSuchMasterException;
import com.ryhnik.exception.NoSuchMasterRoomException;
import com.ryhnik.mapper.MasterRoomMapper;
import com.ryhnik.repository.MaintenanceDateRepository;
import com.ryhnik.repository.MaintenanceRepository;
import com.ryhnik.repository.MasterRepository;
import com.ryhnik.repository.MasterRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterRoomService {

    private final MasterRoomRepository masterRoomRepository;
    private final MasterRepository masterRepository;
    private final MasterRoomMapper roomMapper;
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceDateRepository maintenanceDateRepository;

    public MasterRoomService(MasterRoomRepository masterRoomRepository,
                             MasterRepository masterRepository,
                             MasterRoomMapper roomMapper,
                             MaintenanceRepository maintenanceRepository,
                             MaintenanceDateRepository maintenanceDateRepository) {
        this.masterRoomRepository = masterRoomRepository;
        this.masterRepository = masterRepository;
        this.roomMapper = roomMapper;
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceDateRepository = maintenanceDateRepository;
    }

    public MasterRoom createMasterRoom(Long masterId, MasterRoom masterRoom) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new NoSuchMasterException(masterId));

        masterRoom.setMaster(master);

        return masterRoomRepository.save(masterRoom);
    }

    public List<MasterRoom> findAllByMasterId(Long masterId) {
        return masterRoomRepository.findAllByMasterId(masterId);
    }

    public MasterRoom findById(Long roomId) {
        return masterRoomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchMasterRoomException(roomId));
    }

    public void deleteById(Long roomId) {
        masterRoomRepository.deleteById(roomId);
    }

    public MasterRoom updateById(Long roomId, Long masterId, MasterRoomUpdateDto updateDto) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new NoSuchMasterException(masterId));

        MasterRoom masterRoomById = findById(roomId);
        MasterRoom updatedRoom = roomMapper.toMasterRoom(updateDto);

       updateMasterRoomFullInfo(masterRoomById, updatedRoom);

       masterRoomRepository.save(masterRoomById);

       maintenanceRepository.deleteAllById(updateDto.getMaintenancesToDelete());
       maintenanceDateRepository.deleteAllById(updateDto.getDatesToDelete());

       return findById(roomId);
    }

    private void updateMasterRoomFullInfo(MasterRoom toUpdate, MasterRoom updated) {
        if(updated.getInfo() != null) {
            toUpdate.setInfo(updated.getInfo());
        }
        toUpdate.setDates(updated.getDates());
        toUpdate.setMaintenances(updated.getMaintenances());
        toUpdate.setReviews(updated.getReviews());
    }
}
