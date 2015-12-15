package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.entity.MyWebView;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_strategy_webview)
public class StrategyDetailWebView extends BaseActivity
{
  private Map<String, Object> params = new HashMap<>();
  //解析数据的参数
  private String guides_line_order;
  private String id;
  @ViewInject(R.id.strategy_webview)
  private WebView mWebView;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    Intent intent = getIntent();
    guides_line_order = intent.getStringExtra("guides_line_order");
    id = intent.getStringExtra("id");
    //加载数据
    LoadingData();

  }

  private void LoadingData()
  {
    //查看更多
    params.put("RequestJson", "{\"guides_line_order\":\"" + guides_line_order + "\",\"guides_id\":\"" + id + "\"}");

    JDYHttpConnect.getInstance().getStrateDetailMore(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        Gson gson = new Gson();
        MyWebView myWebView = gson.fromJson(response, MyWebView.class);
        mWebView.loadDataWithBaseURL(null, myWebView.getData(), "text/html", "utf-8", null);

      }

      @Override
      public void onFailure(String error)
      {

      }
    });
  }

}
