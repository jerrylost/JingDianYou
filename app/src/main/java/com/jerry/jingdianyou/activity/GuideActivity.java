package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jerry.jingdianyou.constant.App;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.GuideAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_guide)
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener
{
  @ViewInject(R.id.guide_text_jump)
  private TextView mJump;

  @ViewInject(R.id.guide_page)
  private ViewPager mViewPager;

  @ViewInject(R.id.guide_layout)
  private LinearLayout mLinearLayout;

  //
  private List<View> mList = new LinkedList<View>();
  private SharedPreferences sp;
  private SharedPreferences.Editor edit;
  private ImageView[] mImageViews;
  private int mCurrentPageN0 = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    sp = getSharedPreferences(App.SP_NAME, MODE_PRIVATE);
    edit = sp.edit();

    //对跳过设置监听
    mJump.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        edit.putBoolean(App.SP_KEY_FIRST_NAME, false);
        edit.commit();

        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
      }
    });

    //添加图片
    onAddView();

    //动态添加圆点
    addPoints();
  }

  /**
   * 添加导航圆点
   */
  private void addPoints()
  {
    mImageViews = new ImageView[mList.size()];

    ImageView point = null;

    for (int i = 0; i < mList.size(); i++)
    {
      point = new ImageView(this);

      // set background selector
      point.setBackgroundResource(R.drawable.selector_nav_point);

      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
      params.rightMargin = 10;
      point.setLayoutParams(params);

      mImageViews[i] = point;

      mLinearLayout.addView(point);
    }

    mImageViews[0].setSelected(true);
    mCurrentPageN0 = 0;
  }

  private void onAddView()
  {
    View view = new View(this);
    view.setBackgroundResource(R.mipmap.help1);
    mList.add(view);

    view = new View(this);
    view.setBackgroundResource(R.mipmap.help2);
    mList.add(view);

    view = new View(this);
    view.setBackgroundResource(R.mipmap.help3);
    mList.add(view);

    view = new View(this);
    view.setBackgroundResource(R.mipmap.help4);
    mList.add(view);

    GuideAdapter adapter = new GuideAdapter(mList);
    mViewPager.setAdapter(adapter);
    mViewPager.setOnPageChangeListener(this);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
  {

  }

  @Override
  public void onPageSelected(int position)
  {
    if (position == mList.size() - 1)
    {
      mJump.setVisibility(View.INVISIBLE);
      mList.get(position).setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          edit.putBoolean(App.SP_KEY_FIRST_NAME, false);
          edit.commit();

          Intent intent = new Intent(GuideActivity.this, MainActivity.class);
          startActivity(intent);

          finish();
        }
      });
    }
    else
    {
      mJump.setVisibility(View.VISIBLE);
    }

    // set selector point
    mImageViews[mCurrentPageN0].setSelected(false);
    mCurrentPageN0 = position;
    mImageViews[position].setSelected(true);
  }

  @Override
  public void onPageScrollStateChanged(int state)
  {

  }
}
