package com.jerry.jingdianyou.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.OnWayAdapter;
import com.jerry.jingdianyou.entity.OnWay;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 * 在路上
 */
@ContentView(R.layout.activity_onway)
public class OnWayActivity extends BaseActivity
{
  @ViewInject(R.id.lv_onway)
  private PullToRefreshListView mLvOnway;

  private List<OnWay.Data> mList = new LinkedList<>();
  private OnWayAdapter mAdapter;

  private Map<String, Object> params = new HashMap<>();

  private String current_onway_id = "";
  private int index = 272;
  private int widthPixels;

  private ProgressDialog mProgressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    initView();
    loadData();
  }

  /**
   * 加载数据
   */
  private void loadData()
  {
    params.put("RequestJson",
        "{\"page_size\":\"10\",\"query_type\":\"0\"," +
        "\"current_onway_id\":\"" + current_onway_id
        + "\",\"current_member_id\":\"\"," + "\"member_id\":\"\"}");

    JDYHttpConnect.getInstance().getOnway(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        mLvOnway.onRefreshComplete();
        Gson gson = new Gson();
        OnWay onWay = gson.fromJson(response, OnWay.class);
        if (onWay != null)
        {
          List<OnWay.Data> data = onWay.getData();
          if (data.size() > 0)
          {
            mList.addAll(data);
            mAdapter.notifyDataSetChanged();
          }
        }

        if (mProgressDialog != null && mProgressDialog.isShowing())
        {
          mProgressDialog.dismiss();
        }
      }

      @Override
      public void onFailure(String error)
      {
        mLvOnway.onRefreshComplete();

        if (mProgressDialog != null && mProgressDialog.isShowing())
        {
          mProgressDialog.dismiss();
        }
      }
    });
  }

  /**
   * 初始化视图
   */
  private void initView()
  {
    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage("正在加载中....");
    mProgressDialog.setCanceledOnTouchOutside(false);
    mProgressDialog.show();

    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    widthPixels = metrics.widthPixels;

    mAdapter = new OnWayAdapter(this, mList, widthPixels);
    mLvOnway.setAdapter(mAdapter);

    mLvOnway.setMode(PullToRefreshBase.Mode.BOTH);
    mLvOnway.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
    {
      @Override
      public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        current_onway_id = "";
        loadData();
      }

      @Override
      public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        current_onway_id = "OI000000000000000" + index;
        loadData();
        index = index - 10;
      }
    });

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

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    mList.clear();
  }
}
