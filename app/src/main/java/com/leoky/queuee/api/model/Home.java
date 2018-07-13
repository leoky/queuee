package com.leoky.queuee.api.model;

public class Home {
    public String status,total_queue,total_complete;

    public Home(String status, String total_queue, String total_complete) {
        this.status = status;
        this.total_queue = total_queue;
        this.total_complete = total_complete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_queue() {
        return total_queue;
    }

    public void setTotal_queue(String total_queue) {
        this.total_queue = total_queue;
    }

    public String getTotal_complete() {
        return total_complete;
    }

    public void setTotal_complete(String total_complete) {
        this.total_complete = total_complete;
    }
}
