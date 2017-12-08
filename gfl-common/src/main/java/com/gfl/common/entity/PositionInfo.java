package com.gfl.common.entity;


public class PositionInfo {
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;

    public PositionInfo() {
        super();
    }

    public PositionInfo(double longitude, double latitude) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "PositionInfo{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
