package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
    private TextView shopName;
    private TextView address;
    private TextView distant;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        cardlayout = findViewById(R.id.food_layout);
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        shopName = findViewById(R.id.HeadShopName);
        address = findViewById(R.id.HeadShopAddress);
        phone = findViewById(R.id.HeadShopPhone);
        distant = findViewById(R.id.HeadShopDist);
        submit = findViewById(R.id.submit);
        shopName.setText(FoodViewModel.getshopBean().getShopName());  //直接把商家shopBean传入
        address.setText(FoodViewModel.getshopBean().getAddress());
        phone.setText(FoodViewModel.getshopBean().getTelephone());
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
//        collectBtn = findViewById(R.id.collect);
//        collectBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
////                    loginViewModel.login(userNameview.getText().toString(),passwordview.getText().toString());
//                    collectBtn.setBackgroundColor(Color.parseColor("#FFA500"));
//                    collectBtn.setText("已收藏");
//                    //     startMain();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        final RadioButton addBtn = cardView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            Boolean flag = addBtn.isChecked();
            @Override
            public void onClick(View v) {
                if(!flag){
                    flag = true;
                    addBtn.setChecked(true);
                }else{
                    flag = false;
                    addBtn.setChecked(false);
                }
            }
        });
        addBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(addBtn.isChecked()) {
                    try {
                        foodViewModel.addChoose(foodBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        foodViewModel.deleteChoose(foodBean);
                    }catch (Exception e){

                    }
                }
            }
        });
//        TextView address = (TextView) cardView.findViewById(R.id.address);
//        address.setText(shopBean.getAddress());

        cardlayout.addView(cardView);
//        return cardView;
    }
}
