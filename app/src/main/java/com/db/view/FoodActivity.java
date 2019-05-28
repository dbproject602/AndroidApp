package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.db.viewmodel.AccountPageViewModel;
import com.db.viewmodel.CreateOrderViewModel;
import com.db.viewmodel.FoodViewModel;
import com.db.viewmodel.HomePageViewModel;
import com.db.viewmodel.SafeSettingViewModel;
import com.db.viewmodel.ShopViewModel;
import com.example.activity.R;

import java.util.List;

import bean.FoodBean;
import bean.ShopBean;

public class FoodActivity extends AppCompatActivity {
    private Button collectBtn;
    private ImageButton backbtn;
    private FloatingActionButton submit;
    private FoodViewModel foodViewModel;
    private LinearLayout cardlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        cardlayout = findViewById(R.id.food_layout);
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        TextView shopName = findViewById(R.id.HeadShopName);
        submit = findViewById(R.id.submit);
        shopName.setText("名字");  //直接把商家shopBean传入
        backbtn = findViewById(R.id.backbtn_food);
        //  检查是否收藏；收藏则变成已收藏，button失效
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CreateOrderViewModel.setShopBean(FoodViewModel.getshopBean());
                    CreateOrderViewModel.setUserBean(AccountPageViewModel.getUserBean());
                    foodViewModel.submit();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                Intent intent = new Intent(FoodActivity.this,CreateOrderActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
            }
        });
        collectBtn = findViewById(R.id.collect);
        collectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
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
                for(FoodBean foodBean:foodBeanList){
                    generateFoodCard(foodBean);
//                    cardlayout.addView(cardView);
                }
            }
        };

        foodViewModel.getShopBeanList().observe(this,foodObserver);
        try {
            foodViewModel.ShowFoodList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateFoodCard(final FoodBean foodBean) {
        View cardView = View.inflate(this, R.layout.food_item, null);
        TextView foodName = (TextView) cardView.findViewById(R.id.foodName);
        TextView price = (TextView) cardView.findViewById(R.id.price);
        price.setText("¥："+String.valueOf(foodBean.getPrice()));
        foodName.setText(foodBean.getFoodName());
        final ImageButton addBtn = cardView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //  添加入购物列表
                try {
                  foodViewModel.addChoose(foodBean);
                  addBtn.setActivated(false);
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
