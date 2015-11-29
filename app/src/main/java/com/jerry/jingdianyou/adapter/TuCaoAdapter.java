package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.TuCaoDetailActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.OnWay;
import com.jerry.jingdianyou.entity.TuCao;
import com.jerry.jingdianyou.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry.Zou
 */

public class TuCaoAdapter extends BaseAdapter
{

  private List<TuCao.Data> objects = new ArrayList<>();

  private Context context;
  private LayoutInflater layoutInflater;
  private int width = 0;

  public TuCaoAdapter(Context context, List<TuCao.Data> objects, int width)
  {
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
    this.objects = objects;
    this.width = width;
  }

  @Override
  public int getCount()
  {
    return objects.size();
  }

  @Override
  public TuCao.Data getItem(int position)
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
      convertView = layoutInflater.inflate(R.layout.list_item_tucao, null);
      viewHolder = new ViewHolder();

      ViewUtils.inject(viewHolder, convertView);

      convertView.setTag(viewHolder);
    }

    viewHolder = (ViewHolder) convertView.getTag();

    viewHolder.tvOnWayName.setText(objects.get(position).getMember_name());
    viewHolder.tvTitle.setText(objects.get(position).getMessage_title());
    viewHolder.tvData.setText(objects.get(position).getMessage_date());
    viewHolder.tvOnWayMessageContent.setText(objects.get(position).getMessage_content());
    viewHolder.tvPing.setText(objects.get(position).getComment_amount());
    viewHolder.tvZan.setText(objects.get(position).getPraise_amount());

    String sex = objects.get(position).getSex();
    Drawable drawable = null;
    // 女
    if ("0".equals(sex))
    {
      drawable = context.getResources().getDrawable(R.mipmap.com_nv);
      viewHolder.tvOnWaySex.setBackgroundResource(R.drawable.sex_type_shape01);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }
    else if ("1".equals(sex))
    { //男
      drawable = context.getResources().getDrawable(R.mipmap.com_nan);
      viewHolder.tvOnWaySex.setBackgroundResource(R.drawable.sex_type_shape02);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }
    else if ("".equals(sex))
    {
      viewHolder.tvOnWaySex.setBackgroundResource(R.drawable.sex_type_shape02);
    }

    viewHolder.tvOnWaySex.setCompoundDrawables(drawable, null, null, null);
    viewHolder.tvOnWaySex.setText(objects.get(position).getAge());

    JDYApplication.getApp().getmImageLoader().displayImage(
        objects.get(position).getPhoto(), viewHolder.ivPhoto,
        JDYApplication.getApp().getmOptions()
    );

    LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(
        (width - 60) / 4, (width - 60) / 4);

    LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
        width - 20, (width - 60) / 4);
    llParams.topMargin = 10;

    ivParams.rightMargin = 10;
    ivParams.gravity = Gravity.CENTER;

    String photos = objects.get(position).getPhotos();
    String rex = "\\,";

    String[] arr = photos.split(rex);
    int line = arr.length / 4;
    int n = 0;
    viewHolder.llPhoto.removeAllViews();

    for (int j = 0; j <= line; j++)
    {
      if (j < line)
      {
        n = 4;
      }
      else if (j == line)
      {
        n = arr.length % 4;
      }

      if (n != 0)
      {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(llParams);
        layout.removeAllViews();
        for (int i = 0; i < n; i++)
        {
          ImageView imageView = new ImageView(context);
          String path = "http://kindin-web.oss-cn-beijing.aliyuncs.com" + arr[j * 4 + i];
          imageView.setScaleType(ImageView.ScaleType.FIT_XY);
          imageView.setLayoutParams(ivParams);

          JDYApplication.getApp().getmImageLoader().displayImage(
              path, imageView,
              JDYApplication.getApp().getmOptions());
          layout.addView(imageView);
        }

        viewHolder.llPhoto.addView(layout);
      }
    }

    convertView.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("tucao", objects.get(position));
        intent.setClass(context, TuCaoDetailActivity.class);
        context.startActivity(intent);
      }
    });

    return convertView;
  }

  protected class ViewHolder
  {

    @ViewInject(R.id.tv_tu_cao_message_title)
    private TextView tvTitle;

    @ViewInject(R.id.iv_tu_cao_photo)
    private CircleImageView ivPhoto;

    @ViewInject(R.id.tv_tu_cao_member_name)
    private TextView tvOnWayName;

    @ViewInject(R.id.tv_tu_cao_sex)
    private TextView tvOnWaySex;

    @ViewInject(R.id.tv_tu_cao_message_content)
    private TextView tvOnWayMessageContent;

    @ViewInject(R.id.ll_tu_cao_photos)
    private LinearLayout llPhoto;

    @ViewInject(R.id.tv_tu_cao_message_date)
    private TextView tvData;

    @ViewInject(R.id.tv_tu_cao_ping)
    private TextView tvPing;

    @ViewInject(R.id.tv_tu_cao_zan)
    private TextView tvZan;

  }
}

