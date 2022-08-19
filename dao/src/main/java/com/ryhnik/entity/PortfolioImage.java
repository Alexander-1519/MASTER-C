package com.ryhnik.entity;

import com.ryhnik.entity.core.BaseAuditableEntity;

import javax.persistence.*;

@Entity
@Table(name = "portfolio_images")
public class PortfolioImage extends BaseAuditableEntity {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToOne
    private MasterRoom masterRoom;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MasterRoom getMasterRoom() {
        return masterRoom;
    }

    public void setMasterRoom(MasterRoom masterRoom) {
        this.masterRoom = masterRoom;
    }
}
