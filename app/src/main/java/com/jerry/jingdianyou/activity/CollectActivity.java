package com.jerry.jingdianyou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.fragment.GonglueFragment;
import com.jerry.jingdianyou.fragment.jingdianFragment;
import com.jerry.jingdianyou.fragment.yueyouFragment;
import com.jerry.jingdianyou.tabIndicator.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_collect)
public class CollectActivity extends FragmentActivity
{
  @ViewInject(R.id.collect_slidingtab)
  private SlidingTabLayout mSlidingTabLayout;

  @ViewInject(R.id.collect_page)
  private ViewPager mViewPager;

  private List<Fragment> mListFragment = new ArrayList<>();

  // 显示标题
  private String titles[] = {getString(R.string.title_scenic),
                             getString(R.string.title_strategy),
                             getString(R.string.title_arrange_travel)};

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    mListFragment.add(new GonglueFragment());
    mListFragment.add(new jingdianFragment());
    mListFragment.add(new yueyouFragment());
    //初始化控件
    initView();
  }

  /**
   * 初始化视图
   */
  private void initView()
  {
    //颜色
    mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.green));

    //页面个数
    // mViewPager.setOffscreenPageLimit(4);

    //铺满
    mSlidingTabLayout.setDistributeEvenly(true);

    //加载的布局类型和数据显示的控件id
    mSlidingTabLayout.setCustomTabView(R.layout.sliding_layout, R.id.text);

    mViewPager.setAdapter(new Collect(getSupportFragmentManager()));

    mSlidingTabLayout.setViewPager(mViewPager);
  }

  public class Collect extends FragmentPagerAdapter
  {
    public Collect(FragmentManager fm)
    {
      super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
      return mListFragment.get(position);
    }

    @Override
    public int getCount()
    {
      return mListFragment == null ? 0 : mListFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
      return titles[position];
    }
  }

  public void doBack(View view)
  {
    finish();
  }
}
