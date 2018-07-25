package com.leoky.queuee.api.model;


public class Queue {
    public String _id;
    public Patient patient;
    public Doctor doctor;
    public String order_no;
    public String note, status, result;
    public String queue_at, done_at;

    public Queue(String _id, Patient patient, Doctor doctor, String order_no, String note, String status, String result, String queue_at, String done_at) {
        this._id = _id;
        this.patient = patient;
        this.doctor = doctor;
        this.order_no = order_no;
        this.note = note;
        this.status = status;
        this.result = result;
        this.queue_at = queue_at;
        this.done_at = done_at;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getQueue_at() {
        return queue_at;
    }

    public void setQueue_at(String queue_at) {
        this.queue_at = queue_at;
    }

    public String getDone_at() {
        return done_at;
    }

    public void setDone_at(String done_at) {
        this.done_at = done_at;
    }
}
