package com.example.day149.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day149.MyService;
import com.example.day149.R;
import com.example.day149.bean.ProgressEvent;
import com.example.day149.utils.ApkInstallUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment implements View.OnClickListener {

    private ProgressBar pbr;
    private TextView tv_name;
    private Button btn_start;
    private View inflate;
    public static String file;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_collection, container, false);
        EventBus.getDefault().register(this);
        initView(inflate);
        initPers();
        file =getActivity().getExternalCacheDir()+"/b.apk";
        return inflate;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveProgress(ProgressEvent progressEvent) {
        if (progressEvent.getType() == 1) {
            pbr.setMax(progressEvent.getMax());
        } else if (progressEvent.getType() == 2) {
            pbr.setProgress(progressEvent.getProgress());
            tv_name.setText("当前下载进度:" + progressEvent.getTxt() + "%");
            if (progressEvent.getTxt() == 100) {
                Toast.makeText(getActivity(), "下载完成", Toast.LENGTH_SHORT).show();
                ApkInstallUtil.installApk(getActivity(), file);
            }
        }
    }

    private void initPers() {
        int i = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (i != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
       /* String[] pers = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(getActivity(), pers, 100);*/
    }

   /* @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false){

        }
    }*/

    private void initView(View inflate) {
        pbr = inflate.findViewById(R.id.pbr);
        tv_name = inflate.findViewById(R.id.tv_name);
        btn_start = inflate.findViewById(R.id.btn_start);

        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MyService.class);
        getActivity().startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
