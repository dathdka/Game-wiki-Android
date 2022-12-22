package com.example.gamewiki;

import static com.example.gamewiki.API.serviceAPI.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gamewiki.API.request.dataRequest;
import com.example.gamewiki.API.response.item;
import com.example.gamewiki.API.serviceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText text;
    ItemAdapter itemAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(MainActivity.this, searchActivity.class );
                startActivity(search);


            }
        });
        getAllItem();//lấy dữ liệu
        recyclerView.setAdapter(itemAdapter);
    }

    public void getAllItem (){
        dataRequest body = new dataRequest();
        Toast.makeText(this, "working on it", Toast.LENGTH_SHORT).show();
        serviceAPI call = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(serviceAPI.class);
        new CompositeDisposable().add(call.getAllItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)//xử lý dữ liệu trả về
        );
    }
    private void handleResponse(ArrayList<item> itemList) {
        try {

            text.setText(itemList.toString());
            itemAdapter = new ItemAdapter(getApplicationContext(),itemList);
            Toast.makeText(this,itemAdapter.toString(),Toast.LENGTH_LONG);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleError(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show();
        Log.e("loi",throwable.toString());
    }
}