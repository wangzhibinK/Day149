package com.example.day149.model;

import com.example.day149.api.ApiService;
import com.example.day149.bean.ProjectBean;
import com.example.day149.callback.ProjectCallBack;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectModel {

    public void setData(ProjectCallBack projectCallBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.baseProjectUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<ProjectBean> data = apiService.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectBean projectBean) {
                        if (projectBean!=null&&projectBean.getData().getDatas()!=null){
                            projectCallBack.onSuccess((ArrayList<ProjectBean.DataBean.DatasBean>) projectBean.getData().getDatas());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                         projectCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
