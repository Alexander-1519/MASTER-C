package com.ryhnik.mapper;

import com.ryhnik.dto.master.room.MasterRoomCreateDto;
import com.ryhnik.dto.master.room.MasterRoomOutputDto;
import com.ryhnik.dto.master.room.MasterRoomUpdateDto;
import com.ryhnik.entity.MasterRoom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {MaintenanceMapper.class,
                MaintenanceDateMapper.class,
                PortfolioImageMapper.class,
                MasterReviewMapper.class,
                UserMapper.class
        })
public interface MasterRoomMapper {

    MasterRoom toMasterRoom(MasterRoomCreateDto createDto);

    MasterRoom toMasterRoom(MasterRoomUpdateDto updateDto);

    MasterRoomOutputDto toOutputDto(MasterRoom masterRoom);

    List<MasterRoomOutputDto> toListOutputDto(List<MasterRoom> masterRooms);
}
