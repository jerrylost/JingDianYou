package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.SignUpAdapter;
import com.jerry.jingdianyou.entity.RendezvousDtlList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_sign_up)
public class SignUpListActivity extends Activity
{
  @ViewInject(R.id.lv_sign_up)
  private ListView mListView;
  private SignUpAdapter mAdapter;
  private List<RendezvousDtlList.Data> data = new ArrayList<>();
  private List<RendezvousDtlList.Data> listData;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    initView();
    Intent intent = getIntent();
    Bundle bundle = intent.getBundleExtra("bundle");
    Serializable sign = bundle.getSerializable("sign");
    listData = (List<RendezvousDtlList.Data>) sign;
    updateView();
  }

  private void updateView()
  {
    if (listData.size() != 0 && listData != null)
    {
      data.addAll(listData);
      mAdapter.notifyDataSetChanged();
    }
    else
    {
      return;
    }
  }

  private void initView()
  {
    mAdapter = new SignUpAdapter(this, data);
    mListView.setAdapter(mAdapter);
  }

  public void SignUpBack(View view)
  {
    this.finish();
    data.clear();
    listData.clear();
  }
}
