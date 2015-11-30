package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
public class WelcomeActivity extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    mHandler.sendEmptyMessageDelayed(1,3000);
  }

  @Override
  protected void onDestroy()
  {
    try
    {
      isFirstRun();
    }
  };

  public void isFirstRun()
  {
    Intent intent = new Intent();
    SharedPreferences sp = getSharedPreferences("jingdianyou", MODE_PRIVATE);
    boolean isFirstRun = sp.getBoolean("isfirst", true);
    if (isFirstRun)
    {
      intent.setClass(this, GuideActivity.class);
    }
    else
    {
      intent.setClass(this, MainActivity.class);

    }
    startActivity(intent);
    finish();
  }
}
