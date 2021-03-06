package com.example.cardlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardlist.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static{
        System.loadLibrary("keys");
    }
    private native String getApi();
    private ActivityMainBinding binding;
    List<data>datas;
    List<data>dataContent;
    Adapter adapter;
    private Adapter.RecycleViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        datas = new ArrayList<>();
        dataContent = new ArrayList<>();
        extractSongs();


    }
    private void setAdapter() {
        setOnclickListiner();
        binding.revycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new Adapter(getApplicationContext(),datas,listener);
        //設定進場動畫
        binding.revycleview.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation));
        binding.revycleview.setAdapter(adapter);
    }

    private void setOnclickListiner() {//清單點擊事件 傳遞到內容頁面
        listener=new Adapter.RecycleViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent it =new Intent(getApplicationContext(),ContentMainActivity.class);
                it.putExtra("title",dataContent.get(position).getTitle());
                it.putExtra("content",dataContent.get(position).getContent());
                it.putExtra("msgTime",dataContent.get(position).getMsgTime());
                it.putExtra("img",dataContent.get(position).getImgURL());
                startActivity(it);

            }
        };
    }

    private void extractSongs() {
        RequestQueue mqueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getApi(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("DataList");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject dataObject = jsonArray.getJSONObject(i);
                                data data=new data();
                                data.setTitle(dataObject.getString("title").toString());
                                //data.setContent(dataObject.getString("content").toString());
                                //data.setMsgTime(dataObject.getString("msgTime").toString());
                                data.setImgURL(dataObject.getString("img"));
                                datas.add(data);

                                data data2=new data();
                                data2.setTitle(dataObject.getString("title").toString());
                                data2.setContent(dataObject.getString("content").toString());
                                data2.setMsgTime(dataObject.getString("msgTime").toString());
                                data2.setImgURL(dataObject.getString("img"));
                                dataContent.add(data2);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setAdapter();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mqueue.add(request);
    }
}