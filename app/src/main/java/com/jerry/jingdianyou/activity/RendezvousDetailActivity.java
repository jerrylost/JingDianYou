package com.jerry.jingdianyou.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.RendezvousDetailAdapter;
import com.jerry.jingdianyou.entity.CommentList;
import com.jerry.jingdianyou.entity.RendezvousDtlList;
import com.jerry.jingdianyou.entity.RendezvousInfo;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_rendezvous_detail)
public class RendezvousDetailActivity extends Activity
{

  private Map<String, Object> params = new HashMap<>();
  private Map<String, Object> rendeParams = new HashMap<>();
  private Map<String, Object> commentParams = new HashMap<>();

  @ViewInject(R.id.lv_rendezvous_detail)
  private PullToRefreshListView mLvDetail;

  @ViewInject(R.id.rb_rendezvous_zan)
  private RadioButton mRbZan;
  private RendezvousDetailAdapter mAdapter;

  private Gson gson = new Gson();
  private List<RendezvousDtlList.Data> rendezvousDtlListData = new ArrayList<>();
  private List<CommentList.Data> commentListData = new ArrayList<>();

  private List<RendezvousInfo.DataEntity> mList = new ArrayList<>();

  private String rendezvous_id = "";
  private String member_id = "";
  private int widthPixels = 0;
  private String page_index = "";
  private String page_size = "100";
  private String current_comment_id = "";
  private String comment_type = "1";
  private String praise_amount;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);

    widthPixels = metrics.widthPixels;

    Intent intent = getIntent();
    rendezvous_id = intent.getStringExtra("rendezvous_id");
    member_id = intent.getStringExtra("member_id");
    initView();
    if (rendezvous_id != null && member_id != null)
    {
      loadData();
      getRendezvousDlList();
      getCommentList();
    }
  }

  private void initView()
  {
    mAdapter = new RendezvousDetailAdapter(widthPixels, mList,
        this, commentListData, rendezvousDtlListData);
    mLvDetail.setAdapter(mAdapter);
  }

  /**
   * 加载 作者信息
   */
  private void loadData()
  {
    params.put("RequestJson", "{" +
        "\"rendezvous_id\":\"" + rendezvous_id + "\"," +
        "\"member_id\":\"" + member_id + "\"}");
    JDYHttpConnect.getInstance().getRendeDetail(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        RendezvousInfo rendezvousInfo = gson.fromJson(response, RendezvousInfo.class);
        RendezvousInfo.DataEntity rendezvousInfoData = rendezvousInfo.getData();
        if (rendezvousInfoData == null)
        {
          return;
        }
        mList.add(rendezvousInfoData);
        praise_amount = rendezvousInfoData.getPraise_amount();
        mRbZan.setText("赞(" + praise_amount + ")");

        mAdapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(String error)
      {

      }
    });
  }

  /**
   * 加载 报名列表
   */

  private void getRendezvousDlList()
  {
    rendeParams.put("RequestJson", "{" +
        "\"page_index\":\"" + page_index + "\"," +
        "\"rendezvous_id\":\"" + rendezvous_id + "\"," +
        "\"page_size\":\"" + page_size + "\"}");
    JDYHttpConnect.getInstance().getRendezvousDtlList(rendeParams, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        RendezvousDtlList rendezvousDtlList = gson.fromJson(response, RendezvousDtlList.class);
        List<RendezvousDtlList.Data> data = rendezvousDtlList.getData();
        if (data.size() > 0 && data != null)
        {
          rendezvousDtlListData.addAll(data);
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
    }
  }
}
