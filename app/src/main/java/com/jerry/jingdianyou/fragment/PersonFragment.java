package com.jerry.jingdianyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.AboutWeActivity;
import com.jerry.jingdianyou.activity.CollectActivity;
import com.jerry.jingdianyou.activity.LoginActivity;
import com.jerry.jingdianyou.activity.MyWalking;
import com.jerry.jingdianyou.activity.PersonMessage;

/**
 * Created by Jerry.Zou
 */

/**
 * 我的
 */
public class PersonFragment extends Fragment
{
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {

    View view = inflater.inflate(R.layout.fg_person, container, false);

    ViewUtils.inject(this, view);

    return view;
  }

  /**
   * 点击事件
   *
   * @param view
   */
  @OnClick({R.id.rl_my_achievement, R.id.ll_login, R.id.rl_base_msg, R.id.rl_my_collect,
      R.id.rl_my_coupon, R.id.rl_my_order, R.id.rl_my_spool, R.id.rl_about_us})
  public void onClick(View view)
  {
    Intent intent = new Intent();

    switch (view.getId())
    {
      case R.id.ll_login://登录
        intent.setClass(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
        break;
      case R.id.rl_my_spool://我的足迹
        intent.setClass(getActivity(), MyWalking.class);
        getActivity().startActivity(intent);
        break;
      case R.id.rl_my_order://我的订单
        break;
      case R.id.rl_my_coupon://我的现金券
        break;
      case R.id.rl_my_collect://我的收藏
        intent.setClass(getActivity(), CollectActivity.class);
        getActivity().startActivity(intent);
        break;
      case R.id.rl_my_achievement://我的成就
        break;
      case R.id.rl_about_us://关于我们
        intent.setClass(getActivity(), AboutWeActivity.class);
        getActivity().startActivity(intent);
        break;
      case R.id.rl_base_msg://基本信息
        intent.setClass(getActivity(), PersonMessage.class);
        getActivity().startActivity(intent);
        break;
    }
  }
}
