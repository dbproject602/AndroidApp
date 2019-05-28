package com.db.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.db.viewmodel.CreateOrderViewModel;
import com.db.viewmodel.LoginViewModel;
import com.example.activity.R;

import bean.FoodBean;
import bean.UserBean;

public class CreateOrderActivity extends AppCompatActivity {
    private ImageButton backbtn;
    private TextView shopname;
    private TextView username;
    private TextView telephone;
    private TextView address;
    private TextView sum;
    private LinearLayout cardlayout;
    private Button submit;
    private CreateOrderViewModel createOrderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        backbtn = (ImageButton) findViewById(R.id.backbtn);
        shopname = (TextView) findViewById(R.id.shopname);
        username = (TextView) findViewById(R.id.username);
        telephone = (TextView) findViewById(R.id.telephone);
        address = (TextView) findViewById(R.id.address);
        sum = (TextView) findViewById(R.id.sum);
        cardlayout = (LinearLayout) findViewById(R.id.cardlayout);
        submit = (Button) findViewById(R.id.submit);
        createOrderViewModel = ViewModelProviders.of(this).get(CreateOrderViewModel.class);
        UserBean userBean = CreateOrderViewModel.getUserBean();
        shopname.setText(CreateOrderViewModel.getShopBean().getShopName());
        username.setText(userBean.getName());
        telephone.setText(userBean.getTelephone());
        address.setText(userBean.getAddress());
        sum.setText("¥"+createOrderViewModel.sum());
        for(FoodBean foodBean:CreateOrderViewModel.getFoodBeanList()){
            TextView textView = new TextView(this);
            textView.setText(foodBean.getFoodName()+"    "+"¥"+foodBean.getPrice());
            cardlayout.addView(textView);
        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createOrderViewModel.submit();
                    Intent intent = new Intent(CreateOrderActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
