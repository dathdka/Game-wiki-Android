package com.example.gamewiki;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamewiki.API.response.item;

import java.util.ArrayList;
import java.util.Locale;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    ArrayList<item> listItem;

    public ItemAdapter(Context context,ArrayList<item> itemList){
        this.context = context;
        this.listItem = itemList;
    }

    public  void addItem (ArrayList<item> i){
        listItem.addAll(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gán View
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //gán dữ liệu
        item item = listItem.get(position);
        holder.txtItem.setText(item.getName());
        holder.imgAvatar.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAvatar;
        TextView txtItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imageAvatar);
            txtItem = itemView.findViewById(R.id.tvItemName);
        }


    }

}
