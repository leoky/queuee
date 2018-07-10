package com.leoky.queuee.api.model;

import java.util.List;

public class RepoQueue {
    public String total_queue;
    public List<Queue> queue;

    public RepoQueue(String total_queue, List<Queue> queue) {
        this.total_queue = total_queue;
        this.queue = queue;
    }

    public String getTotal_queue() {
        return total_queue;
    }

    public void setTotal_queue(String total_queue) {
        this.total_queue = total_queue;
    }

    public List<Queue> getQueue() {
        return queue;
    }

    public void setQueue(List<Queue> queue) {
        this.queue = queue;
    }
}
