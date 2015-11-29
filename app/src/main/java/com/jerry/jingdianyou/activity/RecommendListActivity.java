package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.RecommenListAdapter;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.RecommendList;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */

@ContentView(R.layout.activity_recommend_list)
public class RecommendListActivity extends Activity
{

  //获取酒店信息
  private List<RecommendList.Data> recomDatas = new ArrayList<>();
  private JDYHttpConnect mHttpConnect;
  private ImageLoader mImageloader;
  private Map<String, Object> params = new HashMap<>();
  private String scenic_spot_code;
  private RecommenListAdapter mAdapter;

  @ViewInject(R.id.plv_recommend_list)
  private ListView mLVRecom;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    this.mImageloader = JDYApplication.getApp().getmImageLoader();
    mHttpConnect = JDYHttpConnect.getInstance();
    Intent intent = getIntent();
    scenic_spot_code = intent.getStringExtra("toRecommend");
    initDatas();
  }

  private void initDatas()
  {
    params.put("RequestJson", "{\"scenic_spot_code\":\"" + scenic_spot_code + "\"}");
    mHttpConnect.getInstance().getRecommend(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        Gson gson = new Gson();
        RecommendList recomList = gson.fromJson(response, RecommendList.class);
        if (recomList == null)
        {
          return;
        }
        if (recomList.getData() != null)
        {
          recomDatas.addAll(recomList.getData());
          mAdapter = new RecommenListAdapter(getApplicationContext(), recomDatas);
          mLVRecom.setAdapter(mAdapter);
        }
        else
        {
          return;
        }

      }

      @Override
      public void onFailure(String error)
      {
      }
    });
  }


  @OnClick(R.id.img_recom_item_back)
  public void toBack(View view)
  {
    this.finish();
  }

}
