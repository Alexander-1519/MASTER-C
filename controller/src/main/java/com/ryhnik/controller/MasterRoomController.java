package com.ryhnik.controller;

import com.ryhnik.dto.master.room.MasterRoomCreateDto;
import com.ryhnik.dto.master.room.MasterRoomOutputDto;
import com.ryhnik.dto.master.room.MasterRoomUpdateDto;
import com.ryhnik.entity.MasterRoom;
import com.ryhnik.mapper.MasterRoomMapper;
import com.ryhnik.service.MasterRoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/masters/{masterId}/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "MasterRooms", description = "API for master rooms")
public class MasterRoomController {

    private final MasterRoomService masterRoomService;
    private final MasterRoomMapper masterRoomMapper;

    public MasterRoomController(MasterRoomService masterRoomService, MasterRoomMapper masterRoomMapper) {
        this.masterRoomService = masterRoomService;
        this.masterRoomMapper = masterRoomMapper;
    }

    @PostMapping
    public ResponseEntity<MasterRoomOutputDto> createMasterRoom(@PathVariable Long masterId,
                                                                @RequestBody MasterRoomCreateDto createDto) {
        MasterRoom masterRoom = masterRoomService.createMasterRoom(masterId, masterRoomMapper.toMasterRoom(createDto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(masterRoomMapper.toOutputDto(masterRoom));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<MasterRoomOutputDto> updateMasterRoomById(@PathVariable Long masterId,
                                                                    @PathVariable Long roomId,
                                                                    @RequestBody MasterRoomUpdateDto updateDto) {
        MasterRoom masterRoom = masterRoomService.updateById(roomId, masterId, updateDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(masterRoomMapper.toOutputDto(masterRoom));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<MasterRoomOutputDto> getMasterRoomById(@PathVariable Long masterId,
                                                                 @PathVariable Long roomId) {
        MasterRoom masterRoom = masterRoomService.findById(roomId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(masterRoomMapper.toOutputDto(masterRoom));
    }

    @GetMapping
    public ResponseEntity<List<MasterRoomOutputDto>> getMasterRoomById(@PathVariable Long masterId) {
        List<MasterRoom> masterRooms = masterRoomService.findAllByMasterId(masterId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(masterRoomMapper.toListOutputDto(masterRooms));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteMasterRoomById(@PathVariable Long masterId,
                                                     @PathVariable Long roomId) {
        masterRoomService.deleteById(roomId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
