package com.example.gamewiki;

import static com.example.gamewiki.API.serviceAPI.BASE_SERVICE;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamewiki.API.request.dataRequest;
import com.example.gamewiki.API.response.item;
import com.example.gamewiki.API.serviceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class detailActivity extends AppCompatActivity {
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        text = (EditText) findViewById(R.id.details);
    }

    public void getItemDetail () {
        dataRequest body = new dataRequest();
        Toast.makeText(this, "working on it", Toast.LENGTH_SHORT).show();
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
        try {
            Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
            text.setText(item.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_LONG).show();
        Log.e("loi",throwable.toString());
    }
}