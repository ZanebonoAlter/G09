package com.tomcat.status.model;

public class connector extends connectorKey {
    private Integer maxthreads;

    private Integer currentthreadcount;

    private Integer currentthreadsbusy;

    private Long maxtime;

    private Long processingtime;

    private Integer requestcount;

    private Integer errorcount;

    private Long bytesreceived;

    private Long bytessent;

    public Integer getMaxthreads() {
        return maxthreads;
    }

    public void setMaxthreads(Integer maxthreads) {
        this.maxthreads = maxthreads;
    }

    public Integer getCurrentthreadcount() {
        return currentthreadcount;
    }

    public void setCurrentthreadcount(Integer currentthreadcount) {
        this.currentthreadcount = currentthreadcount;
    }

    public Integer getCurrentthreadsbusy() {
        return currentthreadsbusy;
    }

    public void setCurrentthreadsbusy(Integer currentthreadsbusy) {
        this.currentthreadsbusy = currentthreadsbusy;
    }

    public Long getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(Long maxtime) {
        this.maxtime = maxtime;
    }

    public Long getProcessingtime() {
        return processingtime;
    }

    public void setProcessingtime(Long processingtime) {
        this.processingtime = processingtime;
    }

    public Integer getRequestcount() {
        return requestcount;
    }

    public void setRequestcount(Integer requestcount) {
        this.requestcount = requestcount;
    }

    public Integer getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(Integer errorcount) {
        this.errorcount = errorcount;
    }

    public Long getBytesreceived() {
        return bytesreceived;
    }

    public void setBytesreceived(Long bytesreceived) {
        this.bytesreceived = bytesreceived;
    }

    public Long getBytessent() {
        return bytessent;
    }

    public void setBytessent(Long bytessent) {
        this.bytessent = bytessent;
    }
}