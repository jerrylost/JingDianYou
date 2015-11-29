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
import com.jerry.jingdianyou.adapter.TuCaoDetailAdapter;
import com.jerry.jingdianyou.entity.CommentList;
import com.jerry.jingdianyou.entity.TuCao;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_tucao_detail)
public class TuCaoDetailActivity extends Activity
{
  @ViewInject(R.id.lv_tocao_detail)
  private ListView mListView;
  private TuCaoDetailAdapter mAdapter;
  private List<TuCao.Data> mList = new ArrayList<>();
  private int width = 0;
  private String current_comment_id = "";
  private String comment_type = "3";
  private String praise_amount;
  private List<CommentList.Data> commentListData = new ArrayList<>();
  private Map<String, Object> commentParams = new HashMap<>();
  private Map<String, Object> params = new HashMap<>();
  private Gson gson = new Gson();
  private TuCao.Data data;
  private String message_id;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    initView();

    Intent intent = getIntent();
    Serializable tucao = intent.getSerializableExtra("tucao");
    data = (TuCao.Data) tucao;
    if (data != null)
    {
      setData();
    }

  }

  @ViewInject(R.id.rb_rendezvous_zan)
  private RadioButton mRbZan;

  private void setData()
  {
    mList.add(data);
    praise_amount = data.getPraise_amount();
    mRbZan.setText("赞(" + praise_amount + ")");
    message_id = data.getMessage_id();
    mAdapter.notifyDataSetChanged();
    getCommentList();
  }

  /**
   * 加载评价列表
   */
  private void getCommentList()
  {
    commentParams.put("RequestJson", "{" +
        "\"page_size\":\"10\"," +
        "\"current_comment_id\":\"" + current_comment_id + "\"," +
        "\"object_id\":\"" + message_id + "\"," +
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
    width = metrics.widthPixels;
    mAdapter = new TuCaoDetailAdapter(this, commentListData, mList, width);
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
