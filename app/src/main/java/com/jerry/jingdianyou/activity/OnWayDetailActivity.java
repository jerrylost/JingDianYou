package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.OnWayDetailAdapter;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.CommentList;
import com.jerry.jingdianyou.entity.OnWay;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_onway_detail)
public class OnWayDetailActivity extends Activity
{
  @ViewInject(R.id.lv_onway_detail)
  private ListView mListView;
  private List<OnWay.Data> mDataList = new ArrayList<>();
  private OnWayDetailAdapter mAdapter;
  // 从约游里面的在路上传过来的
  private OnWay.Data fromOnway;
  @ViewInject(R.id.rb_rendezvous_zan)
  private RadioButton mRbZan;

  private String rendezvous_id = "";

  private int widthPixels = 0;

  private String current_comment_id = "";
  private String comment_type = "2";
  private String praise_amount;
  private List<CommentList.Data> commentListData = new ArrayList<>();
  private Map<String, Object> commentParams = new HashMap<>();
  private Map<String, Object> params = new HashMap<>();
  private Gson gson = new Gson();

  // 从主页传过来的
  private String onway_id = null;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    initView();
    Intent intent = getIntent();
    onway_id = intent.getStringExtra("homeonway");
    OnWay.Data detail = (OnWay.Data) intent.getSerializableExtra("onway");
    if (onway_id != null)
    {
      loadData();
    }
    else
    {
      setBaseData(detail);
    }


  }

  private void loadData()
  {
    params.put("RequestJson", "{\"onway_id\":\"" + onway_id + "\"}");
    JDYHttpConnect.getInstance().getOnWayDetail(
        params, new DataCallBack()
        {
          @Override
          public void onSuccess(String response)
          {
            JSONObject object = null;
            String data = "";
            try
            {
              object = new JSONObject(response);
              data = object.getString("data");
              OnWay.Data data2 = gson.fromJson(data, OnWay.Data.class);
              setBaseData(data2);
            }
            catch (JSONException e)
            {
              e.printStackTrace();
            }
          }

          @Override
          public void onFailure(String error)
          {

          }
        }
    );
  }

  private void setBaseData(OnWay.Data detail)
  {
    if (detail != null)
    {
      mDataList.add(detail);
      praise_amount = detail.getPraise_amount();
      mRbZan.setText("赞(" + praise_amount + ")");
      rendezvous_id = detail.getOnway_id();
      mAdapter.notifyDataSetChanged();
      getCommentList();
    }
    else
    {
      this.finish();
    }
  }

  /**
   * 加载评价列表
   */
  private void getCommentList()
  {
    commentParams.put("RequestJson", "{" +
        "\"page_size\":\"10\"," +
        "\"current_comment_id\":\"" + current_comment_id + "\"," +
        "\"object_id\":\"" + rendezvous_id + "\"," +
        "\"comment_type\":\"" + comment_type + "\"}");
    JDYHttpConnect.getInstance().getCommentList(commentParams, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        CommentList commentList = gson.fromJson(response, CommentList.class);
        List<CommentList.Data> data = commentList.getData();
        if (data.size() > 0 && data != null)
        {
          commentListData.addAll(data);
          mAdapter.notifyDataSetChanged();
        }
        else
        {
          return;
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
    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    widthPixels = metrics.widthPixels;
    mAdapter = new OnWayDetailAdapter(this, mDataList, commentListData, widthPixels);
    mListView.setAdapter(mAdapter);
  }

  private boolean isFirst = true;
  private int zanCount = 0;

  @OnClick({R.id.rb_rendezvous_zan, R.id.rb_rendezvous_comment, R.id.rb_rendezvous_sixin})
  public void selectButton(View view)
  {
    Drawable drawable = null;
    switch (view.getId())
    {
      case R.id.rb_rendezvous_sixin:
        break;
      case R.id.rb_rendezvous_comment:
        break;
      case R.id.rb_rendezvous_zan:
        if (isFirst)
        {
          drawable = getResources().getDrawable(R.mipmap.com_tab_zan_click);
          drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
          mRbZan.setCompoundDrawables(null, drawable, null, null);
          zanCount = Integer.parseInt(praise_amount) + 1;
          mRbZan.setText("赞(" + zanCount + ")");
          isFirst = false;
        }
        else
        {
          drawable = getResources().getDrawable(R.mipmap.com_tab_zan);
          drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
          mRbZan.setCompoundDrawables(null, drawable, null, null);
          zanCount = zanCount - 1;
          mRbZan.setText("赞(" + zanCount + ")");
          isFirst = true;
        }
        break;
      default:
        break;
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
