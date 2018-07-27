package com.leoky.queuee.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.leoky.queuee.R;
import com.leoky.queuee.api.ApiService;
import com.leoky.queuee.api.model.UserData;
import com.leoky.queuee.api.service.UserService;
import com.leoky.queuee.session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView tvEmail;
    private EditText tvPassword;
    private Button btnLogin;

    private UserService userService;
    private ProgressDialog loading;
    private SessionManager sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeNoAB);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvEmail = (AutoCompleteTextView)findViewById(R.id.email);
        tvPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        userService = ApiService.getClient().create(UserService.class);
        sp =  new SessionManager(this);

        if(sp.isLogin()){
//            Intent i = new Intent(this,MainActivity.class);
//            startActivity(i);
//            finish();
            loading = ProgressDialog.show(LoginActivity.this, null, "Please wait", true, false);
            requestLogin(sp.getSpEmail(),sp.getSpPassword());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(LoginActivity.this, null, "Please wait", true, false);
                requestLogin(tvEmail.getText().toString(),tvPassword.getText().toString());
            }
        });

    }
    private void requestLogin(String email,String pass){
        Call<UserData> callUser = userService.loginRequest(email,pass);

        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();
                boolean isSuccess= false;
                if( u!=null) {
                    if (u.getError() == null) {
                        sp.createUserSession(u.get_id(), u.getEmail(), u.getName(), u.getPassword(), u.getPhoto(), u.getDob(), u.getPhone(), u.getGender(),
                                u.getClinic().getClinic_name(), u.getClinic().getLocation(), u.getClinic().getEstimate(), u.getClinic().getStatus());
                        Intent i = new Intent(getApplication(), MainActivity.class);
                        startActivity(i);
                        isSuccess= true;
                    }
                }
                loading.dismiss();
                if(isSuccess){
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                loading.dismiss();
                System.out.println("erorr "+t);
            }
        });
    }

}
