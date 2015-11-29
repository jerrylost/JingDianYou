package com.jerry.jingdianyou.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jerry.jingdianyou.R;


public class SlideBar extends View
{

  public static String[] chars =
      {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
          "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
  private Paint mPaint = new Paint();
  private int choose = -1;

  private Context context;

  public SlideBar(Context context)
  {
    this(context, null);
    this.context = context;
  }

  public SlideBar(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }


  @Override
  protected void onDraw(Canvas canvas)
  {
    super.onDraw(canvas);

    int height = getHeight();

    int width = getWidth();

    int codeHeight = height / chars.length;

    for (int i = 0; i < chars.length; i++)
    {
      mPaint.setColor(Color.rgb(33, 65, 98));

      mPaint.setAntiAlias(true);

      mPaint.setTextSize(30);

      mPaint.setTypeface(Typeface.DEFAULT_BOLD);

      if (i == choose)
      {
        mPaint.setColor(Color.parseColor("#3399ff"));
      }

      float xpos = (width - mPaint.measureText(chars[i])) / 2;
      float ypos = codeHeight * (i + 1);
      canvas.drawText(chars[i], xpos, ypos, mPaint);

      mPaint.reset();
    }
  }

  private TextView mCharDialag;

  public void setCharDialag(TextView mCharDialag)
  {
    this.mCharDialag = mCharDialag;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event)
  {
    final float y = event.getY();
    final int action = event.getAction();

    int curentPosition = (int) (y / getHeight() * chars.length);

    switch (action)
    {
      case MotionEvent.ACTION_UP:

        if (mCharDialag != null)
        {
          mCharDialag.setVisibility(View.INVISIBLE);
        }

        choose = -1;

        break;
      case MotionEvent.ACTION_MOVE:
      case MotionEvent.ACTION_DOWN:
        setBackgroundResource(R.drawable.shape_slidebar_background);

        if (curentPosition >= 0 && curentPosition < chars.length)
        {

          if (listener != null)
          {
            listener.onItemClicked(chars[curentPosition]);
          }
          if (mCharDialag != null)
          {
            mCharDialag.setText(chars[curentPosition]);
            mCharDialag.setVisibility(View.VISIBLE);
          }

          choose = curentPosition;
        }
        break;
    }

    invalidate();

    return true;
  }

  private OnSlideBarItemClickListener listener;

  public void setOnSlideBarItemClickListener(OnSlideBarItemClickListener listener)
  {
    this.listener = listener;
  }

  public interface OnSlideBarItemClickListener
  {
    public void onItemClicked(String word);
  }


}
