package com.ryhnik.entity;

import com.ryhnik.entity.core.BaseAuditableEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "master_rooms")
public class MasterRoom extends BaseAuditableEntity {

    private String info;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @ManyToOne
    private MasterCategory masterCategory;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "masterRoom")
    private List<MaintenanceDate> dates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterRoom")
    private List<Maintenance> maintenances;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "masterRoom")
    private List<PortfolioImage> images;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "masterRoom")
    private List<MasterReview> reviews = new ArrayList<>();

    @ManyToOne
    private Master master;

    public MasterCategory getMasterCategory() {
        return masterCategory;
    }

    public void setMasterCategory(MasterCategory masterCategory) {
        this.masterCategory = masterCategory;
    }

    public List<MaintenanceDate> getDates() {
        return dates;
    }

    public void setDates(List<MaintenanceDate> dates) {
        this.dates = dates;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public List<PortfolioImage> getImages() {
        return images;
    }

    public void setImages(List<PortfolioImage> images) {
        this.images = images;
    }

    public List<MasterReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MasterReview> reviews) {
        this.reviews = reviews;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
}
