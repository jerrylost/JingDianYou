package com.jerry.jingdianyou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.TuCaoAdapter;
import com.jerry.jingdianyou.entity.TuCao;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
public class TuCaoFragment extends Fragment
{
  @ViewInject(R.id.lv_fg_tucao)
  private PullToRefreshListView mLvTuCao;
  private List<TuCao.Data> mList = new LinkedList<>();
  private TuCaoAdapter mAdapter;
  private Map<String, Object> params = new HashMap<>();

  private String current_message_id = "";
  private int index = 165;
  private int widthPixels;

  public static TuCaoFragment getInstance(String type)
  {
    TuCaoFragment fragment = new TuCaoFragment();
    Bundle bundle = new Bundle();
    bundle.putString("type", type);
    fragment.setArguments(bundle);
    return fragment;
  }

  private String message_type = "0";

  public TuCaoFragment()
  {
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fg_tucao, container, false);
    ViewUtils.inject(this, view);
    Bundle bundle = getArguments();
    String type = bundle.getString("type");

    if ("".equals(type))
    {
    }
    else
    {
      message_type = type;
    }

    initView();
    loadData();
    return view;
  }

  /**
   * 加载数据
   */
  private void loadData()
  {
    params.put("RequestJson", "{\"page_size\":\"10\",\"message_type\":\"" + message_type + "\"," +
        "\"member_id\":\"\",\"current_message_id\":\"" + current_message_id + "\"}");
    JDYHttpConnect.getInstance().getTuCao(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        mLvTuCao.onRefreshComplete();
        Gson gson = new Gson();
        TuCao tuCao = gson.fromJson(response, TuCao.class);
        if (tuCao != null)
        {
          List<TuCao.Data> data = tuCao.getData();
          if (data.size() > 0)
          {
            mList.addAll(data);
            mAdapter.notifyDataSetChanged();
          }
        }
      }

      @Override
      public void onFailure(String error)
      {
        mLvTuCao.onRefreshComplete();
      }
    });
  }

  /**
   * 初始化视图
   */
  private void initView()
  {

    DisplayMetrics metrics = new DisplayMetrics();

    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

    widthPixels = metrics.widthPixels;

    mAdapter = new TuCaoAdapter(getActivity(), mList, widthPixels);
    mLvTuCao.setAdapter(mAdapter);

    mLvTuCao.setMode(PullToRefreshBase.Mode.BOTH);

    mLvTuCao.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
    {
      @Override
      public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        current_message_id = "";
        loadData();
      }

      @Override
      public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        current_message_id = "VI000000000000000" + index;
        loadData();
        index = index - 10;
      }
    });

  }
}

