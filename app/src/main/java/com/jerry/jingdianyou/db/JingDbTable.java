package com.jerry.jingdianyou.db;

/**
 * Created by  Jerry.Zou
 */
public class JingDbTable {
    public static class JingControll {

        public static final int TYPE_JD = 1;
        public static final int TYPE_GL = 2;
        public static final int TYPE_YY = 3;
        //数据库名称
        public static final String NAME = "t_jing_collect";
        //列表名称
        public static final String COLUMN_ID = "guides_id";//scenic_spot_code
        public static final String COLUMN_TITLE = "guides_title";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_PIC = "guides_pic";



        //创建数据库
        public static final String CREAT_DB = "create table " + NAME +
                "(" +
                "_id integer primary key autoincrement,"
                + COLUMN_TYPE + " integer,"
                + COLUMN_ID + " integer,"
                + COLUMN_TITLE + " text,"
                + COLUMN_PIC + " text"
                + ")";
    }
}
