package com.jerry.jingdianyou.adapter;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.StrategyDetailWebView;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.MyWebView;
import com.jerry.jingdianyou.entity.StrategyDetail;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
public class StrategyDetailListAdapter extends BaseAdapter
{
  private Context context;
  private List<StrategyDetail.Data.GuidesInfoDataEntity.DataEntity> mDataList;
  private String id;


  public StrategyDetailListAdapter(Context context,
                                   List<StrategyDetail.Data.GuidesInfoDataEntity.DataEntity> mDataList,
                                   String id)
  {
    this.context = context;
    this.mDataList = mDataList;
    this.id = id;
  }

  @Override
  public int getCount()
  {
    return mDataList.size();
  }

  @Override
  public Object getItem(int position)
  {
    return mDataList.get(position);
  }

  @Override
  public long getItemId(int position)
  {

    return position;
  }

  @Override
  public View getView(final int position, final View convertView, ViewGroup parent)
  {
    View view = null;

    if (convertView != null)
    {
      view = convertView;
    }
    else
    {
      view = LayoutInflater.from(context).inflate(R.layout.list_strategy_detail, parent, false);
    }
    ViewHolder holder = (ViewHolder) view.getTag();
    if (holder == null)
    {
      holder = new ViewHolder();
      holder.mItemoutLined = (TextView) view.findViewById(R.id.item_outlined);
      holder.mItemDescription = (TextView) view.findViewById(R.id.item_description);
      holder.mItemPicture = (ImageView) view.findViewById(R.id.item_picture);

      holder.mCityCode = (TextView) view.findViewById(R.id.city_code);
      holder.mDay = (TextView) view.findViewById(R.id.day);


      holder.mLinearLayout = (LinearLayout) view.findViewById(R.id.detail_linearlayout);


    }
    StrategyDetail.Data.GuidesInfoDataEntity.DataEntity guidesInfoDataEntity = mDataList.get(position);

    //设置标题
    holder.mItemoutLined.setText(guidesInfoDataEntity.getItem_outlined());
    //设置描述
    holder.mItemDescription.setText(guidesInfoDataEntity.getItem_description());
    //第几天的城市位置
    holder.mCityCode.setText(guidesInfoDataEntity.getCity_code());

    if (position == 0)
    {
      holder.mLinearLayout.setVisibility(View.VISIBLE);


    }
    if (position == mDataList.size() / 2)
    {
      holder.mLinearLayout.setVisibility(View.VISIBLE);
    }
    //设置图片
    ImageLoader mImageLoader = JDYApplication.getApp().getmImageLoader();
    DisplayImageOptions mOptions = JDYApplication.getApp().getmOptions();
    holder.mItemPicture.setScaleType(ImageView.ScaleType.FIT_XY);
    mImageLoader.displayImage(guidesInfoDataEntity.getItem_picture(), holder.mItemPicture, mOptions);

    //设置点击事件
    view.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        StrategyDetail.Data.GuidesInfoDataEntity.DataEntity guidesInfoDataEntity = (StrategyDetail.Data.GuidesInfoDataEntity.DataEntity) getItem(position);
        Intent intent = new Intent();
        intent.setClass(context, StrategyDetailWebView.class);
        intent.putExtra("guides_line_order", guidesInfoDataEntity.getGuides_line_order());
        intent.putExtra("id", id);
        context.startActivity(intent);

      }
    });


    view.setTag(holder);
    return view;
  }


  public static class ViewHolder
  {
    public TextView mItemoutLined;
    public ImageView mItemPicture;
    public TextView mItemDescription;

    public TextView mCityCode;
    public TextView mDay;
    public LinearLayout mLinearLayout;
  }
}
