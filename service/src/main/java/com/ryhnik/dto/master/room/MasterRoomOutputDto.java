package com.ryhnik.dto.master.room;

import com.ryhnik.dto.maintenance.MaintenanceInputCreateDto;
import com.ryhnik.dto.maintenancedate.MaintenanceDateInputCreateDto;
import com.ryhnik.entity.PortfolioImage;

import java.time.LocalDateTime;
import java.util.List;

public class MasterRoomOutputDto {

    private String name;
    private LocalDateTime startedAt;
    private List<MaintenanceInputCreateDto> maintenances;
    private List<MaintenanceDateInputCreateDto> dates;
    private List<PortfolioImage> images;
}
