package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.CityActivity;
import com.jerry.jingdianyou.entity.City;
import com.jerry.jingdianyou.entity.CitySort;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class CityAdapter extends BaseAdapter
{
  private Context mContext;
  private List<CitySort> mCitys;
  private LayoutInflater mInflater;

  public CityAdapter(Context mContext, List<CitySort> mCitys)
  {
    this.mContext = mContext;
    this.mInflater = LayoutInflater.from(mContext);
    this.mCitys = mCitys;
  }

  @Override
  public int getCount()
  {
    return mCitys.size();
  }

  @Override
  public CitySort getItem(int position)
  {
    return mCitys.get(position);
  }

  @Override
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent)
  {
    ViewHolder holder = null;
    if (convertView == null)
    {
      convertView = mInflater.inflate(R.layout.list_city_item, null);
      holder = new ViewHolder();
      holder.category = (TextView) convertView.findViewById(R.id.category);
      holder.title = (TextView) convertView.findViewById(R.id.title);
      convertView.setTag(holder);
    }
    else
    {
      holder = (ViewHolder) convertView.getTag();
    }
    CitySort city = getItem(position);
    String categoryLetter = city.getLetter();
    int hitIndex = -1;
    for (int i = 0; i < mCitys.size(); i++)
    {
      if (categoryLetter.equals(mCitys.get(i).getLetter()))
      {
        hitIndex = i;
        break;
      }
    }
    //判断hitIndex 是否等于position
    //显示分类
    if (hitIndex == position)
    {
      holder.category.setVisibility(View.VISIBLE);
      String temp = city.getLetter();
      holder.category.setText(temp);
    }
    //否则不显示分类
    else
    {
      holder.category.setVisibility(View.GONE);
    }
    holder.title.setText(city.getCityName());
    holder.position = position;
    convertView.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        ViewHolder holder = (ViewHolder) v.getTag();
        CitySort item = getItem(holder.position);
        Intent intent = new Intent();
        intent.setAction("city");
        intent.putExtra("cityName", item.getCityName());
        intent.putExtra("cityCode", item.getCityId());
        mContext.sendBroadcast(intent);
        ((CityActivity) mContext).finish();
      }
    });


    return convertView;
  }

  public int getWordPosition(String word)
  {
    for (int i = 0; i < mCitys.size(); i++)
    {
      if (mCitys.get(i).getLetter().equals(word))
      {
        return i;
      }
    }

    return -1;
  }

  public static class ViewHolder
  {
    public TextView category;
    public TextView title;
    public int position;
  }
}
