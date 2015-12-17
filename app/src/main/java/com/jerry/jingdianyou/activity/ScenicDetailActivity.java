package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jerry.jingdianyou.db.JingDbHelper;
import com.jerry.jingdianyou.db.JingDbTable;
import com.jerry.jingdianyou.utils.ToastUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.TravelItemAdapter;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.entity.ScenicDetail;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;
import com.jerry.jingdianyou.view.MyListView;
import com.jerry.jingdianyou.view.RoundImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_scenic_datail)
public class ScenicDetailActivity extends BaseActivity
{
    //详情页面的标题上的风景名
    @ViewInject(R.id.tv_detail_scenic_name)
    private Button mdetail_scenic_name;

    //详情页面的风景图片
    @ViewInject(R.id.img_detail_product_pic)
    private ImageView mproduct_pic;

    //景区地址，也用来展示提醒
    @ViewInject(R.id.tv_scenic_address_reqirement)
    private TextView maddress_reqirement;

    //景区简介
    @ViewInject(R.id.product_description)
    private TextView mproduct_description;
    //返券
    @ViewInject(R.id.tv_travel_give_integral)
    private TextView mgive_integral;

    //加载图片的imageloader
    private ImageLoader mImageloader;

    //定义查看详情所需的请求参数
    private String scenic_spot_code;
    private String scenic_city;

    //定义一个显示进度的对话框
    private ProgressDialog mProgressDialog;
    private JDYHttpConnect mHttpConnect;

    //实现地址和注意  之间转换的图片按钮
    @ViewInject(R.id.img_booking_requirement)
    private ImageView mchangaddtoreq;

    //定义一个map集合用来存放请求参数
    private Map<String, Object> params = new HashMap<>();

    //加载travel栏的listview
    @ViewInject(R.id.lv_item_travel)
    private MyListView mLvTravel;
    //---------------------------------------------------
    @ViewInject(R.id.tv_travel_product_name)
    private TextView tv_travel_product_name;
    //预定须知按钮
    @ViewInject(R.id.btn_booking_know)
    private Button btn_booking_know;
    @ViewInject(R.id.tv_travel_sales_price)
    private TextView tv_travel_sales_price;
    @ViewInject(R.id.tv_travel_market_price)
    private TextView tv_travel_market_price;
    //酒店模块的各变量
    @ViewInject(R.id.tv_scenic_hotel_cname)
    private TextView tv_scenic_hotel_cname;
    @ViewInject(R.id.tv_scenic_hotel_distance)
    private TextView tv_scenic_hotel_distance;
    @ViewInject(R.id.img_scenic_hotel_picture)
    private RoundImageView img_scenic_hotel_picture;
    @ViewInject(R.id.tv_scenic_hotel_star_level)
    private TextView tv_scenic_hotel_star_level;
    @ViewInject(R.id.tv_scenic_hotel_price)
    private TextView tv_scenic_hotel_price;
    //推荐模块的各变量
    @ViewInject(R.id.tv_recommend_scenic_name)
    private TextView mRecomSceName;
    @ViewInject(R.id.tv_recommend_scenic_distance)
    private TextView mRecomSceDistance;
    @ViewInject(R.id.tv_recommend_scenic_price)
    private TextView mRecomScePrice;
    @ViewInject(R.id.img_recommend_scenic_pic)
    private ImageView mRecomScePic;


    //-------------------------------------------------------
    private TravelItemAdapter mTravelAdapter;
    //只显示一条信息
    @ViewInject(R.id.ll_travel_item)
    private LinearLayout mllTavelItem;
    //获取景区信息
    private List<ScenicDetail.DataEntity.ScenicProductData> mTraveldata;


