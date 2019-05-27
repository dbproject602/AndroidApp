package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.db.viewmodel.FoodViewModel;
import com.db.viewmodel.ShopViewModel;
import com.example.activity.R;

import java.util.List;

import bean.FoodBean;
import bean.ShopBean;

public class FoodActivity extends AppCompatActivity {
    private Button collectBtn;
    private ImageButton backbtn;
    private FoodViewModel foodViewModel;
    private String shopId;
    private String shopName;
    private String shopAddress;
    private LinearLayout cardlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Intent intent = getIntent();
        shopId = intent.getStringExtra("shopId");
        shopName = intent.getStringExtra("shopName");
        shopAddress = intent.getStringExtra("shopAddress");
        cardlayout = findViewById(R.id.food_layout);
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        TextView shopNameView = findViewById(R.id.HeadShopName);
        shopNameView.setText(shopName);
        TextView shopAddressView = findViewById(R.id.HeadShopAddress);
        shopAddressView.setText(shopAddress);
        foodViewModel.setshopid(shopId);
        Toast.makeText(FoodActivity.this, "shopId:"+foodViewModel.getshopid()+"input id:"+shopId, Toast.LENGTH_LONG).show();
        backbtn = findViewById(R.id.backbtn_food);
        //  检查是否收藏；收藏则变成已收藏，button失效
        collectBtn = findViewById(R.id.collect);
        collectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    collectBtn.setText("已收藏");
                    collectBtn.setBackgroundColor(Color.parseColor("#FF8C00"));
//                    loginViewModel.login(userNameview.getText().toString(),passwordview.getText().toString());
                    //     startMain();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });
        final Observer<List<FoodBean>> foodObserver = new Observer<List<FoodBean>>() {
            @Override
            public void onChanged(@Nullable List<FoodBean> foodBeanList) {
//                System.out.println("debug: size of foodlist:"+foodBeanList.is);
//                for(FoodBean foodBean:foodBeanList){
////                    generateFoodCard(foodBean);
////                    cardlayout.addView(cardView);
//
//                }
            }
        };

        // 不注释会报错
        foodViewModel.getShopBeanList().observe(this,foodObserver);
        try {
            foodViewModel.ShowFoodList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateFoodCard(FoodBean foodBean) {
        View cardView = View.inflate(this, R.layout.food_item, null);
        TextView foodName = (TextView) cardView.findViewById(R.id.foodName);
        TextView price = (TextView) cardView.findViewById(R.id.price);
        price.setText("¥："+String.valueOf(foodBean.getPrice()));
        foodName.setText(foodBean.getFoodName());
        Button addBtn = cardView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //  添加入购物列表
                try {
//                    loginViewModel.login(userNameview.getText().toString(),passwordview.getText().toString());
                    //     startMain();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        TextView address = (TextView) cardView.findViewById(R.id.address);
//        address.setText(shopBean.getAddress());

        cardlayout.addView(cardView);
//        return cardView;
    }
}
