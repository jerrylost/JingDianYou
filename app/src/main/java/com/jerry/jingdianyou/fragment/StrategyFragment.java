package com.jerry.jingdianyou.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.SearchActivity;
import com.jerry.jingdianyou.adapter.StrategyListAdapter;
import com.jerry.jingdianyou.entity.Strategy;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */

/**
 * 攻略
 */
public class StrategyFragment extends Fragment
{
  @ViewInject(R.id.fg_stragegy_pulltorefresh)
  private PullToRefreshListView mPullToRefresh;

  //适配器
  private StrategyListAdapter mStrateAdapter;

  //添加参数
  private Map<String, Object> params = new HashMap<String, Object>();

  //添加在适配器中显示的值
  private List<Strategy.Data> mList = new ArrayList<Strategy.Data>();
  private int page_index = 1;

  @ViewInject(R.id.et_search)
  private EditText mSearchKey;
  @ViewInject(R.id.iv_search)
  private ImageView mSearch;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {

    View view = inflater.inflate(R.layout.fg_strategy, container, false);

    ViewUtils.inject(this, view);

    //加载数据
    initLoading();

    //下拉刷新上拉加载
    PullRefresh();

    //初始化适配器对象
    mStrateAdapter = new StrategyListAdapter(view.getContext(), mList);
    //下拉刷新与适配器适配
    mPullToRefresh.setAdapter(mStrateAdapter);
    return view;
  }

  //下拉刷新上拉加载
  private void PullRefresh()
  {
    //上拉下拉同时具备
    mPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
    //对事件进行监听
    mPullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
    {
      @Override
      public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        //下拉刷新,首先清除集合中数据，然后把page_index置为1，重新加载数据
        mList.clear();
        mStrateAdapter.notifyDataSetChanged();
        page_index = 1;
        initLoading();

      }

      @Override
      public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        //上拉加载。page_index++，加载数据
        page_index++;
        initLoading();
      }
    });
  }

  //加载数据
  private void initLoading()
  {

    params.put("RequestJson", "{\"page_index\":" + page_index + "," +
        "\"page_size\":10," +
        "\"member_id\":\"\"," +
        "\"search_key\":\"\"}");

    JDYHttpConnect.getInstance().getStrategy(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        Gson gson = new Gson();
        Strategy strategy = gson.fromJson(response, Strategy.class);
        if (strategy.getData() != null)
        {

          mList.addAll(strategy.getData());
        }
        else
        {
          return;
        }
        mStrateAdapter.notifyDataSetChanged();
        mPullToRefresh.onRefreshComplete();
      }

      @Override
      public void onFailure(String error)
      {
        mPullToRefresh.onRefreshComplete();
      }
    });
  }

  @OnClick(R.id.iv_search)
  public void doSearch(View view)
  {

    String SearchKey = mSearchKey.getText().toString();
    if ("".equals(SearchKey))
    {
      Toast.makeText(getActivity(), "请先输入关键字！", Toast.LENGTH_SHORT).show();
      return;
    }

    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setClass(getActivity(), SearchActivity.class);
    intent.putExtra("key", SearchKey);
    intent.putExtra("type", 1 + "");
    getActivity().startActivity(intent);
  }

}
