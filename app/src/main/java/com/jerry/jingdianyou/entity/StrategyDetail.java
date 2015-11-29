package com.jerry.jingdianyou.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class StrategyDetail
{
  private String errMsg;
  @SerializedName("data")
  private Data mydata;
  private String resultStatus;

  public void setErrMsg(String errMsg)
  {
    this.errMsg = errMsg;
  }

  public void setMydata(Data mydata)
  {
    this.mydata = mydata;
  }

  public void setResultStatus(String resultStatus)
  {
    this.resultStatus = resultStatus;
  }

  public String getErrMsg()
  {
    return errMsg;
  }

  public Data getMydata()
  {
    return mydata;
  }

  public String getResultStatus()
  {
    return resultStatus;
  }

  public static class Data
  {
    private String is_collect;
    private List<GuidesInfoDataEntity> guides_info_data;

    public void setIs_collect(String is_collect)
    {
      this.is_collect = is_collect;
    }

    public void setGuides_info_data(List<GuidesInfoDataEntity> guides_info_data)
    {
      this.guides_info_data = guides_info_data;
    }

    public String getIs_collect()
    {
      return is_collect;
    }

    public List<GuidesInfoDataEntity> getGuides_info_data()
    {
      return guides_info_data;
    }

    public static class GuidesInfoDataEntity
    {
      private String trip_city_name;
      private String trip_day;
      private List<DataEntity> data;

      public void setTrip_city_name(String trip_city_name)
      {
        this.trip_city_name = trip_city_name;
      }

      public void setTrip_day(String trip_day)
      {
        this.trip_day = trip_day;
      }

      public void setData(List<DataEntity> data)
      {
        this.data = data;
      }

      public String getTrip_city_name()
      {
        return trip_city_name;
      }

      public String getTrip_day()
      {
        return trip_day;
      }

      public List<DataEntity> getData()
      {
        return data;
      }

      public static class DataEntity
      {
        private String guides_line_order;
        private String guides_title;
        private String item_outlined;
        private String position_y;
        private String position_x;
        private String sub_order;
        private String city_code;
        private String item_description;
        private String scenic_spot_code;
        private String item_picture;
        private String item_type;

        public void setGuides_line_order(String guides_line_order)
        {
          this.guides_line_order = guides_line_order;
        }

        public void setGuides_title(String guides_title)
        {
          this.guides_title = guides_title;
        }

        public void setItem_outlined(String item_outlined)
        {
          this.item_outlined = item_outlined;
        }

        public void setPosition_y(String position_y)
        {
          this.position_y = position_y;
        }

        public void setPosition_x(String position_x)
        {
          this.position_x = position_x;
        }

        public void setSub_order(String sub_order)
        {
          this.sub_order = sub_order;
        }

        public void setCity_code(String city_code)
        {
          this.city_code = city_code;
        }

        public void setItem_description(String item_description)
        {
          this.item_description = item_description;
        }

        public void setScenic_spot_code(String scenic_spot_code)
        {
          this.scenic_spot_code = scenic_spot_code;
        }

        public void setItem_picture(String item_picture)
        {
          this.item_picture = item_picture;
        }

        public void setItem_type(String item_type)
        {
          this.item_type = item_type;
        }

        public String getGuides_line_order()
        {
          return guides_line_order;
        }

        public String getGuides_title()
        {
          return guides_title;
        }

        public String getItem_outlined()
        {
          return item_outlined;
        }

        public String getPosition_y()
        {
          return position_y;
        }

        public String getPosition_x()
        {
          return position_x;
        }

        public String getSub_order()
        {
          return sub_order;
        }

        public String getCity_code()
        {
          return city_code;
        }

        public String getItem_description()
        {
          return item_description;
        }

        public String getScenic_spot_code()
        {
          return scenic_spot_code;
        }

        public String getItem_picture()
        {
          return item_picture;
        }

        public String getItem_type()
        {
          return item_type;
        }
      }
    }
  }
}
