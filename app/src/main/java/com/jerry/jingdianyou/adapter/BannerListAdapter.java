package com.jerry.jingdianyou.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.AdvertList;
import com.jerry.jingdianyou.entity.Home;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class BannerListAdapter<T> extends PagerAdapter
{

  private List<T> mBanners;
  private Context mContext;

  public BannerListAdapter(List<T> mBanners, Context mContext)
  {
    this.mBanners = mBanners;
    this.mContext = mContext;
  }

  @Override
  public int getCount()
  {
    return mBanners.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object)
  {
    return view == object;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position)
  {
    ImageView imageView = null;
    if (mBanners.get(position) instanceof Home.AdvertLinkData)
    {
      imageView = new ImageView(mContext);
      Home.AdvertLinkData bannerList =
          (Home.AdvertLinkData) mBanners.get(position);
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);

      JDYApplication.getApp().getmImageLoader().displayImage(
          bannerList.getAdvert_pic(),
          imageView,
          JDYApplication.getApp().getmOptions()
      );
    }
    else if (mBanners.get(position) instanceof AdvertList.Data)
    {
      imageView = new ImageView(mContext);
      AdvertList.Data bannerList =
          (AdvertList.Data) mBanners.get(position);
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);

      JDYApplication.getApp().getmImageLoader().displayImage(
          bannerList.getAdvert_pic(),
          imageView,
          JDYApplication.getApp().getmOptions()
      );
    }
    container.addView(imageView);

    return imageView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object)
  {
    container.removeView((View) object);
  }
}
