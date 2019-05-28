package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.db.viewmodel.FoodViewModel;
import com.db.viewmodel.ShopViewModel;
import com.example.activity.R;

import java.util.List;

import bean.ShopBean;

public class ShopActivity extends AppCompatActivity {
    private Context context;
    private ShopViewModel shopViewModel;
    private LinearLayout cardlayout;
    private ImageButton backbtn;
    private FoodViewModel foodViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopViewModel = ViewModelProviders.of(this).get(ShopViewModel.class);
        cardlayout = findViewById(R.id.cardlayout);
        backbtn = findViewById(R.id.backbtn_shop);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
            }
        });
        final Observer<List<ShopBean>> ShopObserver = new Observer<List<ShopBean>>() {
            @Override
            public void onChanged(@Nullable List<ShopBean> shopBeanList) {
                for(ShopBean shopBean:shopBeanList){
                    generateShopCard(shopBean);
//                    cardlayout.addView(cardView);
                }
            }
        };

        shopViewModel.getShopBeanList().observe(this,ShopObserver);
        try {
            if(ShopViewModel.getShoptype()!=-1) {
                shopViewModel.ShowShopList();
            }else{
                shopViewModel.ShowShopListbyShopname();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateShopCard(final ShopBean shopBean) {
        View cardView = View.inflate(this, R.layout.shop_item, null);
        TextView shopName = (TextView) cardView.findViewById(R.id.shopName);
        TextView rating = (TextView) cardView.findViewById(R.id.rating);
        rating.setText("评分："+String.valueOf(shopBean.getReputation()));
        shopName.setText(shopBean.getShopName());
//        TextView address = (TextView) cardView.findViewById(R.id.address);
//        address.setText(shopBean.getAddress());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodViewModel.setShopBean(shopBean);
                Intent intent = new Intent(ShopActivity.this,FoodActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });
        cardlayout.addView(cardView);
//        return cardView;
    }


}
