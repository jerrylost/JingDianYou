package com.jerry.jingdianyou.entity;

/**
 * Created by Jerry.Zou
 */

import java.io.Serializable;
import java.util.List;

/**
 * 报名列表
 */
public class RendezvousDtlList implements Serializable
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
    private String age;
    private String member_name;
    private String photo;
    private String signature;
    private String member_id;

    public void setSex(String sex)
    {
      this.sex = sex;
    }

    public void setAge(String age)
    {
      this.age = age;
    }

    public void setMember_name(String member_name)
    {
      this.member_name = member_name;
    }

    public void setPhoto(String photo)
    {
      this.photo = photo;
    }

    public void setSignature(String signature)
    {
      this.signature = signature;
    }

    public void setMember_id(String member_id)
    {
      this.member_id = member_id;
    }

    public String getSex()
    {
      return sex;
    }

    public String getAge()
    {
      return age;
    }

    public String getMember_name()
    {
      return member_name;
    }

    public String getPhoto()
    {
      return photo;
    }

    public String getSignature()
    {
      return signature;
    }

    public String getMember_id()
    {
      return member_id;
    }
  }
}
