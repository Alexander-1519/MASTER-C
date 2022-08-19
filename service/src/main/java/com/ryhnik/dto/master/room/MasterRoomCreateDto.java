package com.ryhnik.dto.master.room;

import com.ryhnik.dto.maintenance.MaintenanceInputCreateDto;
import com.ryhnik.dto.maintenancedate.MaintenanceDateInputCreateDto;
import com.ryhnik.entity.PortfolioImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MasterRoomCreateDto {

    private String name;
    private LocalDateTime startedAt;
    private List<MaintenanceInputCreateDto> maintenances;
    private List<MaintenanceDateInputCreateDto> dates;
    private List<PortfolioImage> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public List<MaintenanceInputCreateDto> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<MaintenanceInputCreateDto> maintenances) {
        this.maintenances = maintenances;
    }

    public List<MaintenanceDateInputCreateDto> getDates() {
        return dates;
    }

    public void setDates(List<MaintenanceDateInputCreateDto> dates) {
        this.dates = dates;
    }

    public List<PortfolioImage> getImages() {
        return images;
    }

    public void setImages(List<PortfolioImage> images) {
        this.images = images;
    }
}
