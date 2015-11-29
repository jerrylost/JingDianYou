package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class RecommendList
{

  /**
   * errMsg : 查询推荐景点信息!
   * data : [{"car_service":1,"give_integral":"0","scenic_draw":0,"scenic_spot_code":"13_118_JLT","sales_price":"48","product_pic":"http://dimg02.c-ctrip.com/images/tg/315/946/472/68734585bb5b49c4a925821027af9f28.jpg","distance":"14171","level":"4","market_price":"65","address":"福建省三明市泰宁县","scenic_name":"九龙潭","longitude":"117.184997558594","latitude":"26.9841003417969"},{"product_pic":"http://dimg02.c-ctrip.com/images/fd/tg/g2/M08/35/39/Cghzf1Va_WOAahbAAAmFkzBr_M0079_C_422_236.jpg","distance":"334","car_service":1,"market_price":"80","give_integral":"0","address":"福建省三明市闽西北泰宁县境内","scenic_draw":0,"scenic_name":"大金湖","longitude":"117.076782226562","latitude":"26.9048900604248","scenic_spot_code":"13_118_DJH","sales_price":"78"},{"product_pic":"http://dimg02.c-ctrip.com/images/tg/950/424/493/4f788868c12244b4b8a736532a2766dc_C_422_236.jpg","distance":"15863","car_service":1,"market_price":"95","give_integral":"0","address":"福建省三明市闽西北泰宁县境内","scenic_draw":0,"scenic_name":"上清溪","longitude":"117.193542480469","latitude":"26.9984436035156","scenic_spot_code":"13_118_SQX","sales_price":"93"},{"product_pic":"http://dimg02.c-ctrip.com/images/tg/600/974/839/7c46780b1e914db892446b576c272bf6_C_422_236.jpg","distance":"103998","car_service":1,"market_price":"35","give_integral":"0","address":"福建省永安市城北9公里205国道旁。","scenic_draw":0,"scenic_name":"桃源洞","longitude":"117.420356750488","latitude":"26.0201568603516","scenic_spot_code":"13_118_TYD","sales_price":"30"}]
   * resultStatus : 0
   */

  private String errMsg;
  private String resultStatus;
  /**
   * car_service : 1
   * give_integral : 0
   * scenic_draw : 0
   * scenic_spot_code : 13_118_JLT
   * sales_price : 48
   * product_pic : http://dimg02.c-ctrip.com/images/tg/315/946/472/68734585bb5b49c4a925821027af9f28.jpg
   * distance : 14171
   * level : 4
   * market_price : 65
   * address : 福建省三明市泰宁县
   * scenic_name : 九龙潭
   * longitude : 117.184997558594
   * latitude : 26.9841003417969
   */

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
    private int car_service;
    private String give_integral;
    private int scenic_draw;
    private String scenic_spot_code;
    private String sales_price;
    private String product_pic;
    private String distance;
    private String level;
    private String market_price;
    private String address;
    private String scenic_name;
    private String longitude;
    private String latitude;

    public void setCar_service(int car_service)
    {
      this.car_service = car_service;
    }

    public void setGive_integral(String give_integral)
    {
      this.give_integral = give_integral;
    }

    public void setScenic_draw(int scenic_draw)
    {
      this.scenic_draw = scenic_draw;
    }

    public void setScenic_spot_code(String scenic_spot_code)
    {
      this.scenic_spot_code = scenic_spot_code;
    }

    public void setSales_price(String sales_price)
    {
      this.sales_price = sales_price;
    }

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

    public int getCar_service()
    {
      return car_service;
    }

    public String getGive_integral()
    {
      return give_integral;
    }

    public int getScenic_draw()
    {
      return scenic_draw;
    }

    public String getScenic_spot_code()
    {
      return scenic_spot_code;
    }

    public String getSales_price()
    {
      return sales_price;
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
  }
}
