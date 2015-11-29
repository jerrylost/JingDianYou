package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class City
{

  private String resultStatus;

  private List<Data> data;

  public void setResultStatus(String resultStatus)
  {
    this.resultStatus = resultStatus;
  }

  public void setData(List<Data> data)
  {
    this.data = data;
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
    private String cityFirstChar;


    private List<CityList> cityList;

    public void setCityFirstChar(String cityFirstChar)
    {
      this.cityFirstChar = cityFirstChar;
    }

    public void setCityList(List<CityList> cityList)
    {
      this.cityList = cityList;
    }

    public String getCityFirstChar()
    {
      return cityFirstChar;
    }

    public List<CityList> getCityList()
    {
      return cityList;
    }

    public static class CityList
    {
      private String showName;
      private String cityCode;

      public void setShowName(String showName)
      {
        this.showName = showName;
      }

      public void setCityCode(String cityCode)
      {
        this.cityCode = cityCode;
      }

      public String getShowName()
      {
        return showName;
      }

      public String getCityCode()
      {
        return cityCode;
      }
    }
  }
}
