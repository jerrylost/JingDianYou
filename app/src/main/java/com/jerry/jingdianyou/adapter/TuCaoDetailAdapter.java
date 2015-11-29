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
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.CommentList;
import com.jerry.jingdianyou.entity.OnWay;
import com.jerry.jingdianyou.entity.TuCao;
import com.jerry.jingdianyou.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry.Zou
 */

public class TuCaoDetailAdapter extends BaseAdapter
{

  private TuCao.Data data = null;
  private List<TuCao.Data> objects = new ArrayList<>();
  private int TYPE = 0;
  private Context context;
  private LayoutInflater layoutInflater;
  private int width = 0;
  private List<CommentList.Data> commentListData;

  public TuCaoDetailAdapter(Context context, List<CommentList.Data> commentListData,
                            List<TuCao.Data> objects, int width)
  {
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
    this.objects = objects;
    this.width = width;
    this.commentListData = commentListData;
  }

  @Override
  public int getCount()
  {
    return objects.size() + commentListData.size();
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
    if (objects.size() == 0)
    {
      return null;
    }
    TuCao.Data data = objects.get(0);
    if (convertView == null)
    {
      convertView = layoutInflater.inflate(R.layout.list_item_tucao_detail, null);
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

    viewHolder.tvOnWayName.setText(data.getMember_name());
    viewHolder.tvTitle.setText(data.getMessage_title());
    viewHolder.tvData.setText(data.getMessage_date());
    viewHolder.tvOnWayMessageContent.setText(data.getMessage_content());

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
    viewHolder.tvOnWaySex.setText(data.getAge());

    JDYApplication.getApp().getmImageLoader().displayImage(
        data.getPhoto(), viewHolder.ivPhoto,
        JDYApplication.getApp().getmOptions()
    );

    LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(
        (width - 60) / 4, (width - 60) / 4);

    LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
        width - 20, (width - 60) / 4);
    llParams.topMargin = 10;

    ivParams.rightMargin = 10;
    ivParams.gravity = Gravity.CENTER;

    String photos = data.getPhotos();
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
    return convertView;

  }

  private View setCommentView(int position, View convertView, ViewGroup parent)
  {
    ViewHolderComment viewHolder = null;
    if (convertView == null)
    {
      convertView = layoutInflater.inflate(R.layout.list_item_rendezvous_comment, null);
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
        View view = layoutInflater.inflate(R.layout.comment_reply, null);

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

