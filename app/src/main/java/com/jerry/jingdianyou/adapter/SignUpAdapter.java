package com.jerry.jingdianyou.adapter;

/**
 * Created by Jerry.Zou
 */

import java.util.ArrayList;
import java.util.List;

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
import com.jerry.jingdianyou.entity.Rendezvous;
import com.jerry.jingdianyou.entity.RendezvousDtlList;
import com.jerry.jingdianyou.view.CircleImageView;

import android.widget.TextView;

public class SignUpAdapter extends BaseAdapter
{

  private List<RendezvousDtlList.Data> objects = new ArrayList<>();

  private Context context;
  private LayoutInflater layoutInflater;

  public SignUpAdapter(Context context, List<RendezvousDtlList.Data> objects)
  {
    this.context = context;
    this.objects = objects;
    this.layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount()
  {
    return objects.size();
  }

  @Override
  public RendezvousDtlList.Data getItem(int position)
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
    if (convertView == null)
    {
      convertView = layoutInflater.inflate(R.layout.list_item_sign_up, null);
      ViewHolder viewHolder = new ViewHolder();

      ViewUtils.inject(viewHolder, convertView);
      convertView.setTag(viewHolder);
    }

    ViewHolder viewHolder = (ViewHolder) convertView.getTag();
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


    viewHolder.tvSignUpName.setText(objects.get(position).getMember_name());
    viewHolder.tvSignUpSignature.setText(objects.get(position).getSignature());
    JDYApplication.getApp().getmImageLoader().displayImage(
        objects.get(position).getPhoto(), viewHolder.ivSignUpPhoto,
        JDYApplication.getApp().getmOptions()
    );
    return convertView;
  }

  protected class ViewHolder
  {
    @ViewInject(R.id.iv_sign_up_photo)
    private CircleImageView ivSignUpPhoto;
    @ViewInject(R.id.tv_sign_up_name)
    private TextView tvSignUpName;
    @ViewInject(R.id.tv_sign_up_sex)
    private TextView tvChatSex;
    @ViewInject(R.id.tv_sign_up_signature)
    private TextView tvSignUpSignature;
  }
}

