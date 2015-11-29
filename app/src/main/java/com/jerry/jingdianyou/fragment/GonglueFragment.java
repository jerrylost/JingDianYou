package com.jerry.jingdianyou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jerry.jingdianyou.R;

/**
 * Created by Jerry.Zou
 */
public class GonglueFragment extends Fragment
{
  public GonglueFragment()
  {
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.gonglue_fragment, container, false);
    return view;
  }
}
