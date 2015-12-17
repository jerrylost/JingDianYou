
package com.jerry.jingdianyou.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.StrategyDetailListAdapter;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.db.JingDbHelper;
import com.jerry.jingdianyou.db.JingDbTable;
import com.jerry.jingdianyou.entity.StrategyDetail;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.strategy_detail)
public class StrategyDetailActivity extends BaseActivity implements View.OnTouchListener
{
  //图片
  @ViewInject(R.id.item_picture)
  private ImageView mStrategyImage;
  //标题
  @ViewInject(R.id.guides_title)
  private TextView mStrategyTitle;
  //适配器
  @ViewInject(R.id.lisview)
  private ListView mLisview;
  //滚动条
  @ViewInject(R.id.scrollView)
  private ScrollView mScrollView;

  //头布局
  @ViewInject(R.id.strategy_detail_head_relative)
  private RelativeLayout mRelativeLayout;
  //天数的布局
  @ViewInject(R.id.linearLaoyut)
  private LinearLayout mLinearLayout;

  //适配器端传递过来的参数id
  private String id;
  //添加参数
  private Map<String, Object> params = new HashMap<String, Object>();

  private ImageLoader mImageLoader;
  private DisplayImageOptions mOptions;
  private List<StrategyDetail.Data.GuidesInfoDataEntity.DataEntity> mList = new ArrayList<>();
  private StrategyDetailListAdapter mStrAdapter;
  private String position_x;
  private String position_y;
  private int i;
  private StrategyDetail strategyDetail;
  private List<StrategyDetail.Data.GuidesInfoDataEntity.DataEntity> data;

  //透明度
  private int alph = 0;
  private float a;

  private JingDbHelper jingDbHelper;
  private String pic;
  private String title;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    jingDbHelper = new JingDbHelper(this);

    mImageLoader = JDYApplication.getApp().getmImageLoader();
    mOptions = JDYApplication.getApp().getmOptions();
    mRelativeLayout.setAlpha(0);

    //获取适配器端传递过来的参数id
    Intent intent = getIntent();

    id = intent.getStringExtra("guides_id");

    pic = intent.getStringExtra("pic");
    title = intent.getStringExtra("title");
    mStrategyImage.setScaleType(ImageView.ScaleType.FIT_XY);
    mImageLoader.displayImage(pic, mStrategyImage, mOptions);
    mStrategyTitle.setText(title);

    //适配适配器
    mStrAdapter = new StrategyDetailListAdapter(this, mList, id);
    mLisview.setAdapter(mStrAdapter);
    //加载数据
    initDetailLoading();

    //滚动条设置事件
    mScrollView.setOnTouchListener(this);
  }

  //加载数据
  private void initDetailLoading()
  {

    params.put("RequestJson", "{\"guides_id\":\"" + id + "\",\"member_id\":\"\"}");
    JDYHttpConnect.getInstance().getStrategyDetail(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        Gson gson = new Gson();
        strategyDetail = gson.fromJson(response, StrategyDetail.class);
        mStrategyImage.setScaleType(ImageView.ScaleType.FIT_XY);
        mList.clear();
        //动态添加天数
        int size = strategyDetail.getMydata().getGuides_info_data().size();
        if (size > 1)
        {
          for (int i = 1; i <= size; i++)
          {
            TextView mTextView = new TextView(getApplicationContext());
            mTextView.setTextSize(25);
            mTextView.setPadding(20, 20, 20, 20);
            mTextView.setText("第" + i + "天");

            mLinearLayout.addView(mTextView);
          }
        }
        if (size != 0)
        {
          for (int i = 0; i < size; i++)
          {
            data =
                strategyDetail.getMydata().getGuides_info_data().get(i).getData();
            int size1 = strategyDetail.getMydata().getGuides_info_data().get(i).getData().size();

            for (int a = 0; a < size1; a++)
            {
              //经度
              position_x = data.get(a).getPosition_x();

              //纬度
              position_y = data.get(a).getPosition_y();
            }
            mList.addAll(data);

          }
        }

        mStrAdapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(String error)
      {

      }
    });
  }

  //定位
  public void onPosition(View view)
  {
    Intent intent = new Intent(this, BaiduMapActivity.class);
    intent.putExtra("position_x", position_x);
    intent.putExtra("position_y", position_y);
    startActivity(intent);
  }

  //点击收藏
  public void onCollect(View view)
  {
    //判断是否已经收藏
    boolean simpleData = jingDbHelper.isSimpleData(
        JingDbTable.JingControll.COLUMN_ID + "=?",
        new String[]{id});

    if (simpleData)
    {
      Toast.makeText(StrategyDetailActivity.this, "数据已经存在，不用再收藏了", Toast.LENGTH_SHORT).show();
    }
    else
    {
      //如果不存在，则插入
      ContentValues values = new ContentValues();
      //插入标题
      values.put(JingDbTable.JingControll.COLUMN_TYPE, JingDbTable.JingControll.TYPE_GL);
      values.put(JingDbTable.JingControll.COLUMN_ID, id);
      values.put(JingDbTable.JingControll.COLUMN_TITLE, title);
      values.put(JingDbTable.JingControll.COLUMN_PIC, pic);
      boolean saveData = new JingDbHelper(this).SaveData(values);
      if (saveData)
      {
        Toast.makeText(this, "收藏成功！！！", Toast.LENGTH_SHORT).show();
      }
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

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    mList.clear();
  }

  @Override
  public boolean onTouch(View v, MotionEvent event)
  {
    float y = 0;
    float v1 = 0;
    switch (event.getAction())
    {

      case MotionEvent.ACTION_UP:
        float y1 = event.getY();
        a = ((1000 - y1) / 800);
        if (a > 0 && a < 1)
        {
          mRelativeLayout.setAlpha(a);
        }
        if (a >= 1)
        {
          mRelativeLayout.setAlpha(1);
        }
        mRelativeLayout.invalidate();

        break;
      case MotionEvent.ACTION_MOVE:
        y1 = event.getY();
        a = ((1000 - y1) / 800);
        if (a > 0 && a < 1)
        {
          mRelativeLayout.setAlpha(a);
        }
        if (a >= 1)
        {
          mRelativeLayout.setAlpha(1);
        }
        mRelativeLayout.invalidate();

        break;
    }

    return false;
  }


}
