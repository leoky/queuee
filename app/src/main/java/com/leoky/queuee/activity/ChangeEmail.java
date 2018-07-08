package com.leoky.queuee.activity;

import android.app.ProgressDialog;
import android.arch.persistence.room.Update;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leoky.queuee.R;
import com.leoky.queuee.api.ApiService;
import com.leoky.queuee.api.model.UserData;
import com.leoky.queuee.api.service.UserService;
import com.leoky.queuee.session.SessionManager;

import retrofit2.Callback;
import retrofit2.Response;

public class ChangeEmail extends AppCompatActivity {

//    public static String KEY_ID = "key_id";
//    public static String KEY_DATA = "key_data";

    EditText editText;
    Button btnSave;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        getSupportActionBar().setTitle("Change Email");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.tvEditText);
        btnSave = findViewById(R.id.btnSave);


        if(MainActivity.sp!=null){
            editText.setText(MainActivity.sp.getSpEmail());
        }else{
            finish();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnSave.setClickable(true);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loading = ProgressDialog.show(ChangeEmail.this, null, "Please wait", true, false);
                        updateData();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.save:
//                loading = ProgressDialog.show(ChangeEmail.this, null, "Please wait", true, false);
//                updateData();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
    private void updateData(){
        retrofit2.Call<UserData> callUser = MainActivity.userService.updateEmail(MainActivity.sp.getSpId(),editText.getText().toString());
        callUser.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(retrofit2.Call<UserData> call, Response<UserData> response) {
                UserData u = response.body();

                if(u!=null){
                    MainActivity.sp.saveSpEmail(editText.getText().toString());
//                    loading.setMessage("Update Succsess");
                    loading.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserData> call, Throwable t) {
                System.out.println("erorr "+ t);
//                loading.setMessage("Update Failed");
                loading.dismiss();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.editdata_menu, menu);
//        return true;
//    }
}
