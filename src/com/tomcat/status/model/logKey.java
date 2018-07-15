package com.tomcat.status.model;

import java.util.Date;

public class logKey {
    private String ipaddress;

    private Integer port;

    private Date time;

    private String errorType;

    private String describeMessage;

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType == null ? null : errorType.trim();
    }

    public String getDescribeMessage() {
        return describeMessage;
    }

    public void setDescribeMessage(String describeMessage) {
        this.describeMessage = describeMessage == null ? null : describeMessage.trim();
    }
}