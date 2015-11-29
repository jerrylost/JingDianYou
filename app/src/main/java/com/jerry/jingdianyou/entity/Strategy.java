package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class Strategy
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
    private String guides_name;
    private String collect_amount;
    private String trip_days;
    private String guides_pic;
    private String is_user;
    private String praise_amount;
    private String guides_id;
    private String is_praise;
    private String upd_date;
    private String guides_description;
    private String comment_amount;
    private String is_collect;
    private String is_new;
    private String member_photo;
    private String is_hot;
    private String member_name;
    private String recommend_status;

    public void setGuides_name(String guides_name)
    {
      this.guides_name = guides_name;
    }

    public void setCollect_amount(String collect_amount)
    {
      this.collect_amount = collect_amount;
    }

    public void setTrip_days(String trip_days)
    {
      this.trip_days = trip_days;
    }

    public void setGuides_pic(String guides_pic)
    {
      this.guides_pic = guides_pic;
    }

    public void setIs_user(String is_user)
    {
      this.is_user = is_user;
    }

    public void setPraise_amount(String praise_amount)
    {
      this.praise_amount = praise_amount;
    }

    public void setGuides_id(String guides_id)
    {
      this.guides_id = guides_id;
    }

    public void setIs_praise(String is_praise)
    {
      this.is_praise = is_praise;
    }

    public void setUpd_date(String upd_date)
    {
      this.upd_date = upd_date;
    }

    public void setGuides_description(String guides_description)
    {
      this.guides_description = guides_description;
    }

    public void setComment_amount(String comment_amount)
    {
      this.comment_amount = comment_amount;
    }

    public void setIs_collect(String is_collect)
    {
      this.is_collect = is_collect;
    }

    public void setIs_new(String is_new)
    {
      this.is_new = is_new;
    }

    public void setMember_photo(String member_photo)
    {
      this.member_photo = member_photo;
    }

    public void setIs_hot(String is_hot)
    {
      this.is_hot = is_hot;
    }

    public void setMember_name(String member_name)
    {
      this.member_name = member_name;
    }

    public void setRecommend_status(String recommend_status)
    {
      this.recommend_status = recommend_status;
    }

    public String getGuides_name()
    {
      return guides_name;
    }

    public String getCollect_amount()
    {
      return collect_amount;
    }

    public String getTrip_days()
    {
      return trip_days;
    }

    public String getGuides_pic()
    {
      return guides_pic;
    }

    public String getIs_user()
    {
      return is_user;
    }

    public String getPraise_amount()
    {
      return praise_amount;
    }

    public String getGuides_id()
    {
      return guides_id;
    }

    public String getIs_praise()
    {
      return is_praise;
    }

    public String getUpd_date()
    {
      return upd_date;
    }

    public String getGuides_description()
    {
      return guides_description;
    }

    public String getComment_amount()
    {
      return comment_amount;
    }

    public String getIs_collect()
    {
      return is_collect;
    }

    public String getIs_new()
    {
      return is_new;
    }

    public String getMember_photo()
    {
      return member_photo;
    }

    public String getIs_hot()
    {
      return is_hot;
    }

    public String getMember_name()
    {
      return member_name;
    }

    public String getRecommend_status()
    {
      return recommend_status;
    }
  }
}
