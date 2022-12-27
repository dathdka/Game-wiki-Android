package com.example.gamewiki;

import static com.example.gamewiki.API.serviceAPI.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class searchActivity extends AppCompatActivity {
    Spinner dropdown;
    Button btnSearch;
    EditText txtKeyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dropdown = findViewById(R.id.spinner1);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtKeyWord = (EditText) findViewById(R.id.txtKeyWord);
        String[] items = new String[]{"Vật phẩm", "Vật phẩm có thể thế tạo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    public void search (){
        String keyword = txtKeyWord.getText().toString();

        if(keyword.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập từ khoá", Toast.LENGTH_SHORT).show();
            return;
        }
        serviceAPI call = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(serviceAPI.class);
        callAPI(keyword, call);
    }

    public void callAPI (String keyword, serviceAPI call){
        String option = dropdown.getSelectedItem().toString();
        dataRequest body = new dataRequest(keyword,"");
        if(option.compareTo("Vật phẩm có thể thế tạo") == 0){
            new CompositeDisposable().add(call.canCrafting(body)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError)
            );
            return;
        }
        new CompositeDisposable().add(call.getItemByName(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleError(Throwable throwable) {
        Log.e("error response", throwable.toString());
        Toast.makeText(this, "Có lỗi xảy ra trong quá trình tìm kiếm, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
    }

    private void handleResponse(ArrayList<item> items) {
        Intent mainAct = new Intent(searchActivity.this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("itemList", items);
        mainAct.putExtras(bundle);
        startActivity(mainAct);
    }
}