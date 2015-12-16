package com.jerry.jingdianyou.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.CityActivity;
import com.jerry.jingdianyou.activity.SearchActivity;
import com.jerry.jingdianyou.tabIndicator.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry.Zou
 * 景点
 */
public class ScenicFragment extends Fragment implements View.OnClickListener
{
  private List<Fragment> mFragments = new ArrayList<>();

  @ViewInject(R.id.st_scenic_sliding)
  private SlidingTabLayout mSlidingTabLayout;

  @ViewInject(R.id.fg_scenic_vp_container)
  private ViewPager mViewPager;

  @ViewInject(R.id.et_search)
  private EditText mKey;

  @ViewInject(R.id.btn_allcity)
  private Button mCitySelect;

  private BroadcastReceiver receiver = new BroadcastReceiver()
  {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      String action = intent.getAction();
      if ("city".equals(action))
      {
        cityName = intent.getStringExtra("cityName");
        mCitySelect.setText(cityName);
      }
    }
  };

  private String cityCode;
  private String cityName;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    IntentFilter filter = new IntentFilter();
    filter.addAction("city");
    getActivity().registerReceiver(receiver, filter);
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
    getActivity().unregisterReceiver(receiver);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fg_scenic, container, false);

    ViewUtils.inject(this, view);

    mFragments.add(FragScenicContainer.getInstance("1"));
    mFragments.add(FragScenicContainer.getInstance("2"));
    mFragments.add(FragScenicContainer.getInstance("3"));

    mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.green));
    //mViewPager.setOffscreenPageLimit(2);

    mSlidingTabLayout.setDistributeEvenly(true);

    mSlidingTabLayout.setCustomTabView(R.layout.sliding_layout, R.id.text);

    MyAdapter adapter = new MyAdapter(getFragmentManager());
    mViewPager.setAdapter(adapter);

    mSlidingTabLayout.setViewPager(mViewPager);

    mViewPager.setCurrentItem(1);

    mCitySelect.setOnClickListener(this);

    return view;
  }

  /**
   * 城市选择
   *
   * @param v
   */
  @Override
  public void onClick(View v)
  {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setClass(getActivity(), CityActivity.class);
    getActivity().startActivity(intent);
  }

  class MyAdapter extends FragmentStatePagerAdapter
  {
    String[] title = {"周边", "国内", "境外"};

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

  @OnClick(R.id.iv_search)
  public void doSearch(View view)
  {
    String key = mKey.getText().toString();
    if ("".equals(key))
    {
      Toast.makeText(getActivity(), "请先输入关键字!", Toast.LENGTH_SHORT).show();
      return;
    }
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setClass(getActivity(), SearchActivity.class);
    intent.putExtra("key", key);
    intent.putExtra("type", 0 + "");
    getActivity().startActivity(intent);
  }
}
