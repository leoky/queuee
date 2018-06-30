package com.leoky.queuee.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "UserData";
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_PASS = "userPass";
    private static final String KEY_NAME = "userName";
    private static final String KEY_IMG = "userImg";
    private static final String KEY_DOB = "userDob";
    private static final String KEY_PHONE = "userPhone";
    private static final String KEY_ISLOGIN = "userIsLogin";


    public Context context;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    public SessionManager(Context c) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    //miss gender
    public void createUserSession(String email,String password,String name,String imgUrl, String dob, String phone ){
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASS,password);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_IMG,imgUrl);
        editor.putString(KEY_DOB,dob);
        editor.putString(KEY_PHONE,phone);
        editor.putBoolean(KEY_ISLOGIN,true);
        editor.commit();
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

    public void saveSpImage (String imgUrl) {
        editor.putString(KEY_IMG, imgUrl);
        editor.commit();
    }

    public String getSpImage () {
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


}
