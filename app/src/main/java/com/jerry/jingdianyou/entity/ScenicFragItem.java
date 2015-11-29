package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class ScenicFragItem
{

  private String errMsg;
  private String resultStatus;

  private List<AdvertLinkData> advert_link_data;

  private List<Data> data;

  public void setErrMsg(String errMsg)
  {
    this.errMsg = errMsg;
  }

  public void setResultStatus(String resultStatus)
  {
    this.resultStatus = resultStatus;
  }

  public void setAdvert_link_data(List<AdvertLinkData> advert_link_data)
  {
    this.advert_link_data = advert_link_data;
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

  public List<AdvertLinkData> getAdvert_link_data()
  {
    return advert_link_data;
  }

  public List<Data> getData()
  {
    return data;
  }

  public static class AdvertLinkData
  {
    private String advert_pic;
    private String action_type;
    private String action;
    private String adv_content;
    private String adv_title;

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
  }

  public static class Data
  {
    //图片
    private String product_pic;
    //所在城市
    private String city_name;
    //等级
    private String level;
    //市场价格，原价
    private String market_price;
    //返券
    private String give_integral;
    //地址
    private String address;
    //景区名称
    private String scenic_name;
    //风景区代码
    private String scenic_spot_code;
    //售价，现价
    private String sales_price;

    public void setProduct_pic(String product_pic)
    {
      this.product_pic = product_pic;
    }

    public void setCity_name(String city_name)
    {
      this.city_name = city_name;
    }

    public void setLevel(String level)
    {
      this.level = level;
    }

    public void setMarket_price(String market_price)
    {
      this.market_price = market_price;
    }

    public void setGive_integral(String give_integral)
    {
      this.give_integral = give_integral;
    }

    public void setAddress(String address)
    {
      this.address = address;
    }

    public void setScenic_name(String scenic_name)
    {
      this.scenic_name = scenic_name;
    }

    public void setScenic_spot_code(String scenic_spot_code)
    {
      this.scenic_spot_code = scenic_spot_code;
    }

    public void setSales_price(String sales_price)
    {
      this.sales_price = sales_price;
    }

    public String getProduct_pic()
    {
      return product_pic;
    }

    public String getCity_name()
    {
      return city_name;
    }

    public String getLevel()
    {
      return level;
    }

    public String getMarket_price()
    {
      return market_price;
    }

    public String getGive_integral()
    {
      return give_integral;
    }

    public String getAddress()
    {
      return address;
    }

    public String getScenic_name()
    {
      return scenic_name;
    }

    public String getScenic_spot_code()
    {
      return scenic_spot_code;
    }

    public String getSales_price()
    {
      return sales_price;
    }
  }
}
