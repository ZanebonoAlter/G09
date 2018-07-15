package com.tomcat.status.model;

public class config extends configKey {
    private Double memoryTotal;

    private Double memoryPoolCompressedClassSpaceUsed;

    private Double connectorCurrentthreadscount;

    private Double connectorCurrentthreadsbusy;

    private Double memoryPoolCodeCacheUsed;

    private Double memoryPoolMetaspaceUsed;

    private Double memoryPoolPsEdenSpaceUsed;

    private Double memoryPoolPsOldGenUsed;

    private Double memoryPoolPsSurvivorSpaceUsed;

    public Double getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(Double memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public Double getMemoryPoolCompressedClassSpaceUsed() {
        return memoryPoolCompressedClassSpaceUsed;
    }

    public void setMemoryPoolCompressedClassSpaceUsed(Double memoryPoolCompressedClassSpaceUsed) {
        this.memoryPoolCompressedClassSpaceUsed = memoryPoolCompressedClassSpaceUsed;
    }

    public Double getConnectorCurrentthreadscount() {
        return connectorCurrentthreadscount;
    }

    public void setConnectorCurrentthreadscount(Double connectorCurrentthreadscount) {
        this.connectorCurrentthreadscount = connectorCurrentthreadscount;
    }

    public Double getConnectorCurrentthreadsbusy() {
        return connectorCurrentthreadsbusy;
    }

    public void setConnectorCurrentthreadsbusy(Double connectorCurrentthreadsbusy) {
        this.connectorCurrentthreadsbusy = connectorCurrentthreadsbusy;
    }

    public Double getMemoryPoolCodeCacheUsed() {
        return memoryPoolCodeCacheUsed;
    }

    public void setMemoryPoolCodeCacheUsed(Double memoryPoolCodeCacheUsed) {
        this.memoryPoolCodeCacheUsed = memoryPoolCodeCacheUsed;
    }

    public Double getMemoryPoolMetaspaceUsed() {
        return memoryPoolMetaspaceUsed;
    }

    public void setMemoryPoolMetaspaceUsed(Double memoryPoolMetaspaceUsed) {
        this.memoryPoolMetaspaceUsed = memoryPoolMetaspaceUsed;
    }

    public Double getMemoryPoolPsEdenSpaceUsed() {
        return memoryPoolPsEdenSpaceUsed;
    }

    public void setMemoryPoolPsEdenSpaceUsed(Double memoryPoolPsEdenSpaceUsed) {
        this.memoryPoolPsEdenSpaceUsed = memoryPoolPsEdenSpaceUsed;
    }

    public Double getMemoryPoolPsOldGenUsed() {
        return memoryPoolPsOldGenUsed;
    }

    public void setMemoryPoolPsOldGenUsed(Double memoryPoolPsOldGenUsed) {
        this.memoryPoolPsOldGenUsed = memoryPoolPsOldGenUsed;
    }

    public Double getMemoryPoolPsSurvivorSpaceUsed() {
        return memoryPoolPsSurvivorSpaceUsed;
    }

    public void setMemoryPoolPsSurvivorSpaceUsed(Double memoryPoolPsSurvivorSpaceUsed) {
        this.memoryPoolPsSurvivorSpaceUsed = memoryPoolPsSurvivorSpaceUsed;
    }
}