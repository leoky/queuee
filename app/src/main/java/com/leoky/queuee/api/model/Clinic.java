package com.leoky.queuee.api.model;

public class Clinic {
    private String clinic_name,status,region,location,estimate;

    public Clinic(String clinic_name, String status, String region, String location, String estimate) {
        this.clinic_name = clinic_name;
        this.status = status;
        this.region = region;
        this.location = location;
        this.estimate = estimate;
    }

    public String getClinic_name() {
        return clinic_name;
    }

    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }
}
