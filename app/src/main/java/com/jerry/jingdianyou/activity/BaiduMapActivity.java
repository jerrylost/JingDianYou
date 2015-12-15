package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.jerry.jingdianyou.R;

/**
 * Created by  Jerry.Zou
 */

public class BaiduMapActivity extends BaseActivity
{
  private BaiduMap mBaiduMap;
  private MapView mMapView;
  private String position_x;
  private String position_y;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.baidu_map);
    mMapView = (MapView) findViewById(R.id.bmapView);
    mBaiduMap = mMapView.getMap();

    Intent intent = getIntent();
    position_x = intent.getStringExtra("position_x");
    position_y = intent.getStringExtra("position_y");

    //显示覆盖物
    showCover();
  }

  /**
   * 显示覆盖物
   */
  private void showCover()
  {
    //定义Maker坐标点
    LatLng point = new LatLng(Double.parseDouble(position_y), Double.parseDouble(position_x));
    //LatLng point=new LatLng(34.16,108.54);
    //构建Marker图标
    BitmapDescriptor bitmap = BitmapDescriptorFactory
        .fromResource(R.mipmap.icon_gcoding);
    //构建MarkerOption，用于在地图上添加Marker
    OverlayOptions option = new MarkerOptions()
        .position(point)
        .icon(bitmap);

    //在地图上添加Marker，并显示
    mBaiduMap.addOverlay(option);
    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(point);
    mBaiduMap.setMapStatus(mapStatusUpdate);
  }


}
