package com.db.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.db.view.FoodActivity;
import com.db.view.ShopActivity;
import com.db.viewmodel.AccountPageViewModel;
import com.db.viewmodel.CreateOrderViewModel;
import com.db.viewmodel.FoodViewModel;
import com.example.activity.R;
import com.db.viewmodel.OrderPageViewModel;

import java.util.List;

import bean.OrderBean;
import bean.ShopBean;
import service.OrderService;
import service.OrderServiceImpl;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private OrderPageViewModel orderPageViewModel;
    private CreateOrderViewModel  createOrderViewModel;
    LinearLayout linearLayout;
    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        System.out.println("index"+index);
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderPageViewModel = ViewModelProviders.of(this).get(OrderPageViewModel.class);

        createOrderViewModel = ViewModelProviders.of(this).get(CreateOrderViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            System.out.println("ARG_SECTION_NUMBER is" +index );

        }
        orderPageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);
        linearLayout  = root.findViewById(R.id.cardlayout);
        final Observer<List<OrderBean>> OrderObserver = new Observer<List<OrderBean>>() {
            @Override
            public void onChanged(@Nullable List<OrderBean> orderBeanList) {

                if (orderBeanList != null) {
                    for(OrderBean orderBean:orderBeanList){
                        generateOrderCard(orderBean);
                        //                    cardlayout.addView(cardView);
                    }
                }
                else{
                    System.out.println("(in Fragment) null order list");
                }
            }
        };
        orderPageViewModel.getOrderList().observe(this,OrderObserver);
        return root;
    }

    private void generateOrderCard(final OrderBean orderBean) {
        View cardView = View.inflate(this.getContext(), R.layout.order_item, null);
        TextView shopName = (TextView) cardView.findViewById(R.id.shopName_order);
        TextView orderId = (TextView) cardView.findViewById(R.id.id_order);
        orderId.setText("订单号："+String.valueOf(orderBean.getOrderId()));
        shopName.setText(orderBean.getShopBean().getShopName());

//        TextView address = (TextView) cardView.findViewById(R.id.address);
//        address.setText(shopBean.getAddress());
        final Button checkbtn = cardView.findViewById(R.id.check_recv);
        if(orderBean.getState()==2){
            checkbtn.setBackgroundColor(Color.parseColor("#FFA500"));
            checkbtn.setText("已收货");
            checkbtn.setActivated(false);
        }
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbtn.setBackgroundColor(Color.parseColor("#FFA500"));
                checkbtn.setText("已收货");
                orderBean.setState(2);
                checkbtn.setActivated(false);
                try {
                    createOrderViewModel.update(orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        linearLayout.addView(cardView);
//        return cardView;
    }
}