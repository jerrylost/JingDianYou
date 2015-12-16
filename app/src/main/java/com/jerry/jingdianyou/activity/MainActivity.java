package com.jerry.jingdianyou.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.application.AppManager;
import com.jerry.jingdianyou.fragment.HomeFragment;
import com.jerry.jingdianyou.fragment.PersonFragment;
import com.jerry.jingdianyou.fragment.RendezvousFragment;
import com.jerry.jingdianyou.fragment.ScenicFragment;
import com.jerry.jingdianyou.fragment.StrategyFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.LinkedList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener
{
  // 主页Fragment集合
  private List<Fragment> mFgList = new LinkedList<Fragment>();

  @ViewInject(R.id.rg_main)
  private RadioGroup mRadioGroup;

  @ViewInject(R.id.rb_main_home)
  private RadioButton mHome;
  @ViewInject(R.id.rb_main_person)
  private RadioButton mPerson;
  @ViewInject(R.id.rb_main_rendezvous)
  private RadioButton mRende;
  @ViewInject(R.id.rb_main_scenic)
  private RadioButton mScenic;
  @ViewInject(R.id.rb_main_strategy)
  private RadioButton mStrategy;

  // 退出时间
  private long mExitTime = 0;

  //定位功能的入口
  private LocationClient client;

  private BDLocationListener locationListener = new BDLocationListener()
  {
    @Override
    public void onReceiveLocation(BDLocation bdLocation)
    {
      //获取定位的状态码
      int locType = bdLocation.getLocType();

      //获取当前的经纬度
      //纬度
      double latitude = bdLocation.getLatitude();

      //经度
      double longitude = bdLocation.getLongitude();

      switch (locType)
      {

        //网络定位
        case BDLocation.TypeNetWorkLocation:

          //GPS
        case BDLocation.TypeGpsLocation:

          //缓存
        case BDLocation.TypeCacheLocation:

          //离线定位模式
        case BDLocation.TypeOffLineLocation:

          sendLocation(latitude, longitude);
          break;
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    initView();

    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    int widthPixels = metrics.widthPixels;

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
    mRadioGroup.setLayoutParams(params);

    LinearLayout.LayoutParams rbParams
            = new LinearLayout.LayoutParams(
                  (widthPixels - 10) / 5, ViewGroup.LayoutParams.WRAP_CONTENT);

    mHome.setLayoutParams(rbParams);
    mRende.setLayoutParams(rbParams);
    mScenic.setLayoutParams(rbParams);
    mStrategy.setLayoutParams(rbParams);
    mPerson.setLayoutParams(rbParams);

    FragmentManager manager = getSupportFragmentManager();

    FragmentTransaction transaction = manager.beginTransaction();

    // 添加 fragment 到容器中，并隐藏
    if (mFgList.size() > 0)
    {

      for (Fragment fragment : mFgList)
      {

        transaction.add(R.id.fg_container, fragment).hide(fragment);
      }

      // 显示 HomeFragment
      transaction.show(mFgList.get(0));

      transaction.commit();
    }

    mRadioGroup.setOnCheckedChangeListener(this);

    //定位的操作
    //初始化定位功能
    client = new LocationClient(getApplicationContext());

    //注册定位服务的接收
    client.registerLocationListener(locationListener);

    initLocationClient();

    client.start();

    //主动开启一次定位
    client.requestLocation();

  }

  private void initLocationClient()
  {
    LocationClientOption locationClientOption = new LocationClientOption();

    //是否打开 GPS
    locationClientOption.setOpenGps(true);

    //设置为 0 只定位 一次
    locationClientOption.setScanSpan(0);

    //是否包含返回地址，这个地址根据 经纬度来 设置的
    locationClientOption.setIsNeedAddress(true);

    //设置为百度自己的地图坐标系统，默认是 gcj02
    locationClientOption.setCoorType("bd0911");

    //设置当前定位模式
    //设置为高精度
    locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

    //设置定位 客户端 的定位设置 和需求
    client.setLocOption(locationClientOption);
  }

  /**
   * 初始化 fragment
   */
  private void initView()
  {
    if (mFgList.size() == 0)
    {
      mFgList.add(new HomeFragment());

      mFgList.add(new StrategyFragment());

      mFgList.add(new ScenicFragment());

      mFgList.add(new RendezvousFragment());

      mFgList.add(new PersonFragment());
    }
  }

  /**
   * RadioGroup 点击事件
   *
   * @param group
   * @param checkedId
   */
  @Override
  public void onCheckedChanged(RadioGroup group, int checkedId)
  {

    int index = 0;

    switch (checkedId)
    {
      case R.id.rb_main_home:
        index = 0;
        break;
      case R.id.rb_main_strategy:
        index = 1;
        break;
      case R.id.rb_main_scenic:
        index = 2;
        break;
      case R.id.rb_main_rendezvous:
        index = 3;
        break;
      case R.id.rb_main_person:
        index = 4;
        break;
      default:
        break;
    }

    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    for (int i = 0; i < mFgList.size(); i++)
    {

      if (i == index)
      {
        transaction.show(mFgList.get(i));
      }
      else
      {
        transaction.hide(mFgList.get(i));
      }
    }

    transaction.commit();

  }

  /**
   * 后退键处理
   */
  @Override
  public void onBackPressed()
  {
    if (mExitTime == 0)
    {
      Toast.makeText(this, R.string.exit_text_tip, Toast.LENGTH_SHORT).show();
      mExitTime = System.currentTimeMillis();
    }
    else
    {
      if (mExitTime - System.currentTimeMillis() <= 3000)
      {
        this.finish();
        AppManager.getAppManager().AppExit(this,false);
      }
    }
  }

  // 发送定位广播
  public void sendLocation(double latitude, double longitude)
  {
    SharedPreferences sp = getSharedPreferences("jingdianyou", MODE_PRIVATE);
    SharedPreferences.Editor edit = sp.edit();
    edit.putString("latitude", String.valueOf(latitude));
    edit.putString("longitude", String.valueOf(longitude));
    edit.commit();
  }
}
