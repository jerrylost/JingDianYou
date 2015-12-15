package com.jerry.jingdianyou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.jerry.jingdianyou.application.AppManager;
import com.jerry.jingdianyou.utils.ToastUtils;

/**
 * Created by jerry on 15/11/19.
 */
public abstract class BaseActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        AppManager.getAppManager().removeActivity(this);
    }

    protected void showToast(CharSequence tip)
    {
        if (TextUtils.isEmpty(tip))
        {
            return;
        }
        ToastUtils.show(this, tip);
    }

    protected void showToast(int resId)
    {
        if (resId == -1)
        {
            return;
        }
        this.showToast(getString(resId));
    }
}
