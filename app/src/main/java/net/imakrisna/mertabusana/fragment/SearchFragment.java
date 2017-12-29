package net.imakrisna.mertabusana.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.imakrisna.mertabusana.BusanaActivity;
import net.imakrisna.mertabusana.DetailActivity;
import net.imakrisna.mertabusana.R;
import net.imakrisna.mertabusana.adapter.BusanaAdapter;
import net.imakrisna.mertabusana.api.ApiService;
import net.imakrisna.mertabusana.model.BookingModel;
import net.imakrisna.mertabusana.model.BusanaModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment implements BusanaAdapter.onClickListener {
    ApiService apiService=new ApiService();
    Call<List<BusanaModel>> apiCall;
    RecyclerView rvBusana;
    BusanaAdapter adapter;
    List<BusanaModel> listBusana=new ArrayList<>();
    ProgressBar progressBar;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_search,container,false);
        rvBusana=view.findViewById(R.id.rvsearch);
        rvBusana.setLayoutManager(new GridLayoutManager(getContext(),3));
        progressBar=view.findViewById(R.id.progressbar);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item =menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listBusana.clear();
                progressBar.setVisibility(View.VISIBLE);
                if (!s.equals("")){
                    apiCall=apiService.getService().searchBusana(s);
                    apiCall.enqueue(new Callback<List<BusanaModel>>() {
                        @Override
                        public void onResponse(Call<List<BusanaModel>> call, Response<List<BusanaModel>> response) {
                            if (response.isSuccessful()){
                                listBusana=response.body();
                                setAdapter();
                            }else{
                                Toast.makeText(getContext(), "respone gagal", Toast.LENGTH_SHORT).show();
                            } progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onFailure(Call<List<BusanaModel>> call, Throwable t) {

                        }
                    });
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }

    public void setAdapter(){
        adapter=new BusanaAdapter(listBusana,getContext(),this);
        rvBusana.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(getContext(),DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_BUSANA,listBusana.get(position));
        startActivity(intent);
    }
}
