package com.jerry.jingdianyou.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class GuideAdapter extends PagerAdapter
{
  private List<View> list;

  public GuideAdapter(List<View> list)
  {
    this.list = list;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position)
  {
    container.addView(list.get(position));
    return list.get(position);
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object)
  {
    container.removeView(list.get(position));
  }

  @Override
  public int getCount()
  {
    return list == null ? 0 : list.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object)
  {
    return view == object;
  }
}
