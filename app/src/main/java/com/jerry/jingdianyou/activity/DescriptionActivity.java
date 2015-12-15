package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.entity.ScenicDesriptWebview;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.webv_description)
public class DescriptionActivity extends BaseActivity
{

  @ViewInject(R.id.webv_scenic_descript)
  private WebView mwebv_scenic_descript;
  //定义对webview的请求参数
  private Map<String, Object> params = new HashMap<>();
  //获取webview所需要的参数
  private String scenic_spot_code;

  //数据请求的工具
  private JDYHttpConnect mHttpconnect;
  //获取简介的数据
  private ScenicDesriptWebview mScenicDesriptWebview = null;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    mHttpconnect = JDYHttpConnect.getInstance();
    ViewUtils.inject(this);
    initData();
  }

  private void initData()
  {
    Intent intent = getIntent();
    scenic_spot_code = intent.getStringExtra("web_scenic_spot_code");
    params.put("RequestJson", "{\"scenic_spot_code\":\"" + scenic_spot_code + "\"}");
    mHttpconnect.getInstance().getScenicDescripWebview(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        Gson gson = new Gson();
        mScenicDesriptWebview = gson.fromJson(response, ScenicDesriptWebview.class);
        if (mScenicDesriptWebview != null)
        {
          initWebView();
        }
      }

      @Override
      public void onFailure(String error)
      {
      }
    });
  }

  private void initWebView()
  {
    if (mScenicDesriptWebview.getData() != null)
    {
      if (!("".equals(mScenicDesriptWebview.getData().getScenicDescription())))
      {
        mwebv_scenic_descript.loadDataWithBaseURL(
            null, mScenicDesriptWebview.getData().getScenicDescription(),
            "text/html", "utf-8", null);
      }
    }
  }

  @OnClick(R.id.img_descript_back)
  public void toBack(View view)
  {
    this.finish();
  }
}
