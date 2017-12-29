package net.imakrisna.mertabusana.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.imakrisna.mertabusana.LoginActivity;
import net.imakrisna.mertabusana.R;
import net.imakrisna.mertabusana.Utilities.MySharedPreference;
import net.imakrisna.mertabusana.model.UserModel;


public class ProfilFragment extends Fragment{
    TextView txtnama,txtalamat,txtnotlp,txtjk,txtemail;
    ImageView ivlogout;

    UserModel user;
    MySharedPreference sharedPreference;

    public ProfilFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        sharedPreference=new MySharedPreference(getContext());

        txtnama.setText(sharedPreference.getNAMA());
        txtalamat.setText(sharedPreference.getALAMAT());
        txtnotlp.setText(sharedPreference.getTELP());
        txtjk.setText(sharedPreference.getJK());
        txtemail.setText(sharedPreference.getEMAIL());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profil,container,false);

        txtnama=view.findViewById(R.id.nama_profil);
        txtalamat=view.findViewById(R.id.alamat_profil);
        txtnotlp=view.findViewById(R.id.notelp_profil);
        txtjk=view.findViewById(R.id.jenis_kelamin_profil);
        txtemail=view.findViewById(R.id.email_profil);
        ivlogout=view.findViewById(R.id.iv_logout);

        ivlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.setIsLogin(false);
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
