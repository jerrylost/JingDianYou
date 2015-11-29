package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.ScenicDetailActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.ScenicFragItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class ScenicContainerAdapter extends BaseAdapter
{
  //定义集合用于存放景区的数据
  private List<ScenicFragItem.Data> mScenics = new ArrayList<>();
  //定义一个imageloader，后面加载图片
  private ImageLoader mImageloader;
  //定义一个上下文
  private Context mContext;
  private LayoutInflater mLayoutInflater;


  public ScenicContainerAdapter(Context mContext, List<ScenicFragItem.Data> mScenics)
  {
    this.mScenics = mScenics;
    mImageloader = JDYApplication.getApp().getmImageLoader();
    this.mContext = mContext;
    this.mLayoutInflater = LayoutInflater.from(mContext);
  }

  @Override
  public int getCount()
  {
    return mScenics.size();
  }

  @Override
  public ScenicFragItem.Data getItem(int position)
  {
    return mScenics.get(position);
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
      convertView = mLayoutInflater.inflate(R.layout.list_scenic_content_item, null);
      holder = new ViewHolder();
      ViewUtils.inject(holder, convertView);
      convertView.setTag(holder);
    }
    else
    {
      holder = (ViewHolder) convertView.getTag();
    }
    ScenicFragItem.Data scenic = getItem(position);

    //景区名称
    holder.tv_scenic_name.setText(scenic.getScenic_name());
    //返券
    if ("0".equals(scenic.getGive_integral()))
    {
      holder.tv_give_integral.setVisibility(View.INVISIBLE);
    }
    else
    {
      holder.tv_give_integral.setText("返券¥" + scenic.getGive_integral());
    }
    //所在城市
    holder.tv_city_name.setText(scenic.getCity_name());
    //售价
    holder.tv_sales_price.setText(scenic.getSales_price());
    //市场价
    holder.tv_market_price.setText("¥" + scenic.getMarket_price());
//        //获取景区编码
//        scenic_spot_code = scenic.getScenic_spot_code();

    //先设置景区等级以及景区图片的默认图片

    holder.img_scenic_level1.setImageResource(R.mipmap.scores_b);
    holder.img_scenic_level2.setImageResource(R.mipmap.scores_b);
    holder.img_scenic_level3.setImageResource(R.mipmap.scores_b);
    holder.img_scenic_level4.setImageResource(R.mipmap.scores_b);
    holder.img_scenic_level5.setImageResource(R.mipmap.scores_b);
    //获取景区的等级
    int level = Integer.parseInt(scenic.getLevel());
    //根据等级数对相应图片进行设置
    switch (level)
    {
      case 0:
        break;
      case 1:
        holder.img_scenic_level1.setImageResource(R.mipmap.scores_a);
        break;
      case 2:
        holder.img_scenic_level1.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level2.setImageResource(R.mipmap.scores_a);
        break;
      case 3:
        holder.img_scenic_level1.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level2.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level3.setImageResource(R.mipmap.scores_a);
        break;
      case 4:
        holder.img_scenic_level1.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level2.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level3.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level4.setImageResource(R.mipmap.scores_a);
        break;
      case 5:
        holder.img_scenic_level1.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level2.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level3.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level4.setImageResource(R.mipmap.scores_a);
        holder.img_scenic_level5.setImageResource(R.mipmap.scores_a);
        break;
    }

    if (scenic.getProduct_pic() == null)
    {
      holder.img_scenic.setImageResource(R.mipmap.empty_photo);
    }
    mImageloader.displayImage(scenic.getProduct_pic(), holder.img_scenic,
        JDYApplication.getApp().getmOptions());

    //设置点击跳转到景区详情的页面的事件
    convertView.setOnClickListener(new ShowScenicDetail(scenic.getScenic_spot_code()));

    return convertView;
  }

  private class ShowScenicDetail implements View.OnClickListener
  {

    //此变量是用来获取景区详细信息的参数
    private String scenic_spot_code;
    private String member_id;

    public ShowScenicDetail(String scenic_spot_code)
    {
      this.scenic_spot_code = scenic_spot_code;
      //  this.member_id = member_id;

    }

    @Override
    public void onClick(View v)
    {
      Intent intent = new Intent();
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.setClass(mContext, ScenicDetailActivity.class);
      intent.putExtra("scenic_spot_code", scenic_spot_code);
      mContext.startActivity(intent);
    }
  }

  protected class ViewHolder
  {
    @ViewInject(R.id.img_scenic)
    private ImageView img_scenic;

    @ViewInject(R.id.tv_scenic_name)
    private TextView tv_scenic_name;

    @ViewInject(R.id.tv_give_integral)
    private TextView tv_give_integral;

    @ViewInject(R.id.tv_city_name)
    private TextView tv_city_name;

    @ViewInject(R.id.tv_sales_price)
    private TextView tv_sales_price;

    @ViewInject(R.id.tv_market_price)
    private TextView tv_market_price;

    @ViewInject(R.id.img_scenic_level1)
    private ImageView img_scenic_level1;

    @ViewInject(R.id.img_scenic_level2)
    private ImageView img_scenic_level2;

    @ViewInject(R.id.img_scenic_level3)
    private ImageView img_scenic_level3;

    @ViewInject(R.id.img_scenic_level4)
    private ImageView img_scenic_level4;

    @ViewInject(R.id.img_scenic_level5)
    private ImageView img_scenic_level5;

  }
}
