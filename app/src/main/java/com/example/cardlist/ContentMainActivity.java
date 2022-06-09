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
        //setContentView(R.layout.activity_content_main);
        binding = ActivityContentMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras= getIntent().getExtras();


        if(extras!=null){
            //Log.d("qwe",extras.getString("content"));
            //Log.d("qwe",extras.getString("content"));
            //Log.d("qwe",extras.getString("msgTime"));

            url=extras.getString("img");
            binding.textViewTitle.setText(extras.getString("title"));
            binding.textViewContent.setText(extras.getString("content"));
            binding.textViewMsgTime.setText(extras.getString("msgTime"));
            Log.d("asd",url);

            image();
        }
    }
    private void image(){
        RequestQueue mqueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                binding.imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //iv.setImageResource(R.drawable.test);
                binding.imageView.setImageResource(R.drawable.k1);
            }
        });
        mqueue.add(imageRequest);
    }
}