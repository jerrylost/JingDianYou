package com.jerry.jingdianyou.adapter;

/**
 * Created by Jerry.Zou
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.RendezvousDetailActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.Rendezvous;

import java.util.ArrayList;
import java.util.List;

public class RendezvousAdapter extends BaseAdapter
{

  private List<Rendezvous.Data> objects = new ArrayList<>();

  private Context context;
  private LayoutInflater layoutInflater;
  private Intent intent = new Intent();

  public RendezvousAdapter(Context context, List<Rendezvous.Data> objects)
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
  public Rendezvous.Data getItem(int position)
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
      convertView = layoutInflater.inflate(R.layout.list_item_rendezvous, null);
      viewHolder = new ViewHolder();

      ViewUtils.inject(viewHolder, convertView);

      convertView.setTag(viewHolder);

    }
    viewHolder = (ViewHolder) convertView.getTag();

    viewHolder.tvName.setText(objects.get(position).getMember_name());
    viewHolder.tvRendezvousDestination.setText(objects.get(position).getDestination());
    viewHolder.tvCommentAmount.setText(objects.get(position).getComment_amount());
    viewHolder.tvRendezvousDate.setText(objects.get(position).getStart_date());
    viewHolder.tvRendezvousDetail.setText(objects.get(position).getRendezvous_content());
    viewHolder.tvPraiseAmount.setText(objects.get(position).getPraise_amount());
    viewHolder.tvSignupAmount.setText(objects.get(position).getSignup_amount() + "人约游");

    String sex = objects.get(position).getSex();
    Drawable drawable = null;
    // 女
    if ("0".equals(sex))
    {
      drawable = context.getResources().getDrawable(R.mipmap.com_nv);
      viewHolder.tvSex.setBackgroundResource(R.drawable.sex_type_shape01);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      viewHolder.tvSex.setCompoundDrawables(drawable, null, null, null);
    }
    else if ("1".equals(sex))
    { //男
      drawable = context.getResources().getDrawable(R.mipmap.com_nan);
      viewHolder.tvSex.setBackgroundResource(R.drawable.sex_type_shape02);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      viewHolder.tvSex.setCompoundDrawables(drawable, null, null, null);
    }
    else if ("".equals(sex))
    {
      viewHolder.tvSex.setBackgroundResource(R.drawable.sex_type_shape02);
    }

    viewHolder.tvSex.setText(objects.get(position).getAge());

    JDYApplication.getApp().getmImageLoader().displayImage(
        objects.get(position).getPhoto(), viewHolder.ivPhoto,
        JDYApplication.getApp().getmOptions()
    );

    convertView.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, RendezvousDetailActivity.class);
        intent.putExtra("rendezvous_id", objects.get(position).getRendezvous_id());
        intent.putExtra("member_id", objects.get(position).getMember_id());
        context.startActivity(intent);
      }
    });

    return convertView;
  }

  protected class ViewHolder
  {

    @ViewInject(R.id.iv_rendezvous_photo)
    private ImageView ivPhoto;

    @ViewInject(R.id.tv_rendezvous_destination)
    private TextView tvRendezvousDestination;

    @ViewInject(R.id.tv_rendezvous_date)
    private TextView tvRendezvousDate;

    @ViewInject(R.id.tv_rendezvous_detail)
    private TextView tvRendezvousDetail;

    @ViewInject(R.id.tv_signup_amount)
    private TextView tvSignupAmount;

    @ViewInject(R.id.tv_praise_amount)
    private TextView tvPraiseAmount;

    @ViewInject(R.id.tv_comment_amount)
    private TextView tvCommentAmount;

    @ViewInject(R.id.tv_rendezvous_name)
    private TextView tvName;
    @ViewInject(R.id.tv_rendezvous_sex)
    private TextView tvSex;
  }
}
