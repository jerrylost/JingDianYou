package com.jerry.jingdianyou.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class AboutWeclomeAdapter extends PagerAdapter
{
  private List<View> list;

  public AboutWeclomeAdapter(List<View> list)
  {
    this.list = list;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position)
  {
    View view = list.get(position);
    container.addView(view);
    return view;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object)
  {
    container.removeView(list.get(position));
  }

  @Override
  public int getCount()
  {
    return list.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object)
  {
    return view == object;
  }
}
