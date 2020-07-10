package com.example.day149.presenter;

import com.example.day149.bean.ProjectBean;
import com.example.day149.callback.ProjectCallBack;
import com.example.day149.model.ProjectModel;
import com.example.day149.view.ProjectView;

import java.util.ArrayList;

public class ProjectPresenter implements ProjectCallBack {
    private ProjectView projectView;
    private ProjectModel projectModel;

    public ProjectPresenter(ProjectView projectView) {
        this.projectView = projectView;
        projectModel = new ProjectModel();
    }

    @Override
    public void onSuccess(ArrayList<ProjectBean.DataBean.DatasBean> list) {
        projectView.onSuccess(list);
    }

    @Override
    public void onFail(String error) {
         projectView.onFail(error);
    }
    public void http(){
        projectModel.setData(this);
    }
}
