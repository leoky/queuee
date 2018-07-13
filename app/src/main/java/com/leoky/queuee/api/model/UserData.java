package com.leoky.queuee.api.model;

import java.util.Date;
//@Entity(tableName = "userData")
public class UserData {
//    @SerializedName("id")
    private String _id;
//    @SerializedName("name")
    private String name;
//    @SerializedName("email")
    private String email;
//    @SerializedName("password")
    private String password;
//    @SerializedName("photo")
    private String photo;
//    @SerializedName("phone")
    private String phone;
//    @SerializedName("category")
    private String category;
//    @SerializedName("gender")
    private String gender;
//    @SerializedName("dob")
    private Date dob;
    private Clinic clinic;
    private String error;

    public UserData(String _id, String name, String email, String password, String photo, String phone, String category, String gender, Date dob, Clinic clinic, String error) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.phone = phone;
        this.category = category;
        this.gender = gender;
        this.dob = dob;
        this.clinic = clinic;
        this.error = error;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
