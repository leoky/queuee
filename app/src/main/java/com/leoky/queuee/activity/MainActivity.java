package com.leoky.queuee.activity;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.leoky.queuee.R;
import com.leoky.queuee.fragment.Fragment_history;
import com.leoky.queuee.fragment.Fragment_home;
import com.leoky.queuee.fragment.Fragment_list;
import com.leoky.queuee.fragment.Fragment_setting;
import com.leoky.queuee.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new Fragment_home(),Fragment_home.class.getSimpleName()).commit();
        }


        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                switch (item.getItemId()){
                    case R.id.nav_1:
                        frag =Fragment_home.newInstance();
                        break;
                    case R.id.nav_2:
                        frag = Fragment_list.newInstance();
                        break;
                    case R.id.nav_3:
                        frag = Fragment_history.newInstance();
                        break;
                    case R.id.nav_4:
                        frag = Fragment_setting.newInstance();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,frag).commit();
                return true;
            }
        });
    }
}
