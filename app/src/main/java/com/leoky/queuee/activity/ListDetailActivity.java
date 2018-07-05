package com.leoky.queuee.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.leoky.queuee.R;

public class ListDetailActivity extends AppCompatActivity {

    public static String LD_NAME = "LD_NAME";
    public static String LD_PHOTO = "LD_PHOTO";
    public static String LD_NOTE = "LD_NOTE";
    public static String LD_NUM = "LD_NUM";

    private TextView tvNote,tvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNote = findViewById(R.id.tvNote);
        tvNum = findViewById(R.id.tvNumNow);

        if(savedInstanceState == null){
            if(getIntent().getExtras()!= null){
                getSupportActionBar().setTitle(getIntent().getStringExtra(LD_NAME));
                tvNum.setText(getIntent().getStringExtra(LD_NUM));
                tvNote.setText(getIntent().getStringExtra(LD_NOTE));
            }
        }

        findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
