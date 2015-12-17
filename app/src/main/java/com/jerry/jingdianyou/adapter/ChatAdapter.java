package com.jerry.jingdianyou.adapter;

/**
 * Created by Jerry.Zou
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.Chat;
import com.jerry.jingdianyou.view.CircleImageView;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter
{

  private List<Chat.Data> objects = new ArrayList<>();

  private Context context;
  private LayoutInflater layoutInflater;

  public ChatAdapter(Context context, List<Chat.Data> list)
  {
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
    this.objects = list;
  }

  @Override
  public int getCount()
  {
    return objects.size();
  }

  @Override
  public Chat.Data getItem(int position)
  {
    return objects.get(position);
  }

  @Override
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    ViewHolder viewHolder = null;
    if (convertView == null)
    {
      convertView = layoutInflater.inflate(R.layout.list_item_chat, null);
      viewHolder = new ViewHolder();
      ViewUtils.inject(viewHolder, convertView);
      convertView.setTag(viewHolder);
    }

    viewHolder = (ViewHolder) convertView.getTag();
    String sex = objects.get(position).getSex();
    Drawable drawable = null;
    // 女
    if ("0".equals(sex))
    {
      drawable = context.getResources().getDrawable(R.mipmap.com_nv);
      viewHolder.tvChatSex.setBackgroundResource(R.drawable.sex_type_shape01);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }
    else if ("1".equals(sex))
    { //男
      drawable = context.getResources().getDrawable(R.mipmap.com_nan);
      viewHolder.tvChatSex.setBackgroundResource(R.drawable.sex_type_shape02);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }
    else if ("".equals(sex))
    {
      viewHolder.tvChatSex.setBackgroundResource(R.drawable.sex_type_shape02);
    }

    viewHolder.tvChatSex.setCompoundDrawables(drawable, null, null, null);
    viewHolder.tvChatSex.setText(objects.get(position).getAge());

    String distance = objects.get(position).getDistance();
    int dist = Integer.parseInt(distance);
    double dis = 0;
    dis = Math.round(dist / 100d) / 10d;
    if (dist < 1000)
    {
      viewHolder.tvChatDistance.setText(dist + "m");
    }
    else
    {
      viewHolder.tvChatDistance.setText(dis + " KM");
    }

    viewHolder.tvChatMemberName.setText(objects.get(position).getMember_name());
    viewHolder.tvSignature.setText(objects.get(position).getSignature());
    JDYApplication.getApp().getmImageLoader().displayImage(
        objects.get(position).getPhoto(), viewHolder.ivChatPhoto,
        JDYApplication.getApp().getmOptions()
    );
    return convertView;
  }

  protected class ViewHolder
  {
    @ViewInject(R.id.tv_signature)
    private TextView tvSignature;
    @ViewInject(R.id.iv_chat_photo)
    private CircleImageView ivChatPhoto;
    @ViewInject(R.id.tv_chat_member_name)
    private TextView tvChatMemberName;
    @ViewInject(R.id.tv_chat_distance)
    private TextView tvChatDistance;
    @ViewInject(R.id.tv_chat_sex)
    private TextView tvChatSex;
  }
}

