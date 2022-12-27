package com.example.gamewiki.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gamewiki.API.response.item;
import com.example.gamewiki.R;
import com.example.gamewiki.detailActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    ArrayList<item> listItem;
    public ItemAdapter(Context context,ArrayList<item> itemList){
        this.context = context;
        this.listItem = itemList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gán View
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position) {
        item Item = listItem.get(position);
        holder.txtItem.setText(Item.getName());

        Glide.with(context)
                .load(Item.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgAvatar);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener(Item);
            }
        });
    }

    private void onClickListener(item item) {
        Intent i = new Intent(context, detailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("idItem", item.get_id());
//        i.putExtras(bundle);
        i.putExtra("idItem", item.get_id());
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAvatar;
        TextView txtItem;
        private RelativeLayout layoutItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imageAvatar);
            txtItem = itemView.findViewById(R.id.tvItemName);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }


    }

}
