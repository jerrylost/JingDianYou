package com.jerry.jingdianyou.fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.ScenicContainerAdapter;
import com.jerry.jingdianyou.entity.ScenicFragItem;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
public class FragScenicContainer extends Fragment
{

  @ViewInject(R.id.pull_to_refresh_scenic_cotent)
  private PullToRefreshListView mScenicList;

  private JDYHttpConnect mHttpConnect;
  //定义一个map集合用来存放请求参数
  private Map<String, Object> params = new HashMap<>();
  //横坐标
  private String mPosition_x = "";
  //纵坐标
  private String mPosition_y = "";
  //    //景点类型
  private String mScenic_Type = "2";
  //每一页数据量
  private int mPageSize = 10;
  //选择的城市
  private String mCity;
  //页码
  private int mPageIndex = 1;
  private ScenicContainerAdapter mAdapter;
  private ProgressDialog mProgressDialog;
  private List<ScenicFragItem.Data> mScenics = new ArrayList<>();
  private static final String SCCESS = "0";

  private String cityName = "";

  public static FragScenicContainer getInstance(String type)
  {
    FragScenicContainer fragment = new FragScenicContainer();
    Bundle bundle = new Bundle();
    bundle.putString("type", type);
    fragment.setArguments(bundle);
    return fragment;
  }

  private BroadcastReceiver receiver = new BroadcastReceiver()
  {
    @Override
    public void onReceive(Context context, Intent intent)
    {
      String action = intent.getAction();
      if ("city".equals(action))
      {
        cityName = intent.getStringExtra("cityName");
        mPosition_y = "";
        mPosition_x = "";
        loadJingDianData();
      }
    }
  };

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    IntentFilter filter = new IntentFilter();
    filter.addAction("city");
    getActivity().registerReceiver(receiver, filter);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fg_scenic_cnotent, container, false);

    ViewUtils.inject(this, view);

    SharedPreferences sp = getActivity().getSharedPreferences("jingdianyou", Context.MODE_PRIVATE);
    mPosition_y = sp.getString("latitude", null);
    mPosition_x = sp.getString("longitude", null);

    Bundle bundle = getArguments();
    mScenic_Type = bundle.getString("type");
    mHttpConnect = JDYHttpConnect.getInstance();
    initViews();
    loadJingDianData();
    return view;
  }

  private void loadJingDianData()
  {
    //  请求数据所需要的参数是一个json语句
    //RequestJson      {"pageSize":"10","position-x":"1.000","position-y":"1.000","city":"","pageIndex":"1","scenic_type":"2"}
    params.put("RequestJson", "{" +
        "\"pageSize\":\"10\"," +
        "\"position-x\":\"" + mPosition_x + "\"," +
        "\"position-y\":\"" + mPosition_y + "\"," +
        "\"city\":\"" + cityName + "\"," +
        "\"pageIndex\":\"" + mPageIndex + "\"," +
        "\"scenic_type\":\"" + mScenic_Type + "\"}");
    mHttpConnect.getScenicList(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        hidLoading();
        //返回景区列表信息
        Gson gson = new Gson();
        ScenicFragItem scenic = gson.fromJson(response, ScenicFragItem.class);
        if (SCCESS.equals(scenic.getResultStatus()))
        {
          mScenics.addAll(scenic.getData());
          mAdapter.notifyDataSetChanged();
        }
      }

      @Override
      public void onFailure(String error)
      {

      }
    });

  }

  private void initViews()
  {
    mAdapter = new ScenicContainerAdapter(getActivity(), mScenics);
    mScenicList.setAdapter(mAdapter);
    mScenicList.setMode(PullToRefreshBase.Mode.BOTH);
    mScenicList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
    {
      @Override
      public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        mScenics.clear();
        mPageIndex = 1;
        loadJingDianData();
        mAdapter.notifyDataSetChanged();
      }

      @Override
      public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        mPageIndex++;
        loadJingDianData();
      }
    });
    //显示正在加载
    mProgressDialog = new ProgressDialog(getActivity());
    mProgressDialog.setMessage("正在加载中...");
    mProgressDialog.setCancelable(false);
    //mProgressDialog.show();
  }

  private void hidLoading()
  {
    if (mScenicList.isRefreshing())
    {
      mScenicList.onRefreshComplete();
    }
    if (mProgressDialog != null && mProgressDialog.isShowing())
    {
      mProgressDialog.dismiss();
    }
  }


}
