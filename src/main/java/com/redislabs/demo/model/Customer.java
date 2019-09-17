package com.redislabs.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RedisHash("customer")
public class Customer {

    @Id private Long id;
    @Indexed private String externalId;
    private String name;
    private Date updateTime;

    public Customer(Long id, String externalId, String name, Date updateTime) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
