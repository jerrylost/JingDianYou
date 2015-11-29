package com.jerry.jingdianyou.utils;

/**
 * Created by Jerry.Zou
 */
public interface DataCallBack
{
  void onSuccess(String response);

  void onFailure(String error);
}
