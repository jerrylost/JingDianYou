package com.jerry.jingdianyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.OnWayDetailActivity;
import com.jerry.jingdianyou.activity.RecommendActivity;
import com.jerry.jingdianyou.activity.RendezvousActivity;
import com.jerry.jingdianyou.activity.RendezvousDetailActivity;
import com.jerry.jingdianyou.activity.SearchActivity;
import com.jerry.jingdianyou.activity.StrategyDetailActivity;
import com.jerry.jingdianyou.adapter.BannerListAdapter;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.Home;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;
import com.jerry.jingdianyou.view.RoundImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 * 首页
 */
public class HomeFragment extends Fragment
{
  @ViewInject(R.id.vp_banner)
  private ViewPager mVpBanner;

  // 存放 滚动圆点
  @ViewInject(R.id.ll_banner)
  private LinearLayout mLinearLayout;

  @ViewInject(R.id.rl_banner)
  private RelativeLayout mRelativeLayout;

  private List<Home.AdvertLinkData> mBanners =
      new LinkedList<>();

  private List<ImageView> mIvList = new LinkedList<>();
  private BannerListAdapter mAdapter;

  private int mCurrentPageNo = 0;
  private ImageView mCurrentImageView;

  // 加载网络数据的参数集合
  Map<String, Object> params = new HashMap<>();
  private Home home;

  private int ONE = 1;
  private int TWO = 3000;

  //  小景推荐
  @ViewInject(R.id.iv_theme_pic_01)
  private ImageView mIvPic01;
  @ViewInject(R.id.tv_theme_name_01)
  private TextView mTvName01;

  @ViewInject(R.id.iv_theme_pic_02)
  private ImageView mIvPic2;
  @ViewInject(R.id.tv_theme_name_02)
  private TextView mTvName02;

  @ViewInject(R.id.iv_theme_pic_03)
  private ImageView mIvPic03;
  @ViewInject(R.id.tv_theme_name_03)
  private TextView mTvName03;

  @ViewInject(R.id.iv_theme_pic_04)
  private ImageView mIvPic04;
  @ViewInject(R.id.tv_theme_name_04)
  private TextView mTvName04;

  // 约游
  @ViewInject(R.id.iv_photo)
  private ImageView mIvPhoto;

  @ViewInject(R.id.tv_destination)
  private TextView mTvDestination;

  @ViewInject(R.id.tv_member_name)
  private TextView mTvMemberName;

  @ViewInject(R.id.iv_member_photo)
  private ImageView mIvMemberPhoto;

  @ViewInject(R.id.tv_signup_amount)
  private TextView mTvSignUpAmount;

  @ViewInject(R.id.tv_rendezvous_content)
  private TextView mTvRendezvousContent;
  @ViewInject(R.id.rl_rende)
  private RelativeLayout mRlRende;

  // 在路上
  @ViewInject(R.id.iv_on_way_member_photo)
  private ImageView mIvOnWayPhoto;

  @ViewInject(R.id.tv_on_way_member_name)
  private TextView mTvOnWayName;

  @ViewInject(R.id.tv_on_way_create_date)
  private TextView mTvOnWayDate;

  @ViewInject(R.id.tv_on_way_create_place)
  private TextView mTvOnWayPlace;

  @ViewInject(R.id.tv_message_content)
  private TextView mTvMessage;

  @ViewInject(R.id.ll_on_way)
  private LinearLayout mLLOnwaay;

  // 超值推荐
  @ViewInject(R.id.iv_theme_pic_51)
  private ImageView mIvTop51;
  @ViewInject(R.id.iv_theme_pic_52)
  private ImageView mIvTop52;

  // 锦囊
  @ViewInject(R.id.ll_guides_data)
  private LinearLayout mLLGuide;
  private int width;
  private int height;
  private List<Home.GuidesData> guides_data = new ArrayList<>();

  @ViewInject(R.id.sl_home)
  private PullToRefreshScrollView mScrollView;
  private List<Home.TopThemeData> top_theme_data = new ArrayList<Home.TopThemeData>();
  private Home.RendezvousData rendezvous_data;
  private Home.OnwayData onway_data;
  private List<Home.AdvertLinkData> data = new ArrayList<Home.AdvertLinkData>();

  @ViewInject(R.id.et_search)
  private EditText mSearchKey;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {

    View view = inflater.inflate(R.layout.fg_home, container, false);

    ViewUtils.inject(this, view);

    // 初始化
    initView();

    // 加载顶部广告栏
    loadBanner();

    return view;
  }

