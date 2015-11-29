package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.ScenicContainerAdapter;
import com.jerry.jingdianyou.adapter.StrategyListAdapter;
import com.jerry.jingdianyou.entity.ScenicFragItem;
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
@ContentView(R.layout.activity_search)
public class SearchActivity extends Activity
{
  @ViewInject(R.id.et_search)
  private EditText mSearch;
  private ScenicContainerAdapter mAdapter;
  @ViewInject(R.id.lv_search)
  private PullToRefreshListView mScenicList;
  private List<ScenicFragItem.Data> mScenics = new ArrayList<>();

  private int mPageIndex = 1;
  private String searchKey;
  private String type;

  //适配器
  private StrategyListAdapter mStrateAdapter;
  //添加参数
  private Map<String, Object> params = new HashMap<>();
  //添加在适配器中显示的值
  private List<Strategy.Data> mList = new ArrayList<Strategy.Data>();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    Intent intent = getIntent();
    searchKey = intent.getStringExtra("key");
    type = intent.getStringExtra("type");
    initView();
    if ("0".equals(type))
    {
      loadData();
    }
    else if ("1".equals(type))
    {
      loadStrategyData();
    }

  }

  //加载数据
  private void loadStrategyData()
  {
    params.clear();
    params.put("RequestJson", "{\"page_index\":" + mPageIndex + "," +
        "\"page_size\":10," +
        "\"member_id\":\"\"," +
        "\"search_key\":\"" + searchKey + "\"}");

    JDYHttpConnect.getInstance().getStrategy(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        mScenicList.onRefreshComplete();
        Gson gson = new Gson();
        Strategy strategy = gson.fromJson(response, Strategy.class);
        if (strategy.getData().size() > 0)
        {
          mList.addAll(strategy.getData());
          mStrateAdapter.notifyDataSetChanged();
        }
        else
        {
          Toast.makeText(SearchActivity.this, "没有查询到相关景区的信息", Toast.LENGTH_SHORT).show();
          return;
        }
      }

      @Override
      public void onFailure(String error)
      {
        mScenicList.onRefreshComplete();
      }
    });
  }

  private void initView()
  {

    if (type == null)
    {
      return;
    }

    if ("0".equals(type))
    {
      mAdapter = new ScenicContainerAdapter(this, mScenics);
      mScenicList.setAdapter(mAdapter);
    }
    else if ("1".equals(type))
    {
      //初始化适配器对象
      mStrateAdapter = new StrategyListAdapter(this, mList);
      //下拉刷新与适配器适配
      mScenicList.setAdapter(mStrateAdapter);
    }

    mScenicList.setMode(PullToRefreshBase.Mode.BOTH);
    mScenicList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
    {
      @Override
      public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        mPageIndex = 1;
        if ("0".equals(type))
        {
          mScenics.clear();
          loadData();
          mAdapter.notifyDataSetChanged();
        }
        else if ("1".equals(type))
        {
          mList.clear();
          loadStrategyData();
          mStrateAdapter.notifyDataSetChanged();
        }
      }

      @Override
      public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        mPageIndex++;
        if ("0".equals(type))
        {
          loadData();
          mAdapter.notifyDataSetChanged();
        }
        else if ("1".equals(type))
        {
          loadStrategyData();
          mStrateAdapter.notifyDataSetChanged();
        }
      }
    });
  }

  private void loadData()
  {
    params.clear();
    params.put("RequestJson", "{\"pageSize\":\"10\"," +
        "\"searchKey\":\"" + searchKey + "\"," +
        "\"pageIndex\":\"" + mPageIndex + "\"}");
    JDYHttpConnect.getInstance().getSearch(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        mScenicList.onRefreshComplete();
        //返回景区列表信息
        Gson gson = new Gson();
        ScenicFragItem scenic = gson.fromJson(response, ScenicFragItem.class);
        int size = scenic.getData().size();

        if (size > 0)
        {
          mScenics.addAll(scenic.getData());
          mAdapter.notifyDataSetChanged();
        }
        else
        {
          Toast.makeText(SearchActivity.this, "没有查询到相关景区的信息", Toast.LENGTH_SHORT).show();
          return;
        }
      }

      @Override
      public void onFailure(String error)
      {
        mScenicList.onRefreshComplete();
      }
    });
  }

  public void doSearch(View view)
  {
    String key = mSearch.getText().toString();
    if ("".equals(key))
    {
      Toast.makeText(this, "请先输入关键字！", Toast.LENGTH_SHORT).show();
      return;
    }

    mPageIndex = 1;
    searchKey = key;

    if ("0".equals(type))
    {
      mScenics.clear();
      loadData();
    }
    else if ("1".equals(type))
    {
      mList.clear();
      loadStrategyData();
    }
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
