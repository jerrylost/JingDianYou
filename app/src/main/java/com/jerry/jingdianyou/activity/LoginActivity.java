package com.jerry.jingdianyou.activity;

import android.app.Activity;


import android.os.Bundle;
import android.view.View;

import com.jerry.jingdianyou.R;


/**
 * Created by Jerry.Zou
 */

public class LoginActivity extends BaseActivity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  public void doLoginBack(View view)
  {
    finish();
  }
}
