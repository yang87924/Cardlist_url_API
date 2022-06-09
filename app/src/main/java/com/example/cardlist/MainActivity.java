package com.example.cardlist;

import androidx.appcompat.app.AppCompatActivity;
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
    private ActivityMainBinding binding;
    List<data>datas;
    List<data>dataContent;
    Adapter adapter;

    private Adapter.RecycleViewClickListener listener;
    private static String url = "https://smuat.megatime.com.tw/EAS/Apps/systex/hr_elearning/hr_elearning_20220602_181350.json";


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
        binding.revycleview.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation));
        binding.revycleview.setAdapter(adapter);
    }

    private void setOnclickListiner() {
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
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
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

    /*private class MyAdApter extends BaseAdapter{
        private LayoutInflater myInflater;
        public MyAdApter(Context c) {
            myInflater=LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view =myInflater.inflate(R.layout.layout,null);
            //ImageView imageView=view.findViewById(R.id.imageView);
            TextView textViewTitle=view.findViewById(R.id.textViewTitle);
            TextView textViewContent=view.findViewById(R.id.textViewContent);
            //imageView.setImageResource(imageIds[i]);
            textViewTitle.setText(title[i]);
            textViewContent.setText(content[i]);
            return  view;

        }
    }*/
