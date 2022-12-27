package com.example.gamewiki;

import static com.example.gamewiki.API.serviceAPI.BASE_SERVICE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gamewiki.API.request.dataRequest;
import com.example.gamewiki.API.response.item;
import com.example.gamewiki.API.serviceAPI;
import com.example.gamewiki.Adapter.ItemAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class detailActivity extends AppCompatActivity {
    ImageView imgAvatar;
    TextView txtInfo;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgAvatar = (ImageView)findViewById(R.id.imgAvatar);
        txtInfo = (TextView)findViewById(R.id.txtInfo);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        Intent i = getIntent();
        String id = i.getStringExtra("idItem");
        getItemDetail(id);

    }

    public void getItemDetail (String id) {
        dataRequest body = new dataRequest("",id);
        Toast.makeText(this, "Geting item details", Toast.LENGTH_SHORT).show();
        serviceAPI call = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(serviceAPI.class);
        new CompositeDisposable().add(call.getItemById(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(item item) {
        if (item!= null){
            Log.i("item info",item.toString());
            Glide.with(this)
                    .load(item.getImage())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(imgAvatar);
            txtInfo.setText(item.toString());
            item.getMaterialArray().remove(0);
            setRecyclerView(item.getMaterialArray());
        }
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(this, "Có lỗi trong quá trình truyền dữ liệu, vui lòng thử lại sau", Toast.LENGTH_LONG).show();
        Log.e("loi",throwable.toString());
    }

    public void setRecyclerView (ArrayList<item> materialArray){
        try {
            this.itemAdapter = new ItemAdapter(this,materialArray);
            this.recyclerView.setAdapter(this.itemAdapter);
            this.itemAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(this.itemAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}