package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class AdvertList
{

  private String errMsg;
  private String resultStatus;
  private List<Data> data;

  public void setErrMsg(String errMsg)
  {
    this.errMsg = errMsg;
  }

  public void setResultStatus(String resultStatus)
  {
    this.resultStatus = resultStatus;
  }

  public void setData(List<Data> data)
  {
    this.data = data;
  }

  public String getErrMsg()
  {
    return errMsg;
  }

  public String getResultStatus()
  {
    return resultStatus;
  }

  public List<Data> getData()
  {
    return data;
  }

  public static class Data
  {

    private String advert_pic;
    private String action_type;
    private String action;
    private String adv_content;
    private String adv_title;
    private String adv_position;

    public void setAdvert_pic(String advert_pic)
    {
      this.advert_pic = advert_pic;
    }

    public void setAction_type(String action_type)
    {
      this.action_type = action_type;
    }

    public void setAction(String action)
    {
      this.action = action;
    }

    public void setAdv_content(String adv_content)
    {
      this.adv_content = adv_content;
    }

    public void setAdv_title(String adv_title)
    {
      this.adv_title = adv_title;
    }

    public void setAdv_position(String adv_position)
    {
      this.adv_position = adv_position;
    }

    public String getAdvert_pic()
    {
      return advert_pic;
    }

    public String getAction_type()
    {
      return action_type;
    }

    public String getAction()
    {
      return action;
    }

    public String getAdv_content()
    {
      return adv_content;
    }

    public String getAdv_title()
    {
      return adv_title;
    }

    public String getAdv_position()
    {
      return adv_position;
    }
  }
}
