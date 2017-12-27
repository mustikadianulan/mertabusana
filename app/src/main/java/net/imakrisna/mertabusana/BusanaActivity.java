package net.imakrisna.mertabusana;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import net.imakrisna.mertabusana.adapter.BusanaAdapter;
import net.imakrisna.mertabusana.api.ApiService;
import net.imakrisna.mertabusana.model.BusanaModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusanaActivity extends AppCompatActivity {
    ApiService apiService=new ApiService();
    Call<List<BusanaModel>> apiCall;
    List<BusanaModel> listBusana=new ArrayList<>();
    RecyclerView rvBusana;
    BusanaAdapter adapter;
    ProgressDialog progressDialog;
    public static final String KEY_JENIS="jenis";

    String jenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busana);
        init();
        setData();
    }

    public void init(){
        rvBusana=findViewById(R.id.rv_busana);
        rvBusana.setLayoutManager(new GridLayoutManager(this,3));
        progressDialog=new ProgressDialog(this);
        jenis=getIntent().getStringExtra(KEY_JENIS);
    }

    public void setData(){
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();

        apiCall=apiService.getService().getBusana(jenis);
        apiCall.enqueue(new Callback<List<BusanaModel>>() {
            @Override
            public void onResponse(Call<List<BusanaModel>> call, Response<List<BusanaModel>> response) {
                if (response.isSuccessful()){
                    listBusana=response.body();
                    setAdapter();
                }else{
                    Toast.makeText(BusanaActivity.this, "respone gagal", Toast.LENGTH_SHORT).show();
                }
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<BusanaModel>> call, Throwable t) {
                Toast.makeText(BusanaActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }

    public void setAdapter(){
        adapter=new BusanaAdapter(listBusana,this);
        rvBusana.setAdapter(adapter);
    }
}
