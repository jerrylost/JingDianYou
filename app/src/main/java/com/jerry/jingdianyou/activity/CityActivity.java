package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.adapter.CityAdapter;
import com.jerry.jingdianyou.entity.CitySort;
import com.jerry.jingdianyou.utils.DataCallBack;
import com.jerry.jingdianyou.utils.JDYHttpConnect;
import com.jerry.jingdianyou.view.SlideBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry.Zou
 */
@ContentView(R.layout.activity_city_select)
public class CityActivity extends Activity
{
  @ViewInject(R.id.lv_city)
  private ListView mLvCity;

  private CityAdapter mAdapter;
  @ViewInject(R.id.slidebar_words)
  private SlideBar mSlideBar;
  @ViewInject(R.id.tv_words_dialog)
  private TextView mDialog;
  private Map<String, Object> params = new HashMap<>();

  private List<CitySort> sortList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ViewUtils.inject(this);

    mSlideBar.setCharDialag(mDialog);
    mSlideBar.setOnSlideBarItemClickListener(new SlideBar.OnSlideBarItemClickListener()
    {
      @Override
      public void onItemClicked(String word)
      {
        int position = mAdapter.getWordPosition(word);
        mLvCity.setSelection(position);
      }
    });

    mAdapter = new CityAdapter(this, sortList);
    mLvCity.setAdapter(mAdapter);

    loadData();
  }

  private void loadData()
  {
    params.put("RequestJson", "{\"pageType\":\"3\",\"inOutsideFlg\":\"0\"}");
    JDYHttpConnect.getInstance().getCity(params, new DataCallBack()
    {
      @Override
      public void onSuccess(String response)
      {
        getData(response);
      }

      @Override
      public void onFailure(String error)
      {

      }
    });
  }

  private void getData(String response)
  {
    try
    {
      JSONObject jsonObject = new JSONObject(response);
      JSONArray jsonArray = jsonObject.getJSONArray("data");
      for (int i = 0; i < jsonArray.length(); i++)
      {
        JSONObject object = jsonArray.getJSONObject(i);
        String cityFirstChar = object.getString("cityFirstChar");
        JSONArray cityList = object.getJSONArray("cityList");
        for (int j = 0; j < cityList.length(); j++)
        {
          CitySort citySort = new CitySort();
          JSONObject cityListJSONObject = cityList.getJSONObject(j);
          String showName = cityListJSONObject.getString("showName");
          String cityCode = cityListJSONObject.getString("cityCode");

          citySort.setLetter(cityFirstChar);
          citySort.setCityId(cityCode);
          citySort.setCityName(showName);

          sortList.add(citySort);
        }
      }

      mAdapter.notifyDataSetChanged();

    }
    catch (JSONException e)
    {
      e.printStackTrace();
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
