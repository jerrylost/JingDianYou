package com.jerry.jingdianyou.entity;

/**
 * Created by Jerry.Zou
 */

import java.util.List;

/**
 * 评价列表
 */
public class CommentList
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
    private String message_date;
    private String reply_amount;
    private String praise_amount;
    private String message_id;
    private String member_name;
    private String is_praise;
    private String photo;
    private String message_content;
    private String member_sex;
    private String member_id;
    private List<DataA> data;

    public void setMessage_date(String message_date)
    {
      this.message_date = message_date;
    }

    public void setReply_amount(String reply_amount)
    {
      this.reply_amount = reply_amount;
    }

    public void setPraise_amount(String praise_amount)
    {
      this.praise_amount = praise_amount;
    }

    public void setMessage_id(String message_id)
    {
      this.message_id = message_id;
    }

    public void setMember_name(String member_name)
    {
      this.member_name = member_name;
    }

    public void setIs_praise(String is_praise)
    {
      this.is_praise = is_praise;
    }

    public void setPhoto(String photo)
    {
      this.photo = photo;
    }

    public void setMessage_content(String message_content)
    {
      this.message_content = message_content;
    }

    public void setMember_sex(String member_sex)
    {
      this.member_sex = member_sex;
    }

    public void setMember_id(String member_id)
    {
      this.member_id = member_id;
    }

    public void setData(List<DataA> data)
    {
      this.data = data;
    }

    public String getMessage_date()
    {
      return message_date;
    }

    public String getReply_amount()
    {
      return reply_amount;
    }

    public String getPraise_amount()
    {
      return praise_amount;
    }

    public String getMessage_id()
    {
      return message_id;
    }

    public String getMember_name()
    {
      return member_name;
    }

    public String getIs_praise()
    {
      return is_praise;
    }

    public String getPhoto()
    {
      return photo;
    }

    public String getMessage_content()
    {
      return message_content;
    }

    public String getMember_sex()
    {
      return member_sex;
    }

    public String getMember_id()
    {
      return member_id;
    }

    public List<DataA> getData()
    {
      return data;
    }

    public static class DataA
    {
      private String message_date;
      private String message_id;
      private String member_name;
      private String photo;
      private String message_content;
      private String member_id;

      public String getMessage_date()
      {
        return message_date;
      }

      public void setMessage_date(String message_date)
      {
        this.message_date = message_date;
      }

      public String getMessage_id()
      {
        return message_id;
      }

      public void setMessage_id(String message_id)
      {
        this.message_id = message_id;
      }

      public String getMember_name()
      {
        return member_name;
      }

      public void setMember_name(String member_name)
      {
        this.member_name = member_name;
      }

      public String getPhoto()
      {
        return photo;
      }

      public void setPhoto(String photo)
      {
        this.photo = photo;
      }

      public String getMessage_content()
      {
        return message_content;
      }

      public void setMessage_content(String message_content)
      {
        this.message_content = message_content;
      }

      public String getMember_id()
      {
        return member_id;
      }

      public void setMember_id(String member_id)
      {
        this.member_id = member_id;
      }
    }
  }

}
