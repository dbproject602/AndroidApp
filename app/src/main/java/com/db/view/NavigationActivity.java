package com.db.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.activity.R;

public abstract class NavigationActivity extends AppCompatActivity {

    static int curPgae = 0;
    void setpage(int pagenum){
        curPgae = pagenum;
    }

    abstract void init_home();

    abstract void init_arround();

    abstract void init_order();

    abstract void init_account();

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("1");
                    if(curPgae==0){
                        return true;
                    }else {
                        init_home();
                        return true;
                    }
                case R.id.navigation_around:
                    System.out.println("2");
                    if(curPgae==1){
                        return true;
                    }else {
                        init_arround();
                        return true;
                    }
                case R.id.navigation_order:
                    System.out.println("3");
                    if(curPgae==2){
                        return true;
                    }else {
                        init_order();
                        return  true;
                    }
                case R.id.navigation_account:
                    System.out.println("4");
                    if(curPgae==3){
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
