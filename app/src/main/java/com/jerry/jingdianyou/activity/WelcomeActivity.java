package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.constant.App;

/**
 * Created by Jerry.Zou
 */
public class WelcomeActivity extends Activity
{
  private SharedPreferences sp = null;

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
    super.onDestroy();

    // remove all callback and message
    mHandler.removeCallbacksAndMessages(null);
  }

  private Handler mHandler = new Handler()
  {
    @Override
    public void handleMessage(Message msg)
    {
      isFirstRun();
    }
  };

  public void isFirstRun()
  {
    Intent intent = new Intent();

    sp = getSharedPreferences(App.SP_NAME, MODE_PRIVATE);

    boolean isFirstRun = sp.getBoolean(App.SP_KEY_FIRST_NAME, true);

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
