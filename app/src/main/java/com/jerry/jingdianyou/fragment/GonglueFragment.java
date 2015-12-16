package com.jerry.jingdianyou.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.jerry.jingdianyou.R;
import com.jerry.jingdianyou.activity.StrategyDetailActivity;
import com.jerry.jingdianyou.db.JingDbHelper;
import com.jerry.jingdianyou.db.JingDbTable;
import com.jerry.jingdianyou.entity.Home;

import java.util.List;

/**
 * Created by Jerry.Zou
 */
public class GonglueFragment extends Fragment {
    public GonglueFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        //查询数据库，如果有攻略类型的记录就加载显示，如果没有就显示暂无数据
        JingDbHelper helper = new JingDbHelper(getActivity());
        final Cursor cursor = helper.queryByType(JingDbTable.JingControll.TYPE_GL);

        if (cursor == null ) {
            view = inflater.inflate(R.layout.gonglue_fragment, container, false);
        } else {

            ListView listView = new ListView(getActivity());

            cursor.moveToFirst();
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{JingDbTable.JingControll.COLUMN_TITLE},
                    new int[]{android.R.id.text1},
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent();

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("guides_id", cursor.getString(cursor.getColumnIndex(JingDbTable.JingControll.COLUMN_ID)));
                    intent.putExtra("pic", cursor.getString(cursor.getColumnIndex(JingDbTable.JingControll.COLUMN_PIC)));
                    intent.putExtra("title", cursor.getString(cursor.getColumnIndex(JingDbTable.JingControll.COLUMN_TITLE)));
                    intent.setClass(getActivity(), StrategyDetailActivity.class);
                    startActivity(intent);
                }
            });

            view = listView;
        }


        return view;
    }

}
