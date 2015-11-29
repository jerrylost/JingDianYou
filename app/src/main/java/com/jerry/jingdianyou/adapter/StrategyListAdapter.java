package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.StrategyDetailActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.Strategy;

import java.util.List;


public class StrategyListAdapter extends BaseAdapter
{
  private Context context;
  private List<Strategy.Data> mList;
  private DisplayImageOptions mOptions;
  private ImageLoader mImageLoader;

  public StrategyListAdapter(Context context, List<Strategy.Data> mList)
  {
    this.context = context;
    this.mList = mList;
  }

  @Override
  public int getCount()
  {
    int ret = 0;
    if (mList != null)
    {
      ret = mList.size();
    }
    return ret;
  }

  @Override
  public Object getItem(int position)
  {
    return mList.get(position);
  }

  @Override
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent)
  {
    View view = null;
    if (convertView != null)
    {
      view = convertView;
    }
    else
    {
      view = LayoutInflater.from(context).inflate(R.layout.list_strategy, parent, false);
    }
    ViewHolder holder = (ViewHolder) view.getTag();
    view.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Strategy.Data data = (Strategy.Data) getItem(position);
        String guides_id = data.getGuides_id();
        Intent intent = new Intent();
        intent.putExtra("guides_id", guides_id);
        intent.putExtra("pic", data.getGuides_pic());
        intent.putExtra("title", data.getGuides_name());
        intent.setClass(context, StrategyDetailActivity.class);
        context.startActivity(intent);
      }
    });
    if (holder == null)
    {
      holder = new ViewHolder();
      holder.mGuidesPic = (ImageView) view.findViewById(R.id.guides_pic);
      holder.mGuidesPic.setScaleType(ImageView.ScaleType.FIT_XY);
      holder.mGuidesName = (TextView) view.findViewById(R.id.guides_name);
      holder.mCollectAmount = (TextView) view.findViewById(R.id.collect_amount);
    }
    Strategy.Data data = mList.get(position);
    holder.mGuidesName.setText(data.getGuides_name());
    holder.mCollectAmount.setText(data.getCollect_amount());
    mImageLoader = JDYApplication.getApp().getmImageLoader();
    mOptions = JDYApplication.getApp().getmOptions();
    mImageLoader.displayImage(data.getGuides_pic(), holder.mGuidesPic, mOptions);


    view.setTag(holder);
    return view;
  }

  public class ViewHolder
  {

    public ImageView mGuidesPic;
    public TextView mGuidesName;
    public TextView mCollectAmount;
  }
}