    //获取景区旅游栏的数据
    private ScenicDetail.DataEntity.ScenicProductData data = null;
    //获取景区酒店信息
    private ScenicDetail.DataEntity.HotelData hotel_data = null;
    //获取推荐信息
    private ScenicDetail.DataEntity.RecommendScenicData recom_data = null;
    private JingDbHelper jingDbHelper;
    private String saveTitle;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        jingDbHelper = new JingDbHelper(this);
        this.mImageloader = JDYApplication.getApp().getmImageLoader();
        mHttpConnect = JDYHttpConnect.getInstance();
        initViews();
        initDatas();

    }

    private ScenicDetail mDetail = null;


    private void initDatas()
    {
        //获得传递过来的参数
        Intent intent = getIntent();
        scenic_spot_code = intent.getStringExtra("scenic_spot_code");
        params.put("RequestJson", "{" +
                "\"scenic_spot_code\":\"" + scenic_spot_code + "\"," +
                "\"member_id\":\"HY000000000000080402\"}");
        mHttpConnect.getInstance().getScenicDetail(params, new DataCallBack()
        {
            @Override
            public void onSuccess(String response)
            {
                Gson gson = new Gson();
                mDetail = gson.fromJson(response, ScenicDetail.class);
                if (mDetail.getData() == null)
                {
                    return;
                }
                setDetailViews();
                if (mProgressDialog.isShowing())
                {
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(String error)
            {
                if (mProgressDialog.isShowing())
                {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    private void initViews()
    {
        //提示正在加载
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在加载中....");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    private ScenicDetail.DataEntity dataEntity = null;
    //定义一个数用于改变按钮状态
    private int mChang = 1;
    //获取预定须知的内容
    private String mRequirement;
    //获取地址的内容
    private String mAddress;
    //定位图标
    @ViewInject(R.id.img_mark)
    private ImageView mark;
    //获取简介内容
    private String mDescription;
    //简介模块，有时候需要隐藏
    @ViewInject(R.id.ll_description)
    private LinearLayout mLldescription;
    //酒店模块，有时候需要隐藏
    @ViewInject(R.id.ll_scenic_hotel)
    private LinearLayout mLlscenichotel;
    @ViewInject(R.id.ll_travel_head)
    private LinearLayout mLlTravelHead;
    //推荐模块，有时候需要隐藏
    @ViewInject(R.id.ll_recomen_head)
    private LinearLayout mLlRecomHead;
    //找到按钮
    @ViewInject(R.id.btn_change_travel_state)
    private Button mbtn_change_travel_state;

    @ViewInject(R.id.tv_up)
    private TextView up;


    //设置布局，加载信息
    private void setDetailViews()
    {
        dataEntity = mDetail.getData();
        if ((dataEntity == null) || !("0".equals(mDetail.getResultStatus())))
        {
            return;
        }
        else
        {
            if (mDetail.getData().getScenic_product_data() == null)
            {
                mLlTravelHead.setVisibility(View.GONE);
                return;
            }
            else
            {
                //获取景区信息
                mTraveldata = mDetail.getData().getScenic_product_data();
                if (!mTraveldata.isEmpty())
                {
                    book_require = mTraveldata.get(0).getBooking_requirement();
                }

                mAddress = dataEntity.getAddress();
                mDescription = dataEntity.getProduct_description();

                //保存到数据库中的景点标题
                saveTitle = dataEntity.getScenic_name();

                mdetail_scenic_name.setText(saveTitle);
                if (!("".equals(mAddress)))
                {
                    maddress_reqirement.setText(mAddress);
                }
                if (!("".equals(mDescription)))
                {
                    mproduct_description.setText(mDescription);
                }
                else
                {
                    mLldescription.setVisibility(View.GONE);
                }
                mImageloader.displayImage(dataEntity.getProduct_pic(), mproduct_pic,
                        JDYApplication.getApp().getmOptions());
                mTravelAdapter = new TravelItemAdapter(this, mTraveldata);
                if (mTraveldata.size() == 1)
                {
                    mbtn_change_travel_state.setVisibility(View.GONE);
                }
                //listview展示的旅游栏信息
                mLvTravel.setAdapter(mTravelAdapter);
            }

            //设置旅游栏信息
            //-------------------------------------------------
            if (mTraveldata.size() == 0 || mLvTravel == null)
            {
                mLlTravelHead.setVisibility(View.GONE);
                return;
            }
            else
            {
                if (!("".equals(mTraveldata.get(0).getSub_product_name())))
                {
                    tv_travel_product_name.setText(mTraveldata.get(0).getSub_product_name());
                    scenicname = mTraveldata.get(0).getSub_product_name();
                }
                if (!("".equals(mTraveldata.get(0).getMarket_price())))
                {
                    tv_travel_market_price.setText("¥" + mTraveldata.get(0).getMarket_price());
                }
                if (!("".equals(mTraveldata.get(0).getSales_price())))
                {
                    tv_travel_sales_price.setText(mTraveldata.get(0).getSales_price());
                    scenicprice = mTraveldata.get(0).getSales_price();
                }
                if (!("0".equals(mTraveldata.get(0).getGive_integral())))
                {
                    mgive_integral.setText("返券" + mTraveldata.get(0).getGive_integral());
                }
                else
                {
                    mgive_integral.setVisibility(View.INVISIBLE);
                }
            }

            //设置酒店信息
            //-------------------------------------------------

            if (dataEntity.getHotel_data() == null)
            {
                mLlscenichotel.setVisibility(View.GONE);
                return;
            }
            else
            {
                hotel_data = dataEntity.getHotel_data();
                tv_scenic_hotel_cname.setText(hotel_data.getHotel_cname());
                tv_scenic_hotel_price.setText("¥" + hotel_data.getPrice());
                tv_scenic_hotel_distance.setText(hotel_data.getDistance() + "m");
                tv_scenic_hotel_star_level.setText(hotel_data.getStar_level() + "星级");
                if (!("".equals(hotel_data.getCity_name())))
                {
                    scenic_city = hotel_data.getCity_name();
                }
                if (!("".equals(hotel_data.getHotel_picture())))
                {
                    mImageloader.displayImage(hotel_data.getHotel_picture(), img_scenic_hotel_picture,
                            JDYApplication.getApp().getmOptions());
                }
            }

            //设置推荐信息
            //----------------------------------------------------------------
            if (dataEntity.getRecommend_scenic_data() == null)
            {
                mLlRecomHead.setVisibility(View.GONE);
                return;
            }
            else
            {
                recom_data = dataEntity.getRecommend_scenic_data();
                if ("".equals(recom_data.getScenic_name()))
                {
                    mRecomSceName.setVisibility(View.INVISIBLE);
                }
                mRecomSceName.setText(recom_data.getScenic_name());
                if ("".equals(recom_data.getDistance()))
                {
                    mRecomSceDistance.setVisibility(View.INVISIBLE);
                }
                mRecomSceDistance.setText("距离" + recom_data.getDistance() + "m");
                if ("".equals(recom_data.getSales_price()))
                {
                    mRecomScePrice.setVisibility(View.INVISIBLE);
                    up.setVisibility(View.INVISIBLE);
                }
                mRecomScePrice.setText("¥ " + recom_data.getSales_price());
                if (!("".equals(recom_data.getProduct_pic())))
                {
                    mImageloader.displayImage(recom_data.getProduct_pic(), mRecomScePic,
                            JDYApplication.getApp().getmOptions());
                }
            }
        }
    }
    //----------------------------------------------------------------
    //点击事件，点击进入预定景区页面

    private String book_require;
    private String[] bookinfo = null;
    private String scenicname;
    private String scenicprice;

    @OnClick(R.id.ll_travel_item)
    public void toBook(View view)
    {
        bookinfo = new String[]{scenicname, book_require, scenicprice};
        Intent intent = new Intent();
        intent.setClass(this, ScenicBookActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("bookinfo", bookinfo);
        this.startActivity(intent);
    }


    //----------------------------------------------------------------
    //点击事件，点击显示用户须知，再次点击显示景区地址
    @OnClick(R.id.img_booking_requirement)
    public void changaddtoreq(View view)
    {

        if (mTraveldata.size() == 0)
        {
            return;
        }
        //获取用户须知的内容
        mRequirement = mTraveldata.get(0).getBooking_requirement();

        if (mChang == 1)
        {
            mChang = 2;
            if (!("".equals(mRequirement)))
            {
                maddress_reqirement.setText(mRequirement);
            }
            mark.setVisibility(View.GONE);
        }
        else if (mChang == 2)
        {
            mChang = 1;
            if (!("".equals(mAddress)))
            {
                maddress_reqirement.setText(mAddress);
            }
            mark.setVisibility(View.VISIBLE);
        }
    }


    @ViewInject(R.id.btn_change_travel_state)
    private Button chageTravel;
    int more = 1;

    @OnClick(R.id.btn_change_travel_state)
    public void changeTravelState(View view)
    {
        if (more == 1)
        {
            more = 2;
            mLvTravel.setVisibility(View.VISIBLE);
            mllTavelItem.setVisibility(View.GONE);
            chageTravel.setText("收起");
        }
        else if (more == 2)
        {
            more = 1;
            mLvTravel.setVisibility(View.GONE);
            mllTavelItem.setVisibility(View.VISIBLE);
            chageTravel.setText("查看更多");
        }
    }

    //----------------------------------------------------------------
    //点击后跳转到显示简介的页面
    @OnClick(R.id.product_description)
    public void ShowdescriptWebview(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("web_scenic_spot_code", scenic_spot_code);
        intent.setClass(ScenicDetailActivity.this, DescriptionActivity.class);
        this.startActivity(intent);
    }

    //点击预定须知之后会跳转到新的页面，一下五个是要展示的参数
    private String business_hours;
    private String productCost;
    private String enter_mode;
    private String backTo_rule;
    private String kindly_reminder;
    private String preference_policy;
    private String[] requirements = null;
    private String[] mHotelParams = null;

    @OnClick(R.id.btn_booking_know)
    public void showRequirement(View view)
    {
        if (!("".equals(mTraveldata.get(0).getBusiness_hours())))
        {
            business_hours = mTraveldata.get(0).getBusiness_hours();
        }
        else
        {
            business_hours = "";
        }
        if (!("".equals(mTraveldata.get(0).getProductCost())))
        {
            productCost = mTraveldata.get(0).getProductCost();
        }
        else
        {
            productCost = "";
        }
        if (!("".equals(mTraveldata.get(0).getEnter_mode())))
        {
            enter_mode = mTraveldata.get(0).getEnter_mode();
        }
        else
        {
            enter_mode = "";
        }
        if (!("".equals(mTraveldata.get(0).getBackTo_rule())))
        {
            backTo_rule = mTraveldata.get(0).getBackTo_rule();
        }
        else
        {
            backTo_rule = "";
        }
        if (!("".equals(mTraveldata.get(0).getKindly_reminder())))
        {
            kindly_reminder = mTraveldata.get(0).getKindly_reminder();
        }
        else
        {
            kindly_reminder = "";
        }
        if (!("".equals(mTraveldata.get(0).getPreference_policy())))
        {
            preference_policy = mTraveldata.get(0).getPreference_policy();
        }
        else
        {
            preference_policy = "";
        }
        requirements = new String[]{business_hours, productCost, enter_mode, backTo_rule, kindly_reminder, preference_policy};
        Intent intent1 = new Intent();
        intent1.putExtra("requirements", requirements);
        intent1.setClass(this, RequirementActivtiy.class);
        this.startActivity(intent1);
    }

    //----------------------------------------------------------------
    //点击查看更多酒店列表
    @OnClick(R.id.ll_hotel_head)
    public void showMoreHotel(View view)
    {
        //address   scenic_spot_code   end_date  min_price   keyword   max_price   start_date   order_by  star_level  order_type
        mHotelParams = new String[]{scenic_city, scenic_spot_code};
        Intent intent2 = new Intent();
        intent2.putExtra("mHotelParams", mHotelParams);
        intent2.setClass(this, HotelMoreActivity.class);
        this.startActivity(intent2);
    }

    //----------------------------------------------------------------
    //点击查看更多推荐
    @OnClick(R.id.ll_recomen_head)
    public void showMoreRecomen(View view)
    {
        Intent intent3 = new Intent();
        intent3.putExtra("toRecommend", scenic_spot_code);
        intent3.setClass(this, RecommendListActivity.class);
        this.startActivity(intent3);
    }

    //----------------------------------------------------------------
    //点击跳转到预定酒店
    @OnClick(R.id.ll_list_hotel_item)
    public void bookHotel(View view)
    {

    }

    //点击收藏按钮，收藏景点数据  scenic_spot_code---存到id
    public void onCollect(View view)
    {
        //判断是否已经收藏
        boolean simpleData = jingDbHelper.isSimpleData(
                JingDbTable.JingControll.COLUMN_ID + "=?",
                new String[]{scenic_spot_code});

        if (simpleData)
        {
            Toast.makeText(ScenicDetailActivity.this, "数据已经存在，不用再收藏了", Toast.LENGTH_SHORT).show();

        }
        else
        {
            //如果不存在，则插入
            ContentValues values = new ContentValues();
            //插入标题
            values.put(JingDbTable.JingControll.COLUMN_TYPE, JingDbTable.JingControll.TYPE_JD);
            values.put(JingDbTable.JingControll.COLUMN_ID, scenic_spot_code);
            values.put(JingDbTable.JingControll.COLUMN_TITLE, saveTitle);

            boolean saveData = new JingDbHelper(this).SaveData(values);
            if (saveData)
            {
                Toast.makeText(this, "收藏成功！！！", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void doBack(View view)
    {
        this.finish();
        this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
    }

    @Override
    public void onBackPressed()
    {
        this.finish();
        this.overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
    }
}
