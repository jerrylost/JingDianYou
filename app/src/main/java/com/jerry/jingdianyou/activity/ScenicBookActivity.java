package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.jerry.jingdianyou.R;

/**
 * Created Jerry.Zou
 */
@ContentView(R.layout.activity_book_scenic)
public class ScenicBookActivity extends BaseActivity
{
  private String[] bookinfo = null;
  private String scenicname;
  private String scenicprice;
  private String bookrequire;

  @ViewInject(R.id.tv_book_scenic_name)
  private TextView mScenicName;
  @ViewInject(R.id.tv_book_sale_price)
  private TextView mSalePrice;
  @ViewInject(R.id.btn_booking_know)
  private Button mBookKnow;
  @ViewInject(R.id.tv_book_total_price)
  private TextView mTotalPrice;
  @ViewInject(R.id.tv_book_need_pay)
  private TextView mNeedPay;
  @ViewInject(R.id.tv_book_buy_num)
  private TextView mBuyNum;
  private int num = 0;
  private int total = 0;
  private int needpay = 0;
  private int price;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);
    Intent intent = getIntent();
    bookinfo = intent.getStringArrayExtra("bookinfo");
    initViews();
  }

  private void initViews()
  {
    scenicname = bookinfo[0];
    bookrequire = bookinfo[1];
    scenicprice = bookinfo[2];

    mScenicName.setText(scenicname);
    mSalePrice.setText(scenicprice);

  }

  @OnClick(R.id.btn_book_add)
  public void toAdd(View view)
  {
    num = Integer.parseInt(mBuyNum.getText().toString());
    price = Integer.parseInt(mSalePrice.getText().toString());
    num++;
    total = num * price;
    mBuyNum.setText("" + num);
    mTotalPrice.setText("" + total);
    mNeedPay.setText("" + total);
  }

  @OnClick(R.id.btn_book_sub)
  public void toSub(View view)
  {
    num = Integer.parseInt(mBuyNum.getText().toString());
    price = Integer.parseInt(mSalePrice.getText().toString());
    if (num <= 1)
    {
      num = 1;
    }
    num--;
    total = num * price;
    mBuyNum.setText("" + num);
    mTotalPrice.setText("" + total);
    mNeedPay.setText("" + total);
  }

  @OnClick(R.id.btn_book_requir)
  public void showBookRequir(View view)
  {
    AlertDialog.Builder builder = new AlertDialog.Builder(this).setMessage(bookrequire)
        .setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick(DialogInterface dialog, int which)
          {
            dialog.dismiss();
          }
        });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  @OnClick(R.id.btn_toback)
  public void toBack(View view)
  {
    this.finish();
  }

  @OnClick(R.id.btn_book_next)
  public void toNext(View view)
  {
    Toast.makeText(this, "快掏钱。。。。", Toast.LENGTH_SHORT).show();
  }
}
