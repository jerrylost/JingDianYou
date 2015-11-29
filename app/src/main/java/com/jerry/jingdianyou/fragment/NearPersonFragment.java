package com.jerry.jingdianyou.fragment;

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
import com.jerry.jingdianyou.adapter.ChatAdapter;
import com.jerry.jingdianyou.adapter.TuCaoAdapter;
import com.jerry.jingdianyou.entity.Chat;
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
public class NearPersonFragment extends Fragment
{
  @ViewInject(R.id.lv_nearby)
  private ListView mLvScene;
  private List<Chat.Data> mList = new LinkedList<>();
  private ChatAdapter mAdapter;
  private Map<String, Object> params = new HashMap<>();
  private static NearPersonFragment fragment = new NearPersonFragment();

  public static NearPersonFragment getInstance()
  {
    return fragment;
  }

  public NearPersonFragment()
  {
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fg_nearby_scene, container, false);
    ViewUtils.inject(this, view);

    initView();
    loadData();
    return view;
  }

  private String position_y = "40.055538";
  private String position_x = "116.302902";
  private String sex_type = "1";
  private String page_size = "100";
  private String range = "100000";
  private String member_id = "";

  /**
   * 加载数据
   */
  private void loadData()
  {
    params.put("RequestJson", "{" +
        "\"position_y\":\"" + position_y + "\"," +
        "\"sex_type\":\"" + sex_type + "\"," +
        "\"position_x\":\"" + position_x + "\"," +
        "\"page_size\":\"" + page_size + "\"," +
        "\"range\":\"" + range + "\"," +
        "\"member_id\":\"" + member_id + "\"}");
    JDYHttpConnect.getInstance().getNearByPerson(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {

        Gson gson = new Gson();
        Chat chat = gson.fromJson(response, Chat.class);
        if (chat != null)
        {
          List<Chat.Data> data = chat.getData();
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

      }
    });
  }

  /**
   * 初始化视图
   */
  private void initView()
  {
    mAdapter = new ChatAdapter(getActivity(), mList);
    mLvScene.setAdapter(mAdapter);
  }

  /**
   * 更新数据
   *
   * @param type
   */
  public void updateData(String type)
  {
    sex_type = type;
    mList.clear();

    loadData();
  }
}

