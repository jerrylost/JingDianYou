package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.page_signup)
public class PersonMessage extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
  }

  public void onBack(View view)
  {
    finish();
  }
}
