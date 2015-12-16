package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.jerry.jingdianyou.activity.SignUpListActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.CommentList;
import com.jerry.jingdianyou.entity.RendezvousDtlList;
import com.jerry.jingdianyou.entity.RendezvousInfo;
import com.jerry.jingdianyou.view.CircleImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class RendezvousDetailAdapter extends BaseAdapter
{

  private RendezvousInfo.DataEntity rendezvousInfoData;
  private List<RendezvousDtlList.Data> rendezvousDtlListData;
  private List<CommentList.Data> commentListData;
  private Context context;
  private LayoutInflater inflater;
  private List<RendezvousInfo.DataEntity> mList;
  private int TYPE = 0;
  private int widthPixels = 0;

  public
  RendezvousDetailAdapter(int widthPixels, List<RendezvousInfo.DataEntity> mList,
                                 Context context,
                                 List<CommentList.Data> commentListData,
                                 List<RendezvousDtlList.Data> rendezvousDtlListData)
  {
    this.mList = mList;
    this.context = context;
    this.commentListData = commentListData;
    this.rendezvousDtlListData = rendezvousDtlListData;
    inflater = LayoutInflater.from(context);
    this.widthPixels = widthPixels;
  }

  @Override
  public int getCount()
  {
    return mList.size() + commentListData.size();
  }

  @Override
  public Object getItem(int position)
  {
    return null;
  }

  @Override
  public int getItemViewType(int position)
  {

    if (position == 0)
    {
      TYPE = 0;
    }
    else
    {
      TYPE = 1;
    }
    return TYPE;
  }

  @Override
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public int getViewTypeCount()
  {
    return 2;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    int type = getItemViewType(position);

    if (type == 0)
    {
      convertView = setBaseView(position, convertView, parent);
    }
    else if (type == 1)
    {
      convertView = setCommentView(position, convertView, parent);
    }
    return convertView;
  }

  private View setBaseView(int position, View convertView, ViewGroup parent)
  {
    ViewHolder viewHolder = null;
    if (mList.size() == 0)
    {
      return null;
    }
    rendezvousInfoData = mList.get(0);

    if (convertView == null)
    {
      convertView = inflater.inflate(R.layout.list_item_rendezvous_detail, null);
      viewHolder = new ViewHolder();

      ViewUtils.inject(viewHolder, convertView);
      convertView.setTag(viewHolder);
    }
    viewHolder = (ViewHolder) convertView.getTag();

    if (commentListData.size() == 0)
    {
      viewHolder.mTvCount.setText("评价" + "(" + 0 + "):");
      viewHolder.mLLRemind.setVisibility(View.VISIBLE);
    }
    else
    {
      int size = commentListData.size();
      viewHolder.mTvCount.setText("评价" + "(" + size + "):");
      viewHolder.mLLRemind.setVisibility(View.GONE);
    }

    viewHolder.tvRendezvousDetailName.setText(rendezvousInfoData.getMember_name());
    viewHolder.tvRendezvousDetailDetail.setText(rendezvousInfoData.getRendezvous_content());
    viewHolder.tvRendezvousDetailDestination.setText(rendezvousInfoData.getDestination());
    viewHolder.tvRendezvousDetailDate.setText(rendezvousInfoData.getStart_date());

    String sex = rendezvousInfoData.getSex();
    Drawable drawable = null;
    // 女
    if ("0".equals(sex))
    {
      drawable = context.getResources().getDrawable(R.mipmap.com_nv);
      viewHolder.tvRendezvousDetailSex.setBackgroundResource(R.drawable.sex_type_shape01);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      viewHolder.tvRendezvousDetailSex.setCompoundDrawables(drawable, null, null, null);
    }
    else if ("1".equals(sex))
    { //男
      drawable = context.getResources().getDrawable(R.mipmap.com_nan);
      viewHolder.tvRendezvousDetailSex.setBackgroundResource(R.drawable.sex_type_shape02);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      viewHolder.tvRendezvousDetailSex.setCompoundDrawables(drawable, null, null, null);
    }
    else if ("".equals(sex))
    {
      viewHolder.tvRendezvousDetailSex.setBackgroundResource(R.drawable.sex_type_shape02);
    }

    viewHolder.tvRendezvousDetailSex.setText(rendezvousInfoData.getAge());

    JDYApplication.getApp().getmImageLoader().displayImage(
        rendezvousInfoData.getPhoto(), viewHolder.ivRendezvousDetailPhoto,
        JDYApplication.getApp().getmOptions()
    );

    // 设置 报名列表
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        (widthPixels - 120) / 6, (widthPixels - 120) / 6
    );
    params.leftMargin = 10;

    int size = rendezvousDtlListData.size();

    if (size > 4)
    {
      size = 4;
      viewHolder.ivDetailMore.setVisibility(View.VISIBLE);
    }
    viewHolder.mLLPics.removeAllViews();
    ImageView imageView = null;
    for (int i = 0; i < size; i++)
    {
      imageView = new CircleImageView(context);
      imageView.setLayoutParams(params);
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      JDYApplication.getApp().getmImageLoader().displayImage(
          rendezvousDtlListData.get(i).getPhoto(),
          imageView, JDYApplication.getApp().getmOptions()
      );
      viewHolder.mLLPics.addView(imageView);
    }
    viewHolder.ivDetailMore.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putSerializable("sign", (Serializable) rendezvousDtlListData);
        intent.putExtra("bundle", bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, SignUpListActivity.class);
        context.startActivity(intent);
      }
    });
    return convertView;
  }

  private View setCommentView(int position, View convertView, ViewGroup parent)
  {
    ViewHolderComment viewHolder = null;
    if (convertView == null)
    {
      convertView = inflater.inflate(R.layout.list_item_rendezvous_comment, null);
      viewHolder = new ViewHolderComment();

      ViewUtils.inject(viewHolder, convertView);
      convertView.setTag(viewHolder);
    }
    viewHolder = (ViewHolderComment) convertView.getTag();
    position = position - 1;

    viewHolder.mName.setText(commentListData.get(position).getMember_name());
    viewHolder.mContent.setText(commentListData.get(position).getMessage_content());
    viewHolder.mDate.setText(commentListData.get(position).getMessage_date());
    viewHolder.mPin.setText(commentListData.get(position).getReply_amount());
    viewHolder.mZan.setText(commentListData.get(position).getPraise_amount());

    JDYApplication.getApp().getmImageLoader().displayImage(
        commentListData.get(position).getPhoto(), viewHolder.mIvPhoto,
        JDYApplication.getApp().getmOptions()
    );

    // 回复消息
    List<CommentList.Data.DataA> data = commentListData.get(position).getData();

    if (data.size() > 0 && data != null)
    {
      for (int i = 0; i < data.size(); i++)
      {

        viewHolder.mLLReply.setVisibility(View.VISIBLE);
        View view = inflater.inflate(R.layout.comment_reply, null);
        TextView textName = (TextView) view.findViewById(R.id.tv_comment_reply_name);
        TextView textContent = (TextView) view.findViewById(
            R.id.tv_comment_reply_message_content
        );
        CommentList.Data.DataA dataA = data.get(i);

        if (dataA == null)
        {
          break;
        }

        textName.setText(data.get(i).getMember_name() + ":");

        textContent.setText(data.get(i).getMessage_content() + "   " +
            data.get(i).getMessage_date());

        viewHolder.mLLReply.addView(view);
      }
    }
    return convertView;
  }

  private class ViewHolder
  {

    @ViewInject(R.id.iv_rendezvous_detail_photo)
    private CircleImageView ivRendezvousDetailPhoto;

    @ViewInject(R.id.tv_rendezvous_detail_name)
    private TextView tvRendezvousDetailName;

    @ViewInject(R.id.tv_rendezvous_detail_sex)
    private TextView tvRendezvousDetailSex;

    @ViewInject(R.id.tv_rendezvous_detail_destination)
    private TextView tvRendezvousDetailDestination;

    @ViewInject(R.id.tv_rendezvous_detail_date)
    private TextView tvRendezvousDetailDate;

    @ViewInject(R.id.tv_rendezvous_detail_detail)
    private TextView tvRendezvousDetailDetail;

    @ViewInject(R.id.ll_rendezvous_detail_pics)
    private LinearLayout mLLPics;

    @ViewInject(R.id.iv_detail_more)
    private ImageView ivDetailMore;

    @ViewInject(R.id.ll_comment_remind)
    private LinearLayout mLLRemind;
    @ViewInject(R.id.tv_rendezvous_comment_count)
    private TextView mTvCount;
  }

  private class ViewHolderComment
  {

    @ViewInject(R.id.iv_comment_photo)
    private ImageView mIvPhoto;
    @ViewInject(R.id.tv_comment_praise_amount)
    private TextView mZan;
    @ViewInject(R.id.tv_comment_comment_amount)
    private TextView mPin;
    @ViewInject(R.id.tv_comment_content)
    private TextView mContent;
    @ViewInject(R.id.tv_comment_date)
    private TextView mDate;
    @ViewInject(R.id.tv_comment_name)
    private TextView mName;

    @ViewInject(R.id.ll_comment_reply)
    private LinearLayout mLLReply;

  }
}
