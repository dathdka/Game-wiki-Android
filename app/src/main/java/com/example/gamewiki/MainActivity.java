package com.example.gamewiki;

import static com.example.gamewiki.API.serviceAPI.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gamewiki.API.request.dataRequest;
import com.example.gamewiki.API.response.item;
import com.example.gamewiki.API.serviceAPI;
import com.example.gamewiki.Adapter.ItemAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ItemAdapter itemAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        if(!isDataFromSearch())
            getAllItem();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(MainActivity.this, searchActivity.class );
                startActivity(search);

            }
        });
    }

    public boolean isDataFromSearch (){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<item> itemList = new ArrayList<item>();
        try {
            itemList = (ArrayList<item>) bundle.getSerializable("itemList");
        }catch (Exception e){
            return false;
        }
        if(itemList.size()>0){
            setView(itemList);
            return true;
        }
        return false;
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
                .subscribe(this::setView, this::handleError)
        );
    }
    private void setView(ArrayList<item> itemList) {
        try {
            this.itemAdapter = new ItemAdapter(this,itemList);
            this.recyclerView.setAdapter(this.itemAdapter);
            this.itemAdapter.notifyDataSetChanged();
            Log.i("item adapter", this.itemAdapter.toString());
            recyclerView.setAdapter(this.itemAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleError(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show();

        Log.e("loi",throwable.toString());
    }
}