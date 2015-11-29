package com.jerry.jingdianyou.db;

/**
 * Created by Administrator on 2015/11/17.
 */
public class JingDbTable
{
  public static class JingControll
  {
    //数据库名称
    public static final String NAME = "t_jing_collect";
    //列表名称
    public static final String COLUMN_ID = "guides_id";
    public static final String COLUMN_TITLE = "guides_title";

    //创建数据库
    public static final String CREAT_DB = "create table " + NAME +
        "(" +
        "_id integer primary key autoincrement,"
        + COLUMN_ID + " integer,"
        + COLUMN_TITLE + " text"
        + ")";
  }
}
