package com.jerry.jingdianyou.adapter;

/**
 * Created by Jerry.Zou
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.OnWayDetailActivity;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.CommentList;
import com.jerry.jingdianyou.entity.OnWay;
import com.jerry.jingdianyou.view.CircleImageView;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

public class OnWayDetailAdapter extends BaseAdapter
{

  private List<OnWay.Data> objects = new ArrayList<>();
  private Context context;
  private LayoutInflater layoutInflater;
  private int width = 0;
  private List<CommentList.Data> commentListData = new ArrayList<>();

  public OnWayDetailAdapter(Context context, List<OnWay.Data> objects,
                            List<CommentList.Data> commentListData, int width)
  {
    this.context = context;
    this.layoutInflater = LayoutInflater.from(context);
    this.objects = objects;
    this.commentListData = commentListData;
    this.width = width;
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
  public long getItemId(int position)
  {
    return position;
  }

  @Override
  public int getItemViewType(int position)
  {

    if (position == 0)
    {
      return 0;
    }
    else
    {
      return 1;
    }
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
      convertView = bindSetBaseView(position, convertView, parent);
    }
    else
    {
      convertView = setCommentView(position, convertView, parent);
    }


    return convertView;
  }

  private View bindSetBaseView(int position, View convertView, ViewGroup parent)
  {

    if (objects.size() == 0 || objects == null)
    {
      ((OnWayDetailActivity) context).finish();
    }
    ViewHolder viewHolder = null;
    if (convertView == null)
    {
      convertView = layoutInflater.inflate(R.layout.list_item_onway_detail, null);
      viewHolder = new ViewHolder();
      ViewUtils.inject(viewHolder, convertView);
      convertView.setTag(viewHolder);
    }

    viewHolder = (ViewHolder) convertView.getTag();

    viewHolder.tvOnwayDetailName.setText(objects.get(position).getMember_name());
    viewHolder.tvLoacation.setText(objects.get(position).getPosition_address());
    viewHolder.tvOnwayDetailData.setText(objects.get(position).getCreate_date());
    viewHolder.tvOnwayDetailContent.setText(objects.get(position).getMessage_content());
    viewHolder.tvOnwayCommentCount.setText("评价（" +
        objects.get(position).getComment_amount() + ")");
    viewHolder.tvPraiseCount.setText(objects.get(position).getPraise_amount() + "人觉得很赞");

    String sex = objects.get(position).getSex();
    Drawable drawable = null;
    // 女
    if ("0".equals(sex))
    {
      drawable = context.getResources().getDrawable(R.mipmap.com_nv);
      viewHolder.tvRendezvousDetailSex.setBackgroundResource(R.drawable.sex_type_shape01);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }
    else if ("1".equals(sex))
    { //男
      drawable = context.getResources().getDrawable(R.mipmap.com_nan);
      viewHolder.tvRendezvousDetailSex.setBackgroundResource(R.drawable.sex_type_shape02);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }
    else if ("".equals(sex))
    {
      viewHolder.tvRendezvousDetailSex.setBackgroundResource(R.drawable.sex_type_shape02);
    }

    viewHolder.tvRendezvousDetailSex.setCompoundDrawables(drawable, null, null, null);
    viewHolder.tvRendezvousDetailSex.setText(objects.get(position).getAge());

    JDYApplication.getApp().getmImageLoader().displayImage(
        objects.get(position).getPhoto(), viewHolder.ivOnwayDetailPhoto,
        JDYApplication.getApp().getmOptions()
    );
    int count = Integer.parseInt(objects.get(position).getComment_amount());
    if (count > 0)
    {
      viewHolder.llOnwayDetailRemind.setVisibility(View.GONE);
    }

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
    viewHolder.llOnwayDetail.removeAllViews();
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

        viewHolder.llOnwayDetail.addView(layout);
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

  protected class ViewHolder
  {
    @ViewInject(R.id.iv_onway_detail_photo)
    private CircleImageView ivOnwayDetailPhoto;

    @ViewInject(R.id.tv_onway_detail_name)
    private TextView tvOnwayDetailName;

    @ViewInject(R.id.tv_onway_detail_data)
    private TextView tvOnwayDetailData;

    @ViewInject(R.id.tv_onway_detail_sex)
    private TextView tvRendezvousDetailSex;

    @ViewInject(R.id.tv_onway_detail_content)
    private TextView tvOnwayDetailContent;

    @ViewInject(R.id.ll_onway_detail_photos)
    private LinearLayout llOnwayDetail;

    @ViewInject(R.id.tv_onway_comment_count)
    private TextView tvOnwayCommentCount;

    @ViewInject(R.id.ll_onway_detail_remind)
    private LinearLayout llOnwayDetailRemind;

    @ViewInject(R.id.tv_onway_detail_location)
    private TextView tvLoacation;

    @ViewInject(R.id.tv_onway_detail_praise_count)
    private TextView tvPraiseCount;
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

