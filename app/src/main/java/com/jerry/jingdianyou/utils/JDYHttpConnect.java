package com.jerry.jingdianyou.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.jerry.jingdianyou.application.JDYApplication;
import com.jerry.jingdianyou.constant.Urls;

import java.util.Map;
import java.util.Set;

/**
 * Created by Jerry.Zou
 */
public class JDYHttpConnect
{

  public static JDYHttpConnect mUtils = new JDYHttpConnect();
  public HttpUtils mHttpUtils;

  /**
   * 单列
   *
   * @return
   */
  public static JDYHttpConnect getInstance()
  {
    return mUtils;
  }

  /**
   * 初始化 HttpUtils
   */
  public JDYHttpConnect()
  {
    mHttpUtils = JDYApplication.getApp().getmHttpUtils();
  }

  /**
   * 加载首页广告栏
   *
   * @param params
   * @param callBack
   */
  public void getBanner(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.BANNER, params, callBack);
  }

  /**
   * 加载 找人聊聊
   *
   * @param params
   * @param callBack
   */
  public void getChat(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.CHAT, params, callBack);
  }

  /**
   * 加载 首页的在路上
   *
   * @param params
   * @param callBack
   */
  public void getOnWayDetail(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.ONWAYDETAIL, params, callBack);
  }

  /**
   * 加载 搜索
   *
   * @param params
   * @param callBack
   */
  public void getSearch(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.SEARCH, params, callBack);
  }

  /**
   * 加载 城市列表
   *
   * @param params
   * @param callBack
   */
  public void getCity(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.CITY, params, callBack);
  }

  /**
   * 加载 约你数据
   *
   * @param params
   * @param callBack
   */
  public void getRendezous(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.RENDEZVOUS, params, callBack);
  }

  /**
   * 加载 在路上
   *
   * @param params
   * @param callBack
   */
  public void getOnway(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.ONWAY, params, callBack);
  }

  /**
   * 加载 吐槽
   *
   * @param params
   * @param callBack
   */
  public void getTuCao(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.TUCAO, params, callBack);
  }

  /**
   * 加载 身边的人
   *
   * @param params
   * @param callBack
   */
  public void getNearByPerson(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.NEARBYPERSON, params, callBack);
  }

  /**
   * 加载 身边的景
   *
   * @param params
   * @param callBack
   */
  public void getNearByScene(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.NEARBYSCENE, params, callBack);
  }

  /**
   * 请求加载景区数据
   */
  public void getScenicList(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.SCENIC_LIST, params, callBack);
  }

  /**
   * 加载景区详细信息
   */
  public void getScenicDetail(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.SCENIC_DETAIL, params, callBack);
  }

  /**
   * 加载景区简介的webview
   */
  public void getScenicDescripWebview(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.SCENIC_DESCRIPTION_WEBVIEW, params, callBack);
  }

  /**
   * 获取酒店列表信息
   */
  public void getHotels(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.SHOW_HOTEL, params, callBack);
  }

  /**
   * 加载推荐景区的详情
   */
  public void getRecommend(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.SHOW_RECOMMEND, params, callBack);
  }

  /**
   * 加载约游广告栏
   *
   * @param params
   * @param callBack
   */
  public void getAdvert(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.Advert, params, callBack);
  }

  /**
   * 攻略首页
   *
   * @param params
   * @param callBack
   */
  public void getStrategy(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.STRATEGY, params, callBack);
  }

  /**
   * 攻略详情
   *
   * @param params
   * @param callBack
   */
  public void getStrategyDetail(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.STRATEGYDETAIL, params, callBack);
  }

  /**
   * 约游详情
   *
   * @param params
   * @param callBack
   */
  public void getRendeDetail(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.RENDEDETAIL, params, callBack);
  }

  /**
   * 约游报名列表
   *
   * @param params
   * @param callBack
   */
  public void getRendezvousDtlList(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.RENdEZVOUSDTLLIS, params, callBack);
  }

  /**
   * 约游详情
   *
   * @param params
   * @param callBack
   */
  public void getCommentList(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.COMMENTLIST, params, callBack);
  }

  /**
   * 攻略详情查看更多
   *
   * @param params
   * @param callBack
   */
  public void getStrateDetailMore(Map<String, Object> params, DataCallBack callBack)
  {
    doPost(Urls.STRATEGYLOOKMORE, params, callBack);
  }

  /**
   * POST 请求数据
   */
  private void doPost(String requestUrl, Map<String, Object> params, final DataCallBack callBack)
  {

    RequestParams requestParams = new RequestParams();

    if (params != null)
    {

      Set<Map.Entry<String, Object>> set = params.entrySet();

      for (Map.Entry<String, Object> entry : set)
      {
        if (entry.getValue() != null && !entry.getValue().equals(""))
        {
          requestParams.addBodyParameter(
              entry.getKey(), String.valueOf(entry.getValue()));
        }
      }
    }

    mHttpUtils.send(
        HttpRequest.HttpMethod.POST,
        requestUrl,
        requestParams,
        new RequestCallBack<String>()
        {
          @Override
          public void onSuccess(ResponseInfo<String> responseInfo)
          {
            callBack.onSuccess(responseInfo.result);
          }

          @Override
          public void onFailure(HttpException error, String msg)
          {
            callBack.onFailure(error.getMessage());
          }
        }
    );

  }
}
