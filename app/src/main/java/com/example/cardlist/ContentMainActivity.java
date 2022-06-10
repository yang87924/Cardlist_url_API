package com.example.cardlist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardlist.databinding.ActivityContentMainBinding;
import com.example.cardlist.databinding.ActivityMainBinding;

public class ContentMainActivity extends AppCompatActivity {
    private ActivityContentMainBinding binding;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContentMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras= getIntent().getExtras();
        if(extras!=null){//判斷傳遞的資料是否為空
            url=extras.getString("img");
            binding.textViewTitle.setText(extras.getString("title"));
            binding.textViewContent.setText(extras.getString("content"));
            binding.textViewMsgTime.setText(extras.getString("msgTime"));
            Log.d("asd",url);
            image();
        }
    }
    private void image(){//判斷圖片是否讀取成功
        RequestQueue mqueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                binding.imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                binding.imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
        mqueue.add(imageRequest);
    }
}