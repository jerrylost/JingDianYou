package com.jerry.jingdianyou.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jerry.jingdianyou.utils.DataLoadedCallBack;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;

import com.jerry.jingdianyou.fragment.NearPersonFragment;

import com.jerry.jingdianyou.fragment.NearSceneFragment;
import com.jerry.jingdianyou.tabIndicator.SlidingTabLayout;

import java.util.ArrayList;

import java.util.List;


/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_nearby)
public class NearByActivity extends FragmentActivity implements View.OnClickListener
{
  private List<Fragment> mFragments = new ArrayList<>();

  @ViewInject(R.id.st_chat_sliding)
  private SlidingTabLayout mSlidingTabLayout;

  @ViewInject(R.id.vp_nearby)
  private ViewPager mViewPager;

  @ViewInject(R.id.iv_nearby_select)
  private ImageButton mIb;
  private PopupWindow popupWindow;
  private NearPersonFragment nearPersonFragment;
  private NearSceneFragment nearSceneFragment;

  private ProgressDialog mProgressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage("正在加载中....");
    mProgressDialog.setCanceledOnTouchOutside(false);
    mProgressDialog.setCancelable(false);
    mProgressDialog.show();

    // 身边的人
    nearPersonFragment = NearPersonFragment.newInstance(new DataLoadedCallBack()
    {
      @Override
      public void onFinished()
      {
        if (mProgressDialog != null && mProgressDialog.isShowing())
        {
          mProgressDialog.dismiss();
        }
      }
    });
    mFragments.add(nearPersonFragment);

    // 身边的景
    nearSceneFragment = new NearSceneFragment();
    mFragments.add(nearSceneFragment);

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
    String[] title = {"身边的人", "身边的景"};

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

  @ViewInject(R.id.tv_select_all)
  private TextView mTvAll;
  @ViewInject(R.id.tv_select_nan)
  private TextView mTvNan;
  @ViewInject(R.id.tv_select_nv)
  private TextView mTvNv;

  public void selectNearSex(View view)
  {

    popupWindow = new PopupWindow();

    View v = LayoutInflater.from(this).inflate(R.layout.select_sex_type, null);

    mTvAll = (TextView) v.findViewById(R.id.tv_select_all);
    mTvNan = (TextView) v.findViewById(R.id.tv_select_nan);
    mTvNv = (TextView) v.findViewById(R.id.tv_select_nv);

    mTvNan.setOnClickListener(this);
    mTvAll.setOnClickListener(this);
    mTvNv.setOnClickListener(this);

    popupWindow = new PopupWindow(
        v, ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT, true);

    popupWindow.setTouchable(true);
    ColorDrawable cd = new ColorDrawable(Color.TRANSPARENT);
    popupWindow.setBackgroundDrawable(cd);

    popupWindow.showAsDropDown(view);

  }

  @Override
  public void onClick(View v)
  {
    switch (v.getId())
    {
      case R.id.tv_select_all:
        updateData("1");
        break;
      case R.id.tv_select_nan:
        updateData("2");
        break;
      case R.id.tv_select_nv:
        updateData("3");
        break;
      default:
        break;
    }
  }

  /**
   * 更新数据
   *
   * @param type
   */
  private void updateData(String type)
  {
    nearPersonFragment.updateData(type);
    popupWindow.dismiss();
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