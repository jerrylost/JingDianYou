package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class NearScene
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

    private String product_pic;
    private String distance;
    private String level;
    private String market_price;
    private String give_integral;
    private String address;
    private String scenic_name;
    private String longitude;
    private String latitude;
    private String scenic_spot_code;
    private String sales_price;

    public void setProduct_pic(String product_pic)
    {
      this.product_pic = product_pic;
    }

    public void setDistance(String distance)
    {
      this.distance = distance;
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

    public void setLongitude(String longitude)
    {
      this.longitude = longitude;
    }

    public void setLatitude(String latitude)
    {
      this.latitude = latitude;
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

    public String getDistance()
    {
      return distance;
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

    public String getLongitude()
    {
      return longitude;
    }

    public String getLatitude()
    {
      return latitude;
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
