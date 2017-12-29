package net.imakrisna.mertabusana.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.imakrisna.mertabusana.R;
import net.imakrisna.mertabusana.Utilities.MySharedPreference;
import net.imakrisna.mertabusana.adapter.BookingAdapter;
import net.imakrisna.mertabusana.adapter.BusanaAdapter;
import net.imakrisna.mertabusana.api.ApiService;
import net.imakrisna.mertabusana.model.BookingModel;
import net.imakrisna.mertabusana.model.BusanaModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookingFragment extends Fragment{
    ApiService apiService=new ApiService();
    Call<List<BookingModel>> apiCall;
    List<BookingModel> listBooking=new ArrayList<>();
    RecyclerView rvBooking;
    BookingAdapter adapter;
    ProgressDialog progressDialog;
    MySharedPreference sharedPreference;
    public BookingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(getContext());
        sharedPreference=new MySharedPreference(getContext());
        setData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_booking,container,false);
        rvBooking=view.findViewById(R.id.rv_booking);
        rvBooking.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public void setData(){
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String id_user=String.valueOf(sharedPreference.getUserId());
        apiCall=apiService.getService().bookingUser(id_user);
        apiCall.enqueue(new Callback<List<BookingModel>>() {
            @Override
            public void onResponse(Call<List<BookingModel>> call, Response<List<BookingModel>> response) {
                if (response.isSuccessful()){
                    listBooking=response.body();
                    setAdapter();
                }else {
                    Toast.makeText(getContext(), "response gagal", Toast.LENGTH_SHORT).show();
                }
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<BookingModel>> call, Throwable t) {
                Toast.makeText(getContext(), "koneksi error", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }
    public void setAdapter(){
        adapter=new BookingAdapter(listBooking,getContext());
        rvBooking.setAdapter(adapter);
    }
}
