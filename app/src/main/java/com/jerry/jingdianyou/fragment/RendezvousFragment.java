package com.jerry.jingdianyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.ChatActivity;
import com.jerry.jingdianyou.activity.NearByActivity;
import com.jerry.jingdianyou.activity.OnWayActivity;
import com.jerry.jingdianyou.activity.RendezvousActivity;
import com.jerry.jingdianyou.activity.TuCaoActivity;
import com.jerry.jingdianyou.adapter.BannerListAdapter;
import com.jerry.jingdianyou.entity.AdvertList;
import com.jerry.jingdianyou.entity.Home;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 * 约游
 */
public class RendezvousFragment extends Fragment
{
  @ViewInject(R.id.vp_banner)
  private ViewPager mVpBanner;

  // 存放 滚动圆点
  @ViewInject(R.id.ll_banner)
  private LinearLayout mLinearLayout;

  @ViewInject(R.id.rl_banner)
  private RelativeLayout mRelativeLayout;

  private List<AdvertList.Data> mBanners =
      new LinkedList<>();

  private List<ImageView> mIvList = new LinkedList<>();
  private BannerListAdapter mAdapter;

  private int mCurrentPageNo = 0;
  private ImageView mCurrentImageView;

  // 加载网络数据的参数集合
  Map<String, Object> params = new HashMap<>();
  private AdvertList advert;

  private int WHAT_1 = 1;
  private int time = 3000;
  private int width = 0;
  private int height = 0;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fg_rendezvous, container, false);

    ViewUtils.inject(this, view);

    // 初始化
    initView();

    // 加载顶部广告栏
    loadAdvert();

    return view;
  }

  /**
   * 加载顶部广告栏
   */
  private void loadAdvert()
  {
    params.put("RequestJson",
        "{\"adv_position\":\"3\",\"sizeTypeHeight\":200,\"sizeTypeWidth\":720}");

    JDYHttpConnect.getInstance().getAdvert(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        Gson gson = new Gson();
        advert = gson.fromJson(response, AdvertList.class);
        List<AdvertList.Data> data = advert.getData();

        if (data != null)
        {
          mBanners.addAll(data);

          addPoint();

          timerChangeBanner();
        }
        mAdapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(String error)
      {

      }
    });
  }

  /**
   * 定时切换
   */
  private void timerChangeBanner()
  {
    mHandler.sendEmptyMessageDelayed(WHAT_1, time);
  }

  private Handler mHandler = new Handler()
  {
    @Override
    public void handleMessage(Message msg)
    {
      mCurrentPageNo = ++mCurrentPageNo % mIvList.size();
      mCurrentImageView.setSelected(false);
      mCurrentImageView = mIvList.get(mCurrentPageNo);
      mCurrentImageView.setSelected(true);

      // mVpBanner 选择对应
      // 圆点切换
      mVpBanner.setCurrentItem(mCurrentPageNo);
      mHandler.sendEmptyMessageDelayed(WHAT_1, time);
    }
  };

  /**
   * 添加圆点
   */
  private void addPoint()
  {
    if (mBanners == null || mBanners.isEmpty())
    {
      return;
    }

    LinearLayout.LayoutParams layoutParams =
              new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT);
    layoutParams.rightMargin = 15;

    for (int i = 0; i < mBanners.size(); i++)
    {
      mCurrentImageView = new ImageView(getActivity());
      mCurrentImageView.setLayoutParams(layoutParams);
      mCurrentImageView.setSelected(false);
      mCurrentImageView.setImageResource(R.drawable.selector_banner);
      mIvList.add(mCurrentImageView);
      mLinearLayout.addView(mCurrentImageView, i);
    }

    mCurrentImageView = mIvList.get(0);
    mCurrentImageView.setSelected(true);
  }

  /**
   * 初始化视图 ViewPager 导航 设置ViewPager 的高度
   */
  private void initView()
  {
    mIvList = new LinkedList<ImageView>();

    mAdapter = new BannerListAdapter(mBanners, getActivity());

    //获取屏幕的尺寸
    DisplayMetrics metrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

    width = metrics.widthPixels;
    height = metrics.heightPixels;

    //设置 ViewPager高度
    ViewGroup.LayoutParams layoutParams = mRelativeLayout.getLayoutParams();
    layoutParams.height = metrics.heightPixels / 3;
    layoutParams.width = metrics.widthPixels;

    mRelativeLayout.setLayoutParams(layoutParams);

    mVpBanner.setAdapter(mAdapter);
    mVpBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
      {

      }

      @Override
      public void onPageSelected(int position)
      {
        if (mCurrentImageView == null)
        {
          return;
        }
        mCurrentPageNo = position;
        mCurrentImageView.setSelected(false);
        mCurrentImageView = mIvList.get(position);
        mCurrentImageView.setSelected(true);
      }

      @Override
      public void onPageScrollStateChanged(int state)
      {
      }
    });

  }

  @OnClick({R.id.ll_rendezvous_you, R.id.ll_rendezvous_onway, R.id.ll_rendezvous_tucao
      , R.id.ll_rendezvous_chat, R.id.ll_rendezvous_nearby})
  public void onClick(View view)
  {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    switch (view.getId())
    {
      case R.id.ll_rendezvous_you:

        intent.setClass(getActivity(), RendezvousActivity.class);
        getActivity().startActivity(intent);
        break;
      case R.id.ll_rendezvous_onway:
        intent.setClass(getActivity(), OnWayActivity.class);
        getActivity().startActivity(intent);
        break;
      case R.id.ll_rendezvous_tucao:
        intent.setClass(getActivity(), TuCaoActivity.class);
        getActivity().startActivity(intent);
        break;
      case R.id.ll_rendezvous_chat:
        intent.setClass(getActivity(), ChatActivity.class);
        getActivity().startActivity(intent);
        break;
      case R.id.ll_rendezvous_nearby:
        intent.setClass(getActivity(), NearByActivity.class);
        getActivity().startActivity(intent);
        break;
      default:
        break;
    }
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
    mBanners.clear();
    mIvList.clear();
    mHandler.removeCallbacksAndMessages(null);
  }
}