  /**
   * 加载顶部广告栏
   */
  private void loadBanner()
  {

    params.put("RequestJson", "{\"member_id\":\"1\"}");

    JDYHttpConnect.getInstance().getBanner(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {

        mScrollView.onRefreshComplete();
        Gson gson = new Gson();

        home = gson.fromJson(response, Home.class);

        top_theme_data.clear();
        top_theme_data = home.getTop_theme_data();
        setTopThemeData();

        rendezvous_data = home.getRendezvous_data();
        setRendezvous();

        onway_data = home.getOnway_data();
        setOnway();

        guides_data.clear();
        guides_data = home.getGuides_data();
        setGuide();

        data.clear();
        data = home.getAdvert_link_data();

        if (data != null)
        {

          mBanners.clear();
          mBanners.addAll(home.getAdvert_link_data());

          addPoint();

          timerChangeBanner();
        }

        mAdapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(String error)
      {
        mScrollView.onRefreshComplete();
      }
    });
  }

  /**
   * 设置 小景推荐
   */
  private void setTopThemeData()
  {

    if (top_theme_data.size() == 0)
    {
      return;
    }
    mTvName01.setText(top_theme_data.get(0).getTheme_name());
    mTvName02.setText(top_theme_data.get(1).getTheme_name());
    mTvName03.setText(top_theme_data.get(2).getTheme_name());
    mTvName04.setText(top_theme_data.get(3).getTheme_name());

    JDYApplication.getApp().getmImageLoader().displayImage(
        top_theme_data.get(0).getTheme_pic(), mIvPic01,
        JDYApplication.getApp().getmOptions());

    JDYApplication.getApp().getmImageLoader().displayImage(
        top_theme_data.get(1).getTheme_pic(), mIvPic2,
        JDYApplication.getApp().getmOptions());

    JDYApplication.getApp().getmImageLoader().displayImage(
        top_theme_data.get(2).getTheme_pic(), mIvPic03,
        JDYApplication.getApp().getmOptions());

    JDYApplication.getApp().getmImageLoader().displayImage(
        top_theme_data.get(3).getTheme_pic(), mIvPic04,
        JDYApplication.getApp().getmOptions());

    // 设置超值推荐
    JDYApplication.getApp().getmImageLoader().displayImage(
        top_theme_data.get(4).getTheme_pic(), mIvTop51,
        JDYApplication.getApp().getmOptions());

    JDYApplication.getApp().getmImageLoader().displayImage(
        top_theme_data.get(5).getTheme_pic(), mIvTop52,
        JDYApplication.getApp().getmOptions());

  }

  @OnClick({R.id.iv_theme_pic_01, R.id.iv_theme_pic_02, R.id.iv_theme_pic_03, R.id.iv_theme_pic_04
      , R.id.iv_theme_pic_51, R.id.iv_theme_pic_52, R.id.ll_home_onway, R.id.ll_rendezvous, R.id.iv_search})
  public void doOnclick(View view)
  {
    Intent intent = new Intent();
    switch (view.getId())
    {
      case R.id.iv_theme_pic_01:
        setTopthemedata(0);
        break;
      case R.id.iv_theme_pic_02:
        setTopthemedata(1);
        break;
      case R.id.iv_theme_pic_03:
        setTopthemedata(2);
        break;
      case R.id.iv_theme_pic_04:
        setTopthemedata(3);
        break;
      case R.id.iv_theme_pic_51:
        setTopthemedata(4);
        break;
      case R.id.iv_theme_pic_52:
        setTopthemedata(5);
        break;
      case R.id.ll_rendezvous:
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(getActivity(), RendezvousDetailActivity.class);
        intent.putExtra("rendezvous_id", rendezvous_data.getRendezvous_id());
        intent.putExtra("member_id", rendezvous_data.getMember_id());
        getActivity().startActivity(intent);
        break;
      case R.id.ll_home_onway:
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(getActivity(), OnWayDetailActivity.class);
        intent.putExtra("homeonway", onway_data.getOnway_id());
        getActivity().startActivity(intent);
        break;
      case R.id.iv_search:

        String SearchKey = mSearchKey.getText().toString();
        if ("".equals(SearchKey))
        {
          Toast.makeText(getActivity(), "请先输入关键字！", Toast.LENGTH_SHORT).show();
          return;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(getActivity(), SearchActivity.class);
        intent.putExtra("key", SearchKey);
        intent.putExtra("type", 0 + "");
        getActivity().startActivity(intent);
      default:
        break;
    }
  }

  private void setTopthemedata(int index)
  {
    Intent intent = new Intent();
    intent.setClass(getActivity(), RecommendActivity.class);
    intent.putExtra("title", home.getTop_theme_data().get(index).getTheme_name());
    intent.putExtra("action", home.getTop_theme_data().get(index).getAction());
    getActivity().startActivity(intent);
  }

  /**
   * 设置约游
   */
  private void setRendezvous()
  {

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        width - 18,
        width / 4
    );

    mRlRende.setLayoutParams(params);

    if (rendezvous_data == null)
    {
      return;
    }
    mTvDestination.setText(rendezvous_data.getDestination());
    mTvMemberName.setText(rendezvous_data.getMember_name());
    mTvSignUpAmount.setText(rendezvous_data.getSignup_amount() + "人已报名");
    mTvRendezvousContent.setText(rendezvous_data.getRendezvous_content());

    JDYApplication.getApp().getmImageLoader().displayImage(
        rendezvous_data.getPhoto(), mIvPhoto,
        JDYApplication.getApp().getmOptions()
    );

    JDYApplication.getApp().getmImageLoader().displayImage(
        rendezvous_data.getMember_photo(), mIvMemberPhoto,
        JDYApplication.getApp().getmOptions()
    );
  }

