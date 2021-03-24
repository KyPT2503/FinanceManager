package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser owner;
    @ManyToOne
    private AppUser custom;

    public Share() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public AppUser getCustom() {
        return custom;
    }

    public void setCustom(AppUser custom) {
        this.custom = custom;
    }
}
