package com.example.sensors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rohitramaswamy on 09/01/17.
 */

public class PostToDB {
    String x;
    String y;
    String z;
    private Request request;
    String responseString;

    public PostToDB(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    void post() {
        String url = "http://192.168.1.38/";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("x", x)
                .add("y", y)
                .add("z", z)
                .build();
        request = new Request.Builder()
                .url(url)
                .method("POST", body.create(null, new byte[0]))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseString = response.body().string();
                Log.v("response", responseString);
            }
        });
    }
}
