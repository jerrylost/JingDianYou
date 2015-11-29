package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.HotelList;
import com.jerry.jingdianyou.view.RoundImageView;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class HotelItemAdapter extends BaseAdapter
{

  private List<HotelList.Data> mHotels = null;
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private ImageLoader mImageloader;
  HotelList.Data mHotelData = null;

  public HotelItemAdapter(Context mContext, List<HotelList.Data> mHotels)
  {
    this.mHotels = mHotels;
    this.mContext = mContext;
    mImageloader = JDYApplication.getApp().getmImageLoader();
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  @Override
  public int getCount()
  {
    int count = 0;
    if (mHotels.size() != 0)
    {
      count = mHotels.size();
    }
    return count;
  }

  @Override
  public HotelList.Data getItem(int position)
  {
    return mHotels.get(position);
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
      convertView = mLayoutInflater.inflate(R.layout.list_hotel_item, null);
      holder = new ViewHolder();
      ViewUtils.inject(holder, convertView);
      convertView.setTag(holder);
    }
    else
    {
      holder = (ViewHolder) convertView.getTag();
    }
    if (mHotels.get(position) != null)
    {
      mHotelData = mHotels.get(position);
      holder.tv_scenic_hotel_cname.setText(mHotelData.getHotel_cname());
      holder.tv_scenic_hotel_distance.setText(mHotelData.getDistance() + "m");
      holder.tv_scenic_hotel_price.setText("¥" + mHotelData.getPrice());
      holder.tv_scenic_hotel_star_level.setText(mHotelData.getStar_level() + "星级");
      if (!("".equals(mHotelData.getHotel_picture())))
      {
        mImageloader.displayImage(mHotelData.getHotel_picture(), holder.img_scenic_hotel_picture,
            JDYApplication.getApp().getmOptions());
      }
    }

    return convertView;
  }

  public class ViewHolder
  {

    @ViewInject(R.id.img_scenic_hotel_picture)
    private RoundImageView img_scenic_hotel_picture;

    @ViewInject(R.id.tv_scenic_hotel_cname)
    private TextView tv_scenic_hotel_cname;

    @ViewInject(R.id.tv_scenic_hotel_price)
    private TextView tv_scenic_hotel_price;

    @ViewInject(R.id.tv_scenic_hotel_star_level)
    private TextView tv_scenic_hotel_star_level;

    @ViewInject(R.id.tv_scenic_hotel_distance)
    private TextView tv_scenic_hotel_distance;


  }
}
