package com.tomcat.status.model;

public class memory extends memoryKey implements java.io.Serializable{
    /**
	 * 
	 */


	private Double free;

    private Double total;

    private Double max;

    public Double getFree() {
        return free;
    }

    public void setFree(Double free) {
        this.free = free;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}