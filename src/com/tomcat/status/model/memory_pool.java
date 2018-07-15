package com.tomcat.status.model;

public class memory_pool extends memory_poolKey {
    private String type;

    private Double initial;

    private Double committed;

    private Double maximum;

    private Double used;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Double getInitial() {
        return initial;
    }

    public void setInitial(Double initial) {
        this.initial = initial;
    }

    public Double getCommitted() {
        return committed;
    }

    public void setCommitted(Double committed) {
        this.committed = committed;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getUsed() {
        return used;
    }

    public void setUsed(Double used) {
        this.used = used;
    }
}