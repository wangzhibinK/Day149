package com.example.day149;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.day149.api.ApiService;
import com.example.day149.bean.ProgressEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyService extends Service {
    private static final String TAG = "MyService";
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                super.run();
                load();
            }
        }.start();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    private void load() {
        File file = new File(getExternalCacheDir()+"/b.apk");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        new Retrofit.Builder()
                .baseUrl(ApiService.url_load)
                .build()
                .create(ApiService.class)
                .downLoad()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        long max = response.body().contentLength();
                        InputStream inputStream = response.body().byteStream();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                saveFile(file, max, inputStream);
                            }
                        }).start();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        int i = 0;
                    }
                });
    }

    private void saveFile(File file, long max, InputStream inputStream) {
        EventBus.getDefault().post(new ProgressEvent(1, 0, (int) max, 0));
        try {
            FileOutputStream fos = new FileOutputStream(file);
            int len = 0;
            int count = 0;
            byte[] bytes = new byte[1024 * 4];
            while ((len = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
                count += len;
                Log.e(TAG, "saveFile: "+count);
                int l = (int) (((float)count / max) * 100);
                Log.e(TAG, "saveFile: "+l);
                EventBus.getDefault().post(new ProgressEvent(2, count, (int) max, l));
            }
            fos.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

