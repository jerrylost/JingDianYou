package com.jerry.jingdianyou.entity;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class Rendezvous
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
    private String is_join;
    private String sex;
    private String signup_amount;
    private String rendezvous_id;
    private String praise_amount;
    private String is_praise;
    private String photo;
    private String member_id;
    private String destination;
    private String comment_amount;
    private String is_collect;
    private String age;
    private String signup_photos;
    private String rendezvous_content;
    private String member_name;
    private String is_outdate;
    private String start_date;

    public void setIs_join(String is_join)
    {
      this.is_join = is_join;
    }

    public void setSex(String sex)
    {
      this.sex = sex;
    }

    public void setSignup_amount(String signup_amount)
    {
      this.signup_amount = signup_amount;
    }

    public void setRendezvous_id(String rendezvous_id)
    {
      this.rendezvous_id = rendezvous_id;
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

    public void setDestination(String destination)
    {
      this.destination = destination;
    }

    public void setComment_amount(String comment_amount)
    {
      this.comment_amount = comment_amount;
    }

    public void setIs_collect(String is_collect)
    {
      this.is_collect = is_collect;
    }

    public void setAge(String age)
    {
      this.age = age;
    }

    public void setSignup_photos(String signup_photos)
    {
      this.signup_photos = signup_photos;
    }

    public void setRendezvous_content(String rendezvous_content)
    {
      this.rendezvous_content = rendezvous_content;
    }

    public void setMember_name(String member_name)
    {
      this.member_name = member_name;
    }

    public void setIs_outdate(String is_outdate)
    {
      this.is_outdate = is_outdate;
    }

    public void setStart_date(String start_date)
    {
      this.start_date = start_date;
    }

    public String getIs_join()
    {
      return is_join;
    }

    public String getSex()
    {
      return sex;
    }

    public String getSignup_amount()
    {
      return signup_amount;
    }

    public String getRendezvous_id()
    {
      return rendezvous_id;
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

    public String getDestination()
    {
      return destination;
    }

    public String getComment_amount()
    {
      return comment_amount;
    }

    public String getIs_collect()
    {
      return is_collect;
    }

    public String getAge()
    {
      return age;
    }

    public String getSignup_photos()
    {
      return signup_photos;
    }

    public String getRendezvous_content()
    {
      return rendezvous_content;
    }

    public String getMember_name()
    {
      return member_name;
    }

    public String getIs_outdate()
    {
      return is_outdate;
    }

    public String getStart_date()
    {
      return start_date;
    }
  }
}
