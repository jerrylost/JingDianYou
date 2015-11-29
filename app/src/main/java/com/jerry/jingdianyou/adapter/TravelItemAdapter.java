package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.ScenicBookActivity;
import com.jerry.jingdianyou.entity.ScenicDetail;
import com.jerry.jingdianyou.activity.RequirementActivtiy;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class TravelItemAdapter extends BaseAdapter
{
  private List<ScenicDetail.DataEntity.ScenicProductData> mTravallist = null;
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  ScenicDetail.DataEntity.ScenicProductData mTravelData;

  private String business_hours;
  private String productCost;
  private String enter_mode;
  private String backTo_rule;
  private String kindly_reminder;
  private String preference_policy;
  private String[] requirements = null;


  public TravelItemAdapter(Context mContext,
                           List<ScenicDetail.DataEntity.ScenicProductData> mTravallist)
  {
    this.mTravallist = mTravallist;
    this.mContext = mContext;
    this.mLayoutInflater = LayoutInflater.from(mContext);
  }

  @Override
  public int getCount()
  {
    return mTravallist.size();
  }

  @Override
  public ScenicDetail.DataEntity.ScenicProductData getItem(int position)
  {
    return mTravallist.get(position);
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
      convertView = mLayoutInflater.inflate(R.layout.list_travel_item, null);
      holder = new ViewHolder();
      ViewUtils.inject(holder, convertView);
      convertView.setTag(holder);
    }
    else
    {
      holder = (ViewHolder) convertView.getTag();
    }

    if (mTravallist.get(position) != null)
    {
      mTravelData = mTravallist.get(position);
    }
    if (!("".equals(mTravelData.getSub_product_name())))
    {
      holder.tv_travel_product_name.setText(mTravelData.getSub_product_name());
      scenicname = mTravelData.getSub_product_name();
    }
    if (!("".equals(mTravelData.getMarket_price())))
    {
      holder.tv_travel_market_price.setText("¥" + mTravelData.getMarket_price());
    }
    if (!("".equals(mTravelData.getSales_price())))
    {
      holder.tv_travel_sales_price.setText(mTravelData.getSales_price());
      scenicprice = mTravelData.getSales_price();
    }
    if (!("0".equals(mTravelData.getGive_integral())))
    {
      holder.tv_travel_give_integral.setText("返券" + mTravelData.getGive_integral());
    }
    else
    {
      holder.tv_travel_give_integral.setVisibility(View.INVISIBLE);
    }
    if (!("".equals(mTravelData.getBusiness_hours())))
    {
      business_hours = mTravelData.getBusiness_hours();
    }
    else
    {
      business_hours = "";
    }
    if (!("".equals(mTravelData.getProductCost())))
    {
      productCost = mTravelData.getProductCost();
    }
    else
    {
      productCost = "";
    }
    if (!("".equals(mTravelData.getEnter_mode())))
    {
      enter_mode = mTravelData.getEnter_mode();
    }
    else
    {
      enter_mode = "";
    }
    if (!("".equals(mTravelData.getBackTo_rule())))
    {
      backTo_rule = mTravelData.getBackTo_rule();
    }
    else
    {
      backTo_rule = "";
    }
    if (!("".equals(mTravelData.getKindly_reminder())))
    {
      kindly_reminder = mTravelData.getKindly_reminder();
    }
    else
    {
      kindly_reminder = "";
    }
    if (!("".equals(mTravelData.getPreference_policy())))
    {
      preference_policy = mTravelData.getPreference_policy();
    }
    else
    {
      preference_policy = "";
    }
    book_require = mTravelData.getBooking_requirement();
    requirements = new String[]{business_hours, productCost, enter_mode, backTo_rule, kindly_reminder, preference_policy};
    holder.btn_booking_know.setOnClickListener(new myOnClickListener());

    holder.ll_travel_item.setOnClickListener(new ToBook());

    //接下来应该写item的点击事件


    return convertView;
  }

  public class ViewHolder
  {

    @ViewInject(R.id.tv_travel_product_name)
    private TextView tv_travel_product_name;

    @ViewInject(R.id.btn_booking_know)
    private TextView btn_booking_know;

    @ViewInject(R.id.tv_travel_sales_price)
    private TextView tv_travel_sales_price;

    @ViewInject(R.id.tv_travel_market_price)
    private TextView tv_travel_market_price;

    @ViewInject(R.id.tv_travel_give_integral)
    private TextView tv_travel_give_integral;

    @ViewInject(R.id.ll_travel_item)
    private LinearLayout ll_travel_item;

  }

  //点击预定须知按钮后跳转到新的页面 的点击事件  应该传递  mTravelData
  public class myOnClickListener implements View.OnClickListener
  {
    @Override
    public void onClick(View v)
    {
      Intent intent1 = new Intent();
      intent1.putExtra("requirements", requirements);
      intent1.setClass(mContext, RequirementActivtiy.class);
      mContext.startActivity(intent1);
    }
  }

  private String book_require;
  private String[] bookinfo = null;
  private String scenicname;
  private String scenicprice;

  //点击listview的项可预定景区
  public class ToBook implements View.OnClickListener
  {

    @Override
    public void onClick(View v)
    {
      bookinfo = new String[]{scenicname, book_require, scenicprice};
      Intent intent = new Intent();
      intent.setClass(mContext, ScenicBookActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.putExtra("bookinfo", bookinfo);
      mContext.startActivity(intent);
    }
  }

}
