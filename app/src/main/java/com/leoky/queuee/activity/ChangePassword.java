package com.leoky.queuee.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leoky.queuee.R;
import com.leoky.queuee.api.model.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    EditText editText,editText2,editText3;

    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.tvEditText);
        editText2 = findViewById(R.id.tvEditText2);
        editText3 = findViewById(R.id.tvEditText3);
        final Button btnSave = findViewById(R.id.btnSave);

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(! editText.getText().toString().equals("") && !editText2.getText().toString().equals("") && !editText3.getText().toString().equals("")){
                    btnSave.setBackground(getDrawable(R.color.colorBtnEnable));
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(editText2.getText().toString().equals(editText3.getText().toString())) {
                                loading = ProgressDialog.show(ChangePassword.this, null, "Please wait", true, false);
                                updateData();
                            }
                        }
                    });
                }else{
                    btnSave.setClickable(false);
                    btnSave.setBackground(getDrawable(R.color.colorBtnDisable));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void updateData(){
        Call<UserData> callUser = MainActivity.userService.updatePassword(MainActivity.sp.getSpId(),editText.getText().toString(),editText2.getText().toString());
        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(retrofit2.Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();
                if(u!=null){
                    loading.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserData> call, Throwable t) {
                System.out.println("erorr "+ t);
                loading.dismiss();
            }
        });
    }
}
