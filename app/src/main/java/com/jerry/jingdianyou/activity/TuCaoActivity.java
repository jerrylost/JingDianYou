package com.jerry.jingdianyou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;

import com.jerry.jingdianyou.fragment.TuCaoFragment;
import com.jerry.jingdianyou.tabIndicator.SlidingTabLayout;

import java.util.ArrayList;

import java.util.List;


/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_tucao)
public class TuCaoActivity extends FragmentActivity
{
  private List<Fragment> mFragments = new ArrayList<>();

  @ViewInject(R.id.st_sliding)
  private SlidingTabLayout mSlidingTabLayout;

  @ViewInject(R.id.vp_tocao)
  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    mFragments.add(TuCaoFragment.getInstance("0"));
    mFragments.add(TuCaoFragment.getInstance("1"));

    mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.green));
    mViewPager.setOffscreenPageLimit(2);
    mSlidingTabLayout.setDistributeEvenly(true);
    mSlidingTabLayout.setCustomTabView(R.layout.sliding_layout, R.id.text);
    MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(adapter);
    mSlidingTabLayout.setViewPager(mViewPager);

  }

  class MyAdapter extends FragmentStatePagerAdapter
  {
    String[] title = {"全部", "精华"};

    public MyAdapter(FragmentManager fm)
    {
      super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
      return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
      return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
      return title[position];
    }
  }

  public void doBack(View view)
  {
    this.finish();
    this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
  }

  @Override
  public void onBackPressed()
  {
    this.finish();
    this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
  }
}