package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.AboutWeclomeAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by  Jerry.Zou
 */
@ContentView(R.layout.activity_about_welcome)
public class AboutWelcomeActivity extends FragmentActivity implements ViewPager.OnPageChangeListener
{
  //存储图片的集合
  private List<View> mList = new LinkedList<View>();
  //小圆点视图
  private ImageView[] imageViews;
  //ViewPage
  @ViewInject(R.id.welcome_viewpage)
  private ViewPager mViewPager;

  //LinearLayout
  @ViewInject(R.id.welcome_linearlayout)
  private LinearLayout linearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    //初始化控件
    initView();
    //动态添加小圆点
    addPoint();

  }

  //动态添加小圆点
  private void addPoint()
  {
    imageViews = new ImageView[mList.size()];
    ImageView point = null;
    for (int i = 0; i < imageViews.length; i++)
    {
      point = new ImageView(this);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
      params.rightMargin = 10;
      point.setLayoutParams(params);
      imageViews[i] = point;
      if (i == 0)
      {
        imageViews[i].setImageResource(R.mipmap.ic_focus_select);
      }
      else
      {
        imageViews[i].setImageResource(R.mipmap.ic_focus);
      }
      linearLayout.addView(imageViews[i]);
    }
  }

  //初始化控件
  private void initView()
  {
    //添加图片
    View view = new View(getApplicationContext());
    view.setBackgroundResource(R.mipmap.help1);
    mList.add(view);

    view = new View(getApplicationContext());
    view.setBackgroundResource(R.mipmap.help2);
    mList.add(view);

    view = new View(getApplicationContext());
    view.setBackgroundResource(R.mipmap.help3);
    mList.add(view);

    view = new View(getApplicationContext());
    view.setBackgroundResource(R.mipmap.help4);
    mList.add(view);

    AboutWeclomeAdapter adapter = new AboutWeclomeAdapter(mList);
    mViewPager.setAdapter(adapter);

    //为ViewPage添加点击事件
    mViewPager.setOnPageChangeListener(this);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
  {

  }

  @Override
  public void onPageSelected(int position)
  {

    for (int i = 0; i < imageViews.length; i++)
    {
      if (i != position)
      {
        imageViews[i].setImageResource(R.mipmap.ic_focus);
      }
      else
      {
        imageViews[i].setImageResource(R.mipmap.ic_focus_select);
      }
    }
    if (position == mList.size() - 1)
    {
      mList.get(position).setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          finish();
        }
      });

    }
  }

  @Override
  public void onPageScrollStateChanged(int state)
  {

  }
}
