package com.example.day149.view;

import com.example.day149.bean.ProjectBean;

import java.util.ArrayList;

public interface ProjectView {
    void onSuccess(ArrayList<ProjectBean.DataBean.DatasBean> list);

    void onFail(String error);
}
