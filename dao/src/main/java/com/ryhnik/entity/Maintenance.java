package com.ryhnik.entity;

import com.ryhnik.entity.core.BaseAuditableEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "maintenances")
public class Maintenance extends BaseAuditableEntity {

    private String name;

    private BigDecimal price;

    @ManyToOne
    private MasterRoom masterRoom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MasterRoom getMasterRoom() {
        return masterRoom;
    }

    public void setMasterRoom(MasterRoom masterRoom) {
        this.masterRoom = masterRoom;
    }
}
