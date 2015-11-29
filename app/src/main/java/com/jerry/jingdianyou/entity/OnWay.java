package com.jerry.jingdianyou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class OnWay
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

  public static class Data implements Serializable
  {

    private String sex;
    private String onway_id;
    private String praise_amount;
    private String is_praise;
    private String photo;
    private String member_id;
    private String message_content;
    private String photos;
    private String comment_amount;
    private String is_collect;
    private String create_date;
    private String age;
    private String praise_names;
    private String member_name;
    private String position_address;

    public void setSex(String sex)
    {
      this.sex = sex;
    }

    public void setOnway_id(String onway_id)
    {
      this.onway_id = onway_id;
    }

    public void setPraise_amount(String praise_amount)
    {
      this.praise_amount = praise_amount;
    }

    public void setIs_praise(String is_praise)
    {
      this.is_praise = is_praise;
    }

    public void setPhoto(String photo)
    {
      this.photo = photo;
    }

    public void setMember_id(String member_id)
    {
      this.member_id = member_id;
    }

    public void setMessage_content(String message_content)
    {
      this.message_content = message_content;
    }

    public void setPhotos(String photos)
    {
      this.photos = photos;
    }

    public void setComment_amount(String comment_amount)
    {
      this.comment_amount = comment_amount;
    }

    public void setIs_collect(String is_collect)
    {
      this.is_collect = is_collect;
    }

    public void setCreate_date(String create_date)
    {
      this.create_date = create_date;
    }

    public void setAge(String age)
    {
      this.age = age;
    }

    public void setPraise_names(String praise_names)
    {
      this.praise_names = praise_names;
    }

    public void setMember_name(String member_name)
    {
      this.member_name = member_name;
    }

    public void setPosition_address(String position_address)
    {
      this.position_address = position_address;
    }

    public String getSex()
    {
      return sex;
    }

    public String getOnway_id()
    {
      return onway_id;
    }

    public String getPraise_amount()
    {
      return praise_amount;
    }

    public String getIs_praise()
    {
      return is_praise;
    }

    public String getPhoto()
    {
      return photo;
    }

    public String getMember_id()
    {
      return member_id;
    }

    public String getMessage_content()
    {
      return message_content;
    }

    public String getPhotos()
    {
      return photos;
    }

    public String getComment_amount()
    {
      return comment_amount;
    }

    public String getIs_collect()
    {
      return is_collect;
    }

    public String getCreate_date()
    {
      return create_date;
    }

    public String getAge()
    {
      return age;
    }

    public String getPraise_names()
    {
      return praise_names;
    }

    public String getMember_name()
    {
      return member_name;
    }

    public String getPosition_address()
    {
      return position_address;
    }
  }
}
