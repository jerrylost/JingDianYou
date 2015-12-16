package com.jerry.jingdianyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jerry.jingdianyou.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_about_we)
public class AboutWeActivity extends BaseActivity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
  }

  /**
   * 返回
   *
   * @param view
   */
  public void onAboutBack(View view)
  {
    finish();
  }

  /**
   * 欢迎页面
   *
   * @param view
   */
  public void onWelcome(View view)
  {
    Intent intent = new Intent(this, AboutWelcomeActivity.class);
    startActivity(intent);
  }

  //公司介绍
  public void companyIntroduce(View view)
  {
    Intent intent = new Intent(this, CompanyIntroduceActivity.class);
    startActivity(intent);
  }
}
