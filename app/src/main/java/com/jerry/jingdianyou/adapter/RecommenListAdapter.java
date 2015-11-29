package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.ScenicDetailActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.RecommendList;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class RecommenListAdapter extends BaseAdapter
{
  private List<RecommendList.Data> mRecoms = null;
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private ImageLoader mImageloader;
  private String scenic_spot_code;
  RecommendList.Data mRecomData = null;

  public RecommenListAdapter(Context mContext, List<RecommendList.Data> mRecoms)
  {
    this.mRecoms = mRecoms;
    this.mContext = mContext;
    mImageloader = JDYApplication.getApp().getmImageLoader();
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  @Override
  public int getCount()
  {
    int count = 0;
    if (mRecoms.size() != 0)
    {
      count = mRecoms.size();
    }
    return count;
  }

  @Override
  public RecommendList.Data getItem(int position)
  {
    return mRecoms.get(position);
  }

  @Override
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    ViewHolder holder = null;
    if (convertView == null)
    {
      convertView = mLayoutInflater.inflate(R.layout.list_recommend_item, null);
      holder = new ViewHolder();
      ViewUtils.inject(holder, convertView);
      convertView.setTag(holder);
    }
    else
    {
      holder = (ViewHolder) convertView.getTag();
    }
    if (mRecoms.get(position) != null)
    {
      mRecomData = mRecoms.get(position);
      scenic_spot_code = mRecomData.getScenic_spot_code();
      holder.mRecomSceName.setText(mRecomData.getScenic_name());
      holder.mRecomSceDistance.setText("距离仅" + mRecomData.getDistance() + "m");
      holder.mRecomScePrice.setText(mRecomData.getSales_price());
      holder.mRecomMarketPrice.setVisibility(View.VISIBLE);
      holder.mRecomMarketPrice.setText("¥" + mRecomData.getMarket_price());
      if (!("".equals(mRecomData.getProduct_pic())))
      {
        mImageloader.displayImage(mRecomData.getProduct_pic(), holder.mRecomScePic,
            JDYApplication.getApp().getmOptions());
      }
    }
    holder.mLlRecomItem.setOnClickListener(new ToRecomDetail());
    return convertView;
  }

  public class ViewHolder
  {

    @ViewInject(R.id.tv_recommend_scenic_name)
    private TextView mRecomSceName;

    @ViewInject(R.id.tv_recommend_scenic_distance)
    private TextView mRecomSceDistance;

    @ViewInject(R.id.tv_recommend_scenic_price)
    private TextView mRecomScePrice;

    @ViewInject(R.id.tv_recommend_market_price)
    private TextView mRecomMarketPrice;

    @ViewInject(R.id.img_recommend_scenic_pic)
    private ImageView mRecomScePic;

    @ViewInject(R.id.ll_recom_list_item)
    private LinearLayout mLlRecomItem;
  }

  //点击条目进入详情
  public class ToRecomDetail implements View.OnClickListener
  {

    @Override
    public void onClick(View v)
    {
      Intent intent = new Intent();
      intent.putExtra("scenic_spot_code", scenic_spot_code);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.setClass(mContext, ScenicDetailActivity.class);
      mContext.startActivity(intent);
    }
  }

}
