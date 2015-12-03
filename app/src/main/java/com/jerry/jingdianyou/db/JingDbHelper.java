package com.jerry.jingdianyou.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by  Jerry.Zou
 */
public class JingDbHelper extends SQLiteOpenHelper
{
  public static final String DATABASENAME = "jingdianyou.db";
  public static final int DATABASEVERSION = 1;

  public JingDbHelper(Context context)
  {
    super(context, DATABASENAME, null, DATABASEVERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db)
  {
    //建立数据库
    db.execSQL(JingDbTable.JingControll.CREAT_DB);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {

  }

  //添加数据进入数据库
  public boolean SaveData(ContentValues values)
  {
    SQLiteDatabase db = getWritableDatabase();
    //把数据添加进入数据库
    long insert = db.insert(JingDbTable.JingControll.NAME, null, values);
    //关闭数据库
    db.close();
    //判断是否添加成功
    return insert > 0;

  }

  //判断数据库中是否有相同数据
  public boolean isSimpleData(String where, String[] args)
  {
    boolean flag = false;
    SQLiteDatabase db = getWritableDatabase();
    //遍历查询
    Cursor cursor = db.query(JingDbTable.JingControll.NAME, null, where, args, null, null, null);
    cursor.moveToNext();
    //证明有相同的数据返回true
    if (cursor.getCount() > 0)
    {
      flag = true;
    }
    else
    {
      flag = false;
    }
    //关闭数据库与cursor
    cursor.close();
    db.close();

    return flag;
  }




}
