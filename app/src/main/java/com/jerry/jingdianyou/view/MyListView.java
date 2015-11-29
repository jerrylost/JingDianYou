package com.jerry.jingdianyou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.lidroid.xutils.view.annotation.event.OnTouch;

/**
 * Created by Jerry.Zou
 */
public class MyListView extends ListView
{
  public MyListView(Context context)
  {
    super(context);
  }

  public MyListView(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public MyListView(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
        MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
  }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        return super.onTouchEvent(ev);
//
//    }
}
