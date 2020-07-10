package com.example.day149.api;

import com.example.day149.bean.ProjectBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String baseProjectUrl = "https://www.wanandroid.com/";
    String yes ="111";
    String no = "222";
    @GET("project/list/1/json?cid=294")
    Observable<ProjectBean> getData();



    String url_load = "https://dl.hdslb.com/mobile/latest/";

    @GET("iBiliPlayer-bili.apk?t=1589783162000&spm_id_from=333.47.b_646f776e6c6f61642d6c696e6b.1")
    Call<ResponseBody> downLoad();
}
