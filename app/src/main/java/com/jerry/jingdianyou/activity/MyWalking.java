package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_mywalking)
public class MyWalking extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

  }

  public void doBack(View view)
  {
    finish();
  }
}
