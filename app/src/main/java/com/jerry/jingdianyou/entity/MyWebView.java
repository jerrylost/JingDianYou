package com.jerry.jingdianyou.entity;

/**
 * Created by Jerry.Zou
 */
public class MyWebView
{


  private String errMsg;
  private String data;
  private String resultStatus;

  public void setErrMsg(String errMsg)
  {
    this.errMsg = errMsg;
  }

  public void setData(String data)
  {
    this.data = data;
  }

  public void setResultStatus(String resultStatus)
  {
    this.resultStatus = resultStatus;
  }

  public String getErrMsg()
  {
    return errMsg;
  }

  public String getData()
  {
    return data;
  }

  public String getResultStatus()
  {
    return resultStatus;
  }
}
