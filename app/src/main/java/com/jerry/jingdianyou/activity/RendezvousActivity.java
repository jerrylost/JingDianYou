package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.RendezvousAdapter;
import com.jerry.jingdianyou.entity.Rendezvous;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_rendezvous)
public class RendezvousActivity extends BaseActivity implements View.OnClickListener
{

  @ViewInject(R.id.lv_rendezvous)
  private PullToRefreshListView mLvRende;
  private List<Rendezvous.Data> mList = new LinkedList<>();
  private RendezvousAdapter mAdapter;

  private Map<String, Object> params = new HashMap<>();
  private String current_rendezvous_id = "";
  private String sex_type = "1";
  private String member_id = "";
  private int index = 161;
  private PopupWindow popupWindow;

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
    params.put("RequestJson", "{" +
        "\"current_rendezvous_id\":\"" + current_rendezvous_id + "\"," +
        "\"page_size\":10" + "," +
        "\"sex_type\":\"" + sex_type + "\"," +
        "\"member_id\":\"" + member_id + "\"}");
    JDYHttpConnect.getInstance().getRendezous(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        mLvRende.onRefreshComplete();
        Gson gson = new Gson();
        Rendezvous rendezvous = gson.fromJson(response, Rendezvous.class);
        if (rendezvous != null)
        {
          List<Rendezvous.Data> data = rendezvous.getData();
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
        mLvRende.onRefreshComplete();
      }
    });
  }

  /**
   * 初始化视图
   */
  private void initView()
  {

    mAdapter = new RendezvousAdapter(this, mList);
    mLvRende.setAdapter(mAdapter);

    mLvRende.setMode(PullToRefreshBase.Mode.BOTH);
    mLvRende.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
    {
      @Override
      public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
      {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        current_rendezvous_id = "";
        loadData();
      }

      @Override
      public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
      {

        current_rendezvous_id = "RI000000000000000" + index;
        loadData();
        index = index - 10;
      }
    });
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();

    mList.clear();
  }

  @ViewInject(R.id.tv_select_all)
  private TextView mTvAll;
  @ViewInject(R.id.tv_select_nan)
  private TextView mTvNan;
  @ViewInject(R.id.tv_select_nv)
  private TextView mTvNv;

  public void selectSex(View view)
  {

    View v = LayoutInflater.from(this).inflate(R.layout.select_sex_type, null);

    mTvAll = (TextView) v.findViewById(R.id.tv_select_all);
    mTvNan = (TextView) v.findViewById(R.id.tv_select_nan);
    mTvNv = (TextView) v.findViewById(R.id.tv_select_nv);

    mTvNan.setOnClickListener(this);
    mTvAll.setOnClickListener(this);
    mTvNv.setOnClickListener(this);

    popupWindow = new PopupWindow(
        v, ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT, true
    );
    //popupWindow.setOutsideTouchable(true);
    popupWindow.setTouchable(true);
    ColorDrawable cd = new ColorDrawable(Color.TRANSPARENT);
    popupWindow.setBackgroundDrawable(cd);
    //popupWindow.setAnimationStyle(R.style.PopWindow);
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
    current_rendezvous_id = "";
    sex_type = type;
    mList.clear();
    mAdapter.notifyDataSetChanged();
    loadData();
    popupWindow.dismiss();
  }

  // 返回
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
