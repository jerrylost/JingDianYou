package com.jerry.jingdianyou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class Home
{

  private RendezvousData rendezvous_data;
  private String errMsg;
  private OnwayData onway_data;
  private String resultStatus;
  private List<AdvertLinkData> advert_link_data;
  private List<GuidesData> guides_data;
  private List<TopThemeData> top_theme_data;

  public void setRendezvous_data(RendezvousData rendezvous_data)
  {
    this.rendezvous_data = rendezvous_data;
  }

  public void setErrMsg(String errMsg)
  {
    this.errMsg = errMsg;
  }

  public void setOnway_data(OnwayData onway_data)
  {
    this.onway_data = onway_data;
  }

  public void setResultStatus(String resultStatus)
  {
    this.resultStatus = resultStatus;
  }

  public void setAdvert_link_data(List<AdvertLinkData> advert_link_data)
  {
    this.advert_link_data = advert_link_data;
  }

  public void setGuides_data(List<GuidesData> guides_data)
  {
    this.guides_data = guides_data;
  }

  public void setTop_theme_data(List<TopThemeData> top_theme_data)
  {
    this.top_theme_data = top_theme_data;
  }

  public RendezvousData getRendezvous_data()
  {
    return rendezvous_data;
  }

  public String getErrMsg()
  {
    return errMsg;
  }

  public OnwayData getOnway_data()
  {
    return onway_data;
  }

  public String getResultStatus()
  {
    return resultStatus;
  }

  public List<AdvertLinkData> getAdvert_link_data()
  {
    return advert_link_data;
  }

  public List<GuidesData> getGuides_data()
  {
    return guides_data;
  }

  public List<TopThemeData> getTop_theme_data()
  {
    return top_theme_data;
  }

  public static class RendezvousData
  {

    private String member_photo;
    private String signup_amount;
    private String rendezvous_id;
    private String rendezvous_content;
    private String member_name;
    private String photo;
    private String destination;
    private String member_id;

    public void setMember_photo(String member_photo)
    {
      this.member_photo = member_photo;
    }

    public void setSignup_amount(String signup_amount)
    {
      this.signup_amount = signup_amount;
    }

    public void setRendezvous_id(String rendezvous_id)
    {
      this.rendezvous_id = rendezvous_id;
    }

    public void setRendezvous_content(String rendezvous_content)
    {
      this.rendezvous_content = rendezvous_content;
    }

    public void setMember_name(String member_name)
    {
      this.member_name = member_name;
    }

    public void setPhoto(String photo)
    {
      this.photo = photo;
    }

    public void setDestination(String destination)
    {
      this.destination = destination;
    }

    public void setMember_id(String member_id)
    {
      this.member_id = member_id;
    }

    public String getMember_photo()
    {
      return member_photo;
    }

    public String getSignup_amount()
    {
      return signup_amount;
    }

    public String getRendezvous_id()
    {
      return rendezvous_id;
    }

    public String getRendezvous_content()
    {
      return rendezvous_content;
    }

    public String getMember_name()
    {
      return member_name;
    }

    public String getPhoto()
    {
      return photo;
    }

    public String getDestination()
    {
      return destination;
    }

    public String getMember_id()
    {
      return member_id;
    }
  }

  public static class OnwayData implements Serializable
  {

    private String member_photo;
    private String create_date;
    private String onway_id;
    private String create_place;
    private String member_name;
    private String photo;
    private String message_content;
    private String member_id;

    public void setMember_photo(String member_photo)
    {
      this.member_photo = member_photo;
    }

    public void setCreate_date(String create_date)
    {
      this.create_date = create_date;
    }

    public void setOnway_id(String onway_id)
    {
      this.onway_id = onway_id;
    }

    public void setCreate_place(String create_place)
    {
      this.create_place = create_place;
    }

    public void setMember_name(String member_name)
    {
      this.member_name = member_name;
    }

    public void setPhoto(String photo)
    {
      this.photo = photo;
    }

    public void setMessage_content(String message_content)
    {
      this.message_content = message_content;
    }

    public void setMember_id(String member_id)
    {
      this.member_id = member_id;
    }

    public String getMember_photo()
    {
      return member_photo;
    }

    public String getCreate_date()
    {
      return create_date;
    }

    public String getOnway_id()
    {
      return onway_id;
    }

    public String getCreate_place()
    {
      return create_place;
    }

    public String getMember_name()
    {
      return member_name;
    }

    public String getPhoto()
    {
      return photo;
    }

    public String getMessage_content()
    {
      return message_content;
    }

    public String getMember_id()
    {
      return member_id;
    }
  }

  public static class AdvertLinkData
  {

    private String advert_pic;
    private String action_type;
    private String action;
    private String district_id;
    private String advert_title;

    public void setAdvert_pic(String advert_pic)
    {
      this.advert_pic = advert_pic;
    }

    public void setAction_type(String action_type)
    {
      this.action_type = action_type;
    }

    public void setAction(String action)
    {
      this.action = action;
    }

    public void setDistrict_id(String district_id)
    {
      this.district_id = district_id;
    }

    public void setAdvert_title(String advert_title)
    {
      this.advert_title = advert_title;
    }

    public String getAdvert_pic()
    {
      return advert_pic;
    }

    public String getAction_type()
    {
      return action_type;
    }

    public String getAction()
    {
      return action;
    }

    public String getDistrict_id()
    {
      return district_id;
    }

    public String getAdvert_title()
    {
      return advert_title;
    }
  }

  public static class GuidesData
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

  public static class TopThemeData
  {

    private String action_type;
    private String action;
    private String district_id;
    private String theme_pic;
    private String theme_name;

    public void setAction_type(String action_type)
    {
      this.action_type = action_type;
    }

    public void setAction(String action)
    {
      this.action = action;
    }

    public void setDistrict_id(String district_id)
    {
      this.district_id = district_id;
    }

    public void setTheme_pic(String theme_pic)
    {
      this.theme_pic = theme_pic;
    }

    public void setTheme_name(String theme_name)
    {
      this.theme_name = theme_name;
    }

    public String getAction_type()
    {
      return action_type;
    }

    public String getAction()
    {
      return action;
    }

    public String getDistrict_id()
    {
      return district_id;
    }

    public String getTheme_pic()
    {
      return theme_pic;
    }

    public String getTheme_name()
    {
      return theme_name;
    }
  }
}
