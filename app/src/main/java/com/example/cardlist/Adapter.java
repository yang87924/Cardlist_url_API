package com.example.cardlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends  RecyclerView.Adapter<Adapter.ViewHolder>  {
    LayoutInflater inflater;
    List<data> data;
    private Context context;
    private RecycleViewClickListener listener;
    public Adapter(Context context ,List<data>data,RecycleViewClickListener listener){
        this.inflater=LayoutInflater.from(context);
        this.data=data;
        this.listener=listener;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        //holder.content.setText(data.get(position).getContent());
        //holder.msgTime.setText(data.get(position).getMsgTime());
        //Picasso.get().load(data.get(position).getImgURL()).into(holder.imgURL);
        Picasso .get()
                .load(data.get(position).getImgURL())
                .placeholder(R.mipmap.ic_launcher)              //新增佔位圖片
                .error(R.mipmap.ic_launcher)                          //載入失敗顯示的圖片
                .into(holder.imgURL);
        Animation animation= AnimationUtils.loadAnimation(holder.title.getContext(),R.anim.myanim);
        holder.itemView.startAnimation(animation);

        /*Picasso = new Picasso.Builder(context.getApplicationContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e(TAG, uri.toString(), exception);
                    }
                }).build();*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public interface RecycleViewClickListener{
        void onClick(View v,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,content,msgTime;
        ImageView imgURL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textViewTitle);
            //content=itemView.findViewById(R.id.textViewContent);
            //msgTime=itemView.findViewById(R.id.textViewMsgTime);
            imgURL=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
}
