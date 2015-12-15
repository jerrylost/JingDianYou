package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.HotelItemAdapter;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.HotelList;
import com.jerry.jingdianyou.entity.ScenicDetail;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_hotel_list)
public class HotelMoreActivity extends BaseActivity
{
  //获取酒店信息
  private List<HotelList.Data> hotelDatas = new ArrayList<>();
  private JDYHttpConnect mHttpConnect;
  private ImageLoader mImageloader;
  private Map<String, Object> params = new HashMap<>();
  private String scenic_spot_code;
  private String mCity;
  private HotelItemAdapter mAdapter;
  private int page_index = 1;

  //定义请求酒店数据的参数
  //address   scenic_spot_code   end_date  min_price   keyword
  // max_price   start_date   order_by  star_level  order_type
  private String[] mHotelParams = new String[6];

  @ViewInject(R.id.lv_hotel_list)
  private PullToRefreshListView mLVHotel;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    this.mImageloader = JDYApplication.getApp().getmImageLoader();
    mHttpConnect = JDYHttpConnect.getInstance();
    Intent intent = getIntent();
    mHotelParams = intent.getStringArrayExtra("mHotelParams");
    initViews();
    initDatas();
  }

  private void initViews()
  {
    mAdapter = new HotelItemAdapter(getApplicationContext(), hotelDatas);
    mLVHotel.setAdapter(mAdapter);
    mLVHotel.setMode(PullToRefreshBase.Mode.BOTH);
    mLVHotel.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
    {
      @Override
      public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        hotelDatas.clear();
        page_index = 1;
        initDatas();
        mAdapter.notifyDataSetChanged();
      }

      @Override
      public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        page_index++;
        initDatas();
      }
    });
  }

  //address   scenic_spot_code   end_date  min_price   keyword   max_price   start_date   order_by  star_level  order_type
  private void initDatas()
  {
    mCity = mHotelParams[0];
    scenic_spot_code = mHotelParams[1];

    //{"page_size":10,"end_date":"2015-11-20","min_price":"","page_index":"1","address":"北京","keyword":"","max_price":"",
    // "scenic_spot_code":"JD000000000000000116","start_date":"2015-11-19","order_by":"","star_level":"","order_type":""}
    params.put("RequestJson", "{\"page_size\":10,\"end_date\":\"2015-11-29\",\"min_price\":\"\",\"page_index\":\"" + page_index + "\"," +
        "\"address\":\"" + mCity + "\",\"keyword\":\"\",\"max_price\":\"\",\"scenic_spot_code\":\"" + scenic_spot_code + "\"," +
        "\"start_date\":\"2015-11-28\",\"order_by\":\"\",\"star_level\":\"\",\"order_type\":\"\"}");
    mHttpConnect.getInstance().getHotels(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        hideLoading();
        Gson gson = new Gson();
        HotelList hotelList = gson.fromJson(response, HotelList.class);
        if (hotelList == null)
        {
          return;
        }
        if (hotelList.getData() != null)
        {

          hotelDatas.addAll(hotelList.getData());
        }

        mAdapter.notifyDataSetChanged();

      }

      @Override
      public void onFailure(String error)
      {

      }
    });
  }

  @OnClick(R.id.img_hotel_item_back)
  public void toBack(View view)
  {
    this.finish();
  }

  private void hideLoading()
  {
    if (mLVHotel.isRefreshing())
    {
      mLVHotel.onRefreshComplete();
    }
  }
}
