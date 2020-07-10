package com.example.day149.callback;

import com.example.day149.bean.ProjectBean;

import java.util.ArrayList;

public interface ProjectCallBack {
    void onSuccess(ArrayList<ProjectBean.DataBean.DatasBean> list);

    void onFail(String error);
}
