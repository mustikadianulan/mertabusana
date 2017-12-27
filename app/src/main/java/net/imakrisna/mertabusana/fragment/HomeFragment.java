package net.imakrisna.mertabusana.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.imakrisna.mertabusana.BusanaActivity;
import net.imakrisna.mertabusana.R;


public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageView imgPengantin,imgTari,imgModifikasi;
    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        imgTari=view.findViewById(R.id.img_tari);
        imgModifikasi=view.findViewById(R.id.img_modifikasi);
        imgPengantin=view.findViewById(R.id.img_pengantin);

        imgTari.setOnClickListener(this);
        imgModifikasi.setOnClickListener(this);
        imgPengantin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(), BusanaActivity.class);
        switch (view.getId()){
            case R.id.img_tari:
                intent.putExtra(BusanaActivity.KEY_JENIS,"tari");
                startActivity(intent);
                break;
            case R.id.img_modifikasi:
                intent.putExtra(BusanaActivity.KEY_JENIS,"modifikasi");
                startActivity(intent);
                break;
            case R.id.img_pengantin:
                intent.putExtra(BusanaActivity.KEY_JENIS,"tradisional");
                startActivity(intent);
                break;
        }

    }
}
