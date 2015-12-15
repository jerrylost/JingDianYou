package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_requirement)
public class RequirementActivtiy extends BaseActivity
{
  @ViewInject(R.id.ll_business_hours)
  private LinearLayout mll_business_hours;

  @ViewInject(R.id.ll_productCost)
  private LinearLayout mll_productCost;

  @ViewInject(R.id.ll_enter_mode)
  private LinearLayout mll_enter_mode;

  @ViewInject(R.id.ll_backTo_rule)
  private LinearLayout mll_backTo_rule;

  @ViewInject(R.id.ll_kindly_reminder)
  private LinearLayout mll_kindly_reminder;

  @ViewInject(R.id.ll_preference_policy)
  private LinearLayout mll_preference_policy;

  @ViewInject(R.id.tv_business_hours)
  private TextView mtv_business_hours;

  @ViewInject(R.id.tv_productCost)
  private TextView mtv_productCost;

  @ViewInject(R.id.tv_enter_mode)
  private TextView mtv_enter_mode;

  @ViewInject(R.id.tv_backTo_rule)
  private TextView mtv_backTo_rule;

  @ViewInject(R.id.tv_kindly_reminder)
  private TextView mtv_kindly_reminder;

  @ViewInject(R.id.tv_preference_policy)
  private TextView mtv_preference_policy;

  private String[] requirements = new String[6];

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    Intent intent = getIntent();
    requirements = intent.getStringArrayExtra("requirements");
    initViews();
  }

  //requirements = new String[]{business_hours,productCost,enter_mode,backTo_rule,kindly_reminder};
  private void initViews()
  {
    if ("".equals(requirements[0]))
    {
      mll_business_hours.setVisibility(View.GONE);
    }
    else
    {
      mtv_business_hours.setText(requirements[0]);
    }

    if ("".equals(requirements[1]))
    {
      mll_productCost.setVisibility(View.GONE);
    }
    else
    {
      mtv_productCost.setText(requirements[1]);
    }

    if ("".equals(requirements[2]))
    {
      mll_enter_mode.setVisibility(View.GONE);
    }
    else
    {
      mtv_enter_mode.setText(requirements[2]);
    }

    if ("".equals(requirements[3]))
    {
      mll_backTo_rule.setVisibility(View.GONE);
    }
    else
    {
      mtv_backTo_rule.setText(requirements[3]);
    }

    if ("".equals(requirements[4]))
    {
      mll_kindly_reminder.setVisibility(View.GONE);
    }
    else
    {
      mtv_kindly_reminder.setText(requirements[4]);
    }

    if ("".equals(requirements[5]))
    {
      mll_preference_policy.setVisibility(View.GONE);
    }
    else
    {
      mtv_preference_policy.setText(requirements[4]);
    }
  }

}
