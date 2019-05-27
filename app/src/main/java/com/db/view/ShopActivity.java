package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.db.viewmodel.RegisterViewModel;
import com.db.viewmodel.ShopViewModel;
import com.example.activity.R;

import java.util.List;

import bean.ShopBean;
import util.ViewUtil;

public class ShopActivity extends AppCompatActivity {
    private Context context;
    private ShopViewModel shopViewModel;
    private LinearLayout cardlayout;
    private ImageButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopViewModel = ViewModelProviders.of(this).get(ShopViewModel.class);
        backbtn = findViewById(R.id.backbtn);
        cardlayout = findViewById(R.id.cardlayout);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });
        final Observer<List<ShopBean>> ShopObserver = new Observer<List<ShopBean>>() {
            @Override
            public void onChanged(@Nullable List<ShopBean> shopBeanList) {
                System.out.println("size"+shopBeanList.size());
                for(ShopBean shopBean:shopBeanList){
                    CardView cardView = cardViewFactory(shopBean);
                    cardlayout.addView(cardView);
                }
            }
        };

        shopViewModel.getShopBeanList().observe(this,ShopObserver);
        try {
            shopViewModel.ShowShopList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CardView cardViewFactory(ShopBean shopBean) {
       // LinearLayout linearLayout=new LinearLayout(this);
      //  linearLayout.setOrientation(LinearLayout.HORIZONTAL);
       // linearLayout.setPadding(ViewUtil.dip2px(context,5),ViewUtil.dip2px(context,5), ViewUtil.dip2px(context,5),ViewUtil.dip2px(context,5));
      //  LinearLayout.LayoutParams LL_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        CardView cardView = new CardView(this);
        TextView foodName = new TextView(this);
        foodName.setText(shopBean.getShopName());
        foodName.setTextSize(18);
        cardView.addView(foodName);

        TextView address = new TextView(this);
        address.setText(shopBean.getAddress());
        address.setTextSize(10);
        cardView.addView(address);

        cardView.setLayoutParams(new CardView.LayoutParams(
                CardView.LayoutParams.MATCH_PARENT,   // width
               200)); // height
        cardView.setRadius(20);
        cardView.setElevation(5);
        cardView.setPadding(0, 20, 10, 0);
        cardView.setCardBackgroundColor(0);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this,FoodActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });

        return cardView;
    }


}
