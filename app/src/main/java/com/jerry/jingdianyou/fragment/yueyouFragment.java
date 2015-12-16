package com.jerry.jingdianyou.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.RendezvousDetailActivity;
import com.jerry.jingdianyou.activity.ScenicDetailActivity;
import com.jerry.jingdianyou.db.JingDbHelper;
import com.jerry.jingdianyou.db.JingDbTable;

/**
 * Created by Jerry.Zou
 */
public class yueyouFragment extends Fragment
{
    public yueyouFragment()
    {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = null;
        //查询数据库，如果有景点类型的记录就加载显示，如果没有就显示暂无数据
        JingDbHelper helper = new JingDbHelper(getActivity());
        final Cursor cursor = helper.queryByType(JingDbTable.JingControll.TYPE_YY);

        if (cursor == null || cursor.getCount() == 0)
        {
            view = inflater.inflate(R.layout.yueyou_fragment, container, false);
        }
        else
        {

            ListView listView = new ListView(getActivity());

            cursor.moveToFirst();
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{JingDbTable.JingControll.COLUMN_TITLE},
                    new int[]{android.R.id.text1},
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    Intent intent = new Intent();

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setClass(getActivity(), RendezvousDetailActivity.class);
                    intent.putExtra("rendezvous_id", cursor.getString(cursor.getColumnIndex(JingDbTable.JingControll.COLUMN_ID)));
                    intent.putExtra("member_id", cursor.getString(cursor.getColumnIndex(JingDbTable.JingControll.COLUMN_PIC)));
                    startActivity(intent);
                }
            });


            view = listView;
        }


        return view;
    }
}
