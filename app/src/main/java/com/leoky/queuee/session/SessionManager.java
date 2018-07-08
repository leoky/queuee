package com.leoky.queuee.session;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class SessionManager {

    private static final String PREF_NAME = "UserData";
    private static final String KEY_ID = "userID";
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_PASS = "userPass";
    private static final String KEY_NAME = "userName";
    private static final String KEY_IMG = "userImg";
    private static final String KEY_DOB = "userDob";
    private static final String KEY_PHONE = "userPhone";
    private static final String KEY_ISLOGIN = "userIsLogin";
    private static final String KEY_LOCATION = "userLocation";
    private static final String KEY_TIME = "userTime";
    private static final String KEY_GENDER = "userGender";
    private static final String KEY_CNAME = "userCName";


    public Context context;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    //miss gender
    public void createUserSession(String id, String email, String name, String password, String imgUrl, Date dob, String phone,String gender,String cname,
                                  String location,String time){
        editor.putString(KEY_ID,id);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASS,password);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_IMG,imgUrl);
        editor.putString(KEY_DOB,dob.toString());
        editor.putString(KEY_PHONE,phone);
        editor.putString(KEY_LOCATION,location);
        editor.putString(KEY_GENDER,gender);
        editor.putString(KEY_TIME,time);
        editor.putString(KEY_CNAME,cname);
        editor.putBoolean(KEY_ISLOGIN,true);
        editor.commit();
    }
    public void saveSpId(String id){
        editor.putString(KEY_ID,id);
        editor.commit();
    }

    public String getSpId() {
        return pref.getString(KEY_ID, "");
    }

    public void saveSpEmail(String email){
        editor.putString(KEY_EMAIL,email);
        editor.commit();
    }

    public String getSpEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public void saveSpPassword(String password) {
        editor.putString(KEY_PASS, password);
        editor.commit();
    }

    public String getSpPassword() {
        return pref.getString(KEY_PASS, "");
    }

    public void saveSpName(String name){
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getSpName() {
        return pref.getString(KEY_NAME, "");
    }

    public void saveSpPhoto (String imgUrl) {
        editor.putString(KEY_IMG, imgUrl);
        editor.commit();
    }

    public String getSpPhoto () {
        return pref.getString(KEY_IMG,"");
    }

    public void saveSpDOB (String dob) {
        editor.putString(KEY_DOB, dob);
        editor.commit();
    }

    public String getSpDOB () {
        return pref.getString(KEY_DOB, "");
    }

    public void saveSpPhone (String phone){
        editor.putString(KEY_PHONE, phone);
        editor.commit();
    }

    public String getSpPhone () {
        return pref.getString(KEY_PHONE, "");
    }

    public void  saveSpIsLogin(boolean b){
        editor.putBoolean(KEY_ISLOGIN, b);
        editor.commit();
    }

    public boolean isLogin () {
        return pref.getBoolean(KEY_ISLOGIN, false);
    }

    public String getSpGender () {
        return pref.getString(KEY_GENDER, "");
    }

    public void  saveSpCName(String cname){
        editor.putString(KEY_CNAME, cname);
        editor.commit();
    }
    public String getSpCName () {
        return pref.getString(KEY_CNAME, "");
    }

    public void  saveSpGender(String gender){
        editor.putString(KEY_GENDER, gender);
        editor.commit();
    }
    public String getSpLocation () {
        return pref.getString(KEY_LOCATION, "");
    }

    public void  saveSpLocation(String location){
        editor.putString(KEY_LOCATION, location);
        editor.commit();
    }
    public String getSpTime () {
        return pref.getString(KEY_TIME, "");
    }

    public void  saveSpTime(String time){
        editor.putString(KEY_GENDER, time);
        editor.commit();
    }
    public void clearSp(){
        pref.edit().clear().commit();
    }
}
