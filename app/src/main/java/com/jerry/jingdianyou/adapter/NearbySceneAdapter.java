package com.jerry.jingdianyou.adapter;

/**
 * Created by Jerry.Zou
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.ScenicDetailActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.NearScene;
import com.jerry.jingdianyou.view.RoundImageView;

import android.widget.TextView;
import android.widget.ImageView;

public class NearbySceneAdapter extends BaseAdapter
{

  private List<NearScene.Data> objects = new ArrayList<>();

  private Context context;
  private LayoutInflater layoutInflater;

  public NearbySceneAdapter(Context context, List<NearScene.Data> objects)
  {
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
    this.objects = objects;
  }

  @Override
  public int getCount()
  {
    return objects.size();
  }

  @Override
  public NearScene.Data getItem(int position)
  {
    return objects.get(position);
  }

  @Override
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent)
  {
    ViewHolder viewHolder = null;
    if (convertView == null)
    {
      convertView = layoutInflater.inflate(R.layout.list_item_nearby_scene, null);
      viewHolder = new ViewHolder();

      ViewUtils.inject(viewHolder, convertView);
      convertView.setTag(viewHolder);
    }
    viewHolder = (ViewHolder) convertView.getTag();

    String level = objects.get(position).getLevel();

    if ("0".equals(level))
    {

    }
    else if ("1".equals(level))
    {
      viewHolder.ivA.setBackgroundResource(R.mipmap.scores_a);
    }
    else if ("2".equals(level))
    {
      viewHolder.ivA.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivB.setBackgroundResource(R.mipmap.scores_a);
    }
    else if ("3".equals(level))
    {
      viewHolder.ivA.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivB.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivC.setBackgroundResource(R.mipmap.scores_a);
    }
    else if ("4".equals(level))
    {
      viewHolder.ivA.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivB.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivC.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivD.setBackgroundResource(R.mipmap.scores_a);
    }
    else if ("5".equals(level))
    {
      viewHolder.ivA.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivB.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivC.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivD.setBackgroundResource(R.mipmap.scores_a);
      viewHolder.ivE.setBackgroundResource(R.mipmap.scores_a);
    }

    int dist = Integer.parseInt(objects.get(position).getDistance());
    double dis = 0;
    dis = Math.round(dist / 100d) / 10d;
    if (dist < 1000)
    {
      viewHolder.tvScenicDistance.setText(objects.get(position).getDistance() + "m");
    }
    else
    {
      viewHolder.tvScenicDistance.setText(dis + "KM");
    }

    if ("".equals(objects.get(position).getSales_price()))
    {
      viewHolder.tvScenicSalesPrice.setVisibility(View.INVISIBLE);
      viewHolder.tvUp.setVisibility(View.INVISIBLE);
    }
    else
    {
      viewHolder.tvScenicSalesPrice.setVisibility(View.VISIBLE);
      viewHolder.tvUp.setVisibility(View.VISIBLE);
      viewHolder.tvScenicSalesPrice.setText("￥" + objects.get(position).getSales_price());
    }

    if ("".equals(objects.get(position).getMarket_price()))
    {
      viewHolder.tvScenicMarketPrice.setVisibility(View.INVISIBLE);
    }
    else
    {
      viewHolder.tvScenicMarketPrice.setVisibility(View.VISIBLE);
      viewHolder.tvScenicMarketPrice.setText(objects.get(position).getMarket_price());
    }

    viewHolder.tvScenicName.setText(objects.get(position).getScenic_name());

    JDYApplication.getApp().getmImageLoader().displayImage(
        objects.get(position).getProduct_pic(), viewHolder.ivScenicProductPic,
        JDYApplication.getApp().getmOptions()
    );


    convertView.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, ScenicDetailActivity.class);
        intent.putExtra("scenic_spot_code", objects.get(position).getScenic_spot_code());
        context.startActivity(intent);
      }
    });

    return convertView;
  }


  protected class ViewHolder
  {
    @ViewInject(R.id.iv_scenic_product_pic)
    private RoundImageView ivScenicProductPic;
    @ViewInject(R.id.tv_scenic_name)
    private TextView tvScenicName;
    @ViewInject(R.id.iv_a)
    private ImageView ivA;
    @ViewInject(R.id.iv_b)
    private ImageView ivB;
    @ViewInject(R.id.iv_c)
    private ImageView ivC;
    @ViewInject(R.id.iv_d)
    private ImageView ivD;
    @ViewInject(R.id.iv_e)
    private ImageView ivE;
    @ViewInject(R.id.tv_scenic_distance)
    private TextView tvScenicDistance;
    @ViewInject(R.id.tv_scenic_sales_price)
    private TextView tvScenicSalesPrice;
    @ViewInject(R.id.tv_scenic_market_price)
    private TextView tvScenicMarketPrice;
    @ViewInject(R.id.tv_up)
    private TextView tvUp;
  }
}

