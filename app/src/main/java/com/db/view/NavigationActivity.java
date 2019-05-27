package com.db.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.activity.R;

public abstract class NavigationActivity extends AppCompatActivity {

    static int current_Activity = 0;
    static Activity cur_activity;
    void setcurrent_Activity(int activity,Activity act){
        current_Activity = activity;
        cur_activity = act;
    }

    abstract void init_home();

    abstract void init_arrount();

    abstract void init_order();

    abstract void init_account();

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(current_Activity==0){
                        return true;
                    }else {
                        init_home();
                        return true;
                    }
                case R.id.navigation_around:
                    if(current_Activity==1){
                        return true;
                    }else {
                        init_arrount();
                        return true;
                    }
                case R.id.navigation_order:
                    if(current_Activity==2){
                        return true;
                    }else {
                        System.out.println("test");
                        init_order();
                        return  true;
                    }
                case R.id.navigation_account:
                    if(current_Activity==3){
                        return true;
                    }else {
                        init_account();
                        return true;
                    }
            }
            return false;
        }
    };
}
