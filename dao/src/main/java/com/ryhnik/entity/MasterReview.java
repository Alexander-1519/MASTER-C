package com.ryhnik.entity;

import com.ryhnik.entity.core.BaseAuditableEntity;

import javax.persistence.*;

@Entity
@Table(name = "masters_reviews")
public class MasterReview extends BaseAuditableEntity {

    @Column(nullable = false)
    private String review;

    private Double rating;

    @ManyToOne
    private MasterRoom masterRoom;

    @ManyToOne
    private User user;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public MasterRoom getMasterRoom() {
        return masterRoom;
    }

    public void setMasterRoom(MasterRoom masterRoom) {
        this.masterRoom = masterRoom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
