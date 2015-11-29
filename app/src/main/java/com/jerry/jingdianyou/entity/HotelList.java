package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class HotelList
{


  /**
   * errMsg : 酒店查询成功!
   * data : [{"city_name":"北京","hotel_id":"60101658","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000coQu.jpg","star_level":"4","is_commision":"0","hotel_type":"1","distance":"504","price":"448","position_y":"39.999897000","payment_mode":"1","hotel_csname":"北京鸿炜亿家连锁酒店(鸟巢店原亚运村店)","position_x":"116.433089000","hotel_cname":"北京鸿炜亿家连锁酒店(鸟巢店原亚运村店)"},{"city_name":"北京","hotel_id":"40101755","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000coVg.jpg","star_level":"3","is_commision":"0","hotel_type":"1","distance":"517","price":"289","position_y":"40.008205000","payment_mode":"1","hotel_csname":"北京瑞德酒店","position_x":"116.434358000","hotel_cname":"北京瑞德酒店"},{"city_name":"北京","hotel_id":"20101690","hotel_picture":"","star_level":"3","is_commision":"0","hotel_type":"1","distance":"798","price":"278","position_y":"40.000706000","payment_mode":"1","hotel_csname":"北京观唐假日酒店","position_x":"116.423093000","hotel_cname":"北京观唐假日酒店"},{"city_name":"北京","hotel_id":"70101916","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000lqVI.jpg","star_level":"4","is_commision":"0","hotel_type":"1","distance":"1172","price":"358","position_y":"39.994079000","payment_mode":"1","hotel_csname":"北京鼎春德酒店","position_x":"116.435039000","hotel_cname":"北京鼎春德酒店"},{"city_name":"北京","hotel_id":"60101575","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000a2RG.jpg","star_level":"3","is_commision":"0","hotel_type":"1","distance":"1265","price":"138","position_y":"39.997382000","payment_mode":"1","hotel_csname":"北京盛旺东润快捷酒店","position_x":"116.419395000","hotel_cname":"北京盛旺东润快捷酒店"},{"city_name":"北京","hotel_id":"50101017","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000cnzo.jpg","star_level":"5","is_commision":"0","hotel_type":"1","distance":"1556","price":"898","position_y":"40.002170000","payment_mode":"1","hotel_csname":"北京名人国际大酒店","position_x":"116.413206000","hotel_cname":"北京名人国际大酒店"},{"city_name":"北京","hotel_id":"10101383","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000colU.jpg","star_level":"3","is_commision":"0","hotel_type":"1","distance":"1786","price":"360","position_y":"39.991661000","payment_mode":"1","hotel_csname":"北京吐哈宾馆","position_x":"116.418207000","hotel_cname":"北京吐哈宾馆"},{"city_name":"北京","hotel_id":"80101735","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000ckvD.jpg","star_level":"4","is_commision":"0","hotel_type":"1","distance":"1791","price":"738","position_y":"40.013102000","payment_mode":"1","hotel_csname":"北京蝶尚精品酒店","position_x":"116.413747000","hotel_cname":"北京蝶尚精品酒店"},{"city_name":"北京","hotel_id":"50101053","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000cjAD.jpg","star_level":"5","is_commision":"0","hotel_type":"1","distance":"1860","price":"1380","position_y":"39.996111000","payment_mode":"1","hotel_csname":"北京五洲皇冠国际酒店","position_x":"116.412173000","hotel_cname":"北京五洲皇冠国际酒店"},{"city_name":"北京","hotel_id":"10101054","hotel_picture":"http://pavo.elongstatic.com/i/Mobile120_120/0000cpFf.jpg","star_level":"5","is_commision":"0","hotel_type":"1","distance":"1895","price":"558","position_y":"39.991253000","payment_mode":"1","hotel_csname":"北京长白山国际酒店(原吉林大厦)","position_x":"116.416836000","hotel_cname":"北京长白山国际酒店(原吉林大厦)"}]
   * resultStatus : 0
   */

  private String errMsg;
  private String resultStatus;
  /**
   * city_name : 北京
   * hotel_id : 60101658
   * hotel_picture : http://pavo.elongstatic.com/i/Mobile120_120/0000coQu.jpg
   * star_level : 4
   * is_commision : 0
   * hotel_type : 1
   * distance : 504
   * price : 448
   * position_y : 39.999897000
   * payment_mode : 1
   * hotel_csname : 北京鸿炜亿家连锁酒店(鸟巢店原亚运村店)
   * position_x : 116.433089000
   * hotel_cname : 北京鸿炜亿家连锁酒店(鸟巢店原亚运村店)
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
    private String city_name;
    private String hotel_id;
    private String hotel_picture;
    private String star_level;
    private String is_commision;
    private String hotel_type;
    private String distance;
    private String price;
    private String position_y;
    private String payment_mode;
    private String hotel_csname;
    private String position_x;
    private String hotel_cname;

    public void setCity_name(String city_name)
    {
      this.city_name = city_name;
    }

    public void setHotel_id(String hotel_id)
    {
      this.hotel_id = hotel_id;
    }

    public void setHotel_picture(String hotel_picture)
    {
      this.hotel_picture = hotel_picture;
    }

    public void setStar_level(String star_level)
    {
      this.star_level = star_level;
    }

    public void setIs_commision(String is_commision)
    {
      this.is_commision = is_commision;
    }

    public void setHotel_type(String hotel_type)
    {
      this.hotel_type = hotel_type;
    }

    public void setDistance(String distance)
    {
      this.distance = distance;
    }

    public void setPrice(String price)
    {
      this.price = price;
    }

    public void setPosition_y(String position_y)
    {
      this.position_y = position_y;
    }

    public void setPayment_mode(String payment_mode)
    {
      this.payment_mode = payment_mode;
    }

    public void setHotel_csname(String hotel_csname)
    {
      this.hotel_csname = hotel_csname;
    }

    public void setPosition_x(String position_x)
    {
      this.position_x = position_x;
    }

    public void setHotel_cname(String hotel_cname)
    {
      this.hotel_cname = hotel_cname;
    }

    public String getCity_name()
    {
      return city_name;
    }

    public String getHotel_id()
    {
      return hotel_id;
    }

    public String getHotel_picture()
    {
      return hotel_picture;
    }

    public String getStar_level()
    {
      return star_level;
    }

    public String getIs_commision()
    {
      return is_commision;
    }

    public String getHotel_type()
    {
      return hotel_type;
    }

    public String getDistance()
    {
      return distance;
    }

    public String getPrice()
    {
      return price;
    }

    public String getPosition_y()
    {
      return position_y;
    }

    public String getPayment_mode()
    {
      return payment_mode;
    }

    public String getHotel_csname()
    {
      return hotel_csname;
    }

    public String getPosition_x()
    {
      return position_x;
    }

    public String getHotel_cname()
    {
      return hotel_cname;
    }
  }
}
