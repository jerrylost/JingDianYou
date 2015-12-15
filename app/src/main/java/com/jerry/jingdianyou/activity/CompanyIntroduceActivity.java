package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.os.Bundle;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_company_introduce)
public class CompanyIntroduceActivity extends BaseActivity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
  }
}
