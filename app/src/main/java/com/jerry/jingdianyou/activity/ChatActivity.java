package com.jerry.jingdianyou.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.ChatAdapter;
import com.jerry.jingdianyou.entity.Chat;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_chat)
public class ChatActivity extends BaseActivity implements View.OnClickListener
{
  @ViewInject(R.id.lv_chat)
  private ListView mLvChat;

  private List<Chat.Data> mList = new ArrayList<>();
  private Map<String, Object> params = new HashMap<>();
  private ChatAdapter mAdapter;
  private PopupWindow popupWindow;

  private String position_y = "40.055538";
  private String position_x = "116.302902";
  private String sex_type = "1";
  private String page_size = "100";
  private String range = "100000";
  private String member_id = "";

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    initView();

    //SharePreUtils utils = SharePreUtils.getInstance(App.SP_NAME);
    //position_x = utils.getString("latitude", position_x);
    //position_y = utils.getString("longitude",position_y);

    loadData();
  }

  private void loadData()
  {
    params.put("RequestJson", "{" +
        "\"position_y\":\"" + position_y + "\"," +
        "\"position_x\":\"" + position_x + "\"," +
        "\"page_size\":\"" + page_size + "\"," +
        "\"sex_type\":\"" + sex_type + "\"," +
        "\"range\":\"" + range + "\"," +
        "\"member_id\":\"" + member_id + "\"}");

    JDYHttpConnect.getInstance().getChat(params, new DataCallBack()
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

  private void initView()
  {
    mAdapter = new ChatAdapter(this, mList);
    mLvChat.setAdapter(mAdapter);
  }

  @ViewInject(R.id.tv_select_all)
  private TextView mTvAll;
  @ViewInject(R.id.tv_select_nan)
  private TextView mTvNan;
  @ViewInject(R.id.tv_select_nv)
  private TextView mTvNv;

  public void selectChatSex(View view)
  {

    popupWindow = new PopupWindow();

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

    popupWindow.setTouchable(true);
    ColorDrawable cd = new ColorDrawable(Color.TRANSPARENT);
    popupWindow.setBackgroundDrawable(cd);

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
    sex_type = type;
    mList.clear();
    mAdapter.notifyDataSetChanged();
    loadData();
    popupWindow.dismiss();
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    mList.clear();
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
