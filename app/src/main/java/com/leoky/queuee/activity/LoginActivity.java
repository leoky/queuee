package com.leoky.queuee.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
        sp =  new SessionManager(getApplicationContext());

        if(sp.isLogin()){
            finish();
            Intent i = new Intent(getApplication(),MainActivity.class);
            startActivity(i);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loading = ProgressDialog.show(getApplication(), null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });

    }
    private void requestLogin(){
        Call<UserData> callUser = userService.loginRequest(tvEmail.getText().toString(),tvPassword.getText().toString());

        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();
//                System.out.println("haha "+userData.getEmail());
//                System.out.println("haha "+userData.getName());
//                System.out.println("haha "+userData.getPassword());
//                loading.dismiss();
               if(u !=null){
                   sp.createUserSession(u.getEmail(),u.getPassword(),u.getName(),u.getImgurl(),u.getDob(),u.getPhone());
                   Intent i = new Intent(getApplication(),MainActivity.class);
                   startActivity(i);
               }

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                System.out.println("erorr "+t);
            }
        });
    }

}