  /**
   * 设置在路上
   */
  private void setOnway()
  {
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        100, 100
    );

    params.leftMargin = 15;
    if (onway_data == null)
    {
      return;
    }

    String photo = onway_data.getPhoto();
    String rex = "\\,";

    String[] arr = photo.split(rex);

    ImageView imageView = null;
    mLLOnwaay.removeAllViews();
    for (int i = 0; i < arr.length; i++)
    {
      imageView = new ImageView(getActivity());
      String path = "http://kindin-web.oss-cn-beijing.aliyuncs.com" + arr[i];
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      imageView.setLayoutParams(params);

      JDYApplication.getApp().getmImageLoader().displayImage(
          path, imageView,
          JDYApplication.getApp().getmOptions()
      );
      mLLOnwaay.addView(imageView);
    }


    mTvOnWayName.setText(onway_data.getMember_name());
    mTvOnWayDate.setText(onway_data.getCreate_date());
    mTvOnWayPlace.setText(onway_data.getCreate_place());
    mTvMessage.setText(onway_data.getMessage_content());

    JDYApplication.getApp().getmImageLoader().displayImage(
        onway_data.getMember_photo(), mIvOnWayPhoto,
        JDYApplication.getApp().getmOptions()
    );

  }

  /**
   * 设置超值推荐
   */
  private void setHotRecommend()
  {

  }

  /**
   * 设置锦囊
   */
  private void setGuide()
  {

    if (guides_data.size() == 0)
    {
      return;
    }
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        width / 3
    );
    params.topMargin = 15;
    mLLGuide.removeAllViews();
    for (int i = 0; i < guides_data.size(); i++)
    {
      View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.image_layout, null);
      view1.setLayoutParams(params);
      RoundImageView imageView = (RoundImageView) view1.findViewById(R.id.iv_guides_pic);
      TextView textView = (TextView) view1.findViewById(R.id.tv_guides_name);

      textView.setText(guides_data.get(i).getGuides_name());

      JDYApplication.getApp().getmImageLoader().displayImage(
          guides_data.get(i).getGuides_pic(), imageView,
          JDYApplication.getApp().getmOptions()
      );

      imageView.setTag(i);
      imageView.setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          int tag = (int) v.getTag();
          Intent intent = new Intent();
          intent.putExtra("guides_id", guides_data.get(tag).getGuides_id());
          intent.putExtra("pic", guides_data.get(tag).getGuides_pic());
          intent.putExtra("title", guides_data.get(tag).getGuides_name());
          intent.setClass(getActivity(), StrategyDetailActivity.class);
          getActivity().startActivity(intent);
        }
      });

      mLLGuide.addView(view1);
    }
  }

  /**
   * 定时切换
   */
  private void timerChangeBanner()
  {

    mHandler.sendEmptyMessageDelayed(ONE, TWO);
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
      mHandler.sendEmptyMessageDelayed(ONE, TWO);
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

    mLinearLayout.removeAllViews();
    LinearLayout.LayoutParams layoutParams =
        new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
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
    mIvList = new LinkedList<>();

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

    mScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
    mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>()
    {
      @Override
      public void onRefresh(PullToRefreshBase<ScrollView> refreshView)
      {

        loadBanner();
      }
    });
  }

  @Override
  public void onDestroy()
  {
    top_theme_data.clear();
    guides_data.clear();
    data.clear();
    super.onDestroy();
  }

}
