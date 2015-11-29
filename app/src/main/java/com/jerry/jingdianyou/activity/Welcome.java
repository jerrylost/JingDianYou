package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
public class Welcome extends Activity implements Runnable
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run()
  {
    try
    {
      Thread.sleep(3000);
      isFirstRun();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

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
