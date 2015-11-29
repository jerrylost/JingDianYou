package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_recomment)
public class RecommendActivity extends Activity
{
  @ViewInject(R.id.wv_recomment)
  private WebView mWebView;
  @ViewInject(R.id.tv_header)
  private TextView mTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    Intent intent = getIntent();
    String action = intent.getStringExtra("action");
    String title = intent.getStringExtra("title");

    //设置WebView属性，能够执行Javascript脚本
    mWebView.getSettings().setJavaScriptEnabled(true);

    mWebView.setWebViewClient(new WebViewClient()
    {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url)
      {
        view.loadUrl(url);
        return false;
      }
    });

    if (action != null && title != null)
    {
      mTitle.setText(title);
      //加载需要显示的网页
      mWebView.loadUrl(action);
    }
    else
    {
      this.finish();
      this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
    }
  }

  @Override
  //设置回退
  //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
  public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())
    {
      mWebView.goBack(); //goBack()表示返回WebView的上一页面
      return true;
    }
    else
    {
      this.finish();
      this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
    }
    return false;
  }

  public void doBack(View view)
  {
    this.finish();
    this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
  }

  @Override
  public void onBackPressed()
  {
    this.finish();
    this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
  }
}
