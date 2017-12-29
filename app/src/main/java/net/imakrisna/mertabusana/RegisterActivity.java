package net.imakrisna.mertabusana;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.imakrisna.mertabusana.Utilities.MySharedPreference;
import net.imakrisna.mertabusana.api.ApiService;
import net.imakrisna.mertabusana.model.UserModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    ApiService apiService= new ApiService();
    Call<UserModel> apiCall;

    EditText edtNama,edtAlamat,edtTelepon,edtEmail,edtpass;
    RadioGroup radioGroupJk;
    ProgressDialog progressDialog;
    String jenisKelamin;
    ImageView btnRegister;
    UserModel userModel;
    MySharedPreference mySharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    public void init(){
        edtNama=findViewById(R.id.edt_nama);
        edtAlamat=findViewById(R.id.edt_alamat);
        edtEmail=findViewById(R.id.edt_email);
        edtTelepon=findViewById(R.id.edt_telp);
        radioGroupJk=findViewById(R.id.radio_group_jk);
        edtpass=findViewById(R.id.edt_pass);
        btnRegister=findViewById(R.id.btn_register);
        radioGroupJk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb=radioGroup.findViewById(i);
                jenisKelamin=rb.getText().toString();
            }
        });

        progressDialog=new ProgressDialog(this);
        mySharedPreference=new MySharedPreference(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register(){
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String nama=edtNama.getText().toString();
        String alamat=edtAlamat.getText().toString();
        String no_telp=edtTelepon.getText().toString();
        String email=edtEmail.getText().toString();
        String pass=edtpass.getText().toString();

        apiCall=apiService.getService().register(nama,alamat,no_telp,jenisKelamin,email,pass);
        apiCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()){
                    userModel=response.body();
                    Toast.makeText(RegisterActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                    mySharedPreference.setIsLogin(true);
                    mySharedPreference.setUserId(userModel.getId_user());
                    mySharedPreference.setNAMA(userModel.getNama());
                    mySharedPreference.setALAMAT(userModel.getAlamat());
                    mySharedPreference.setTELP(userModel.getNo_telp());
                    mySharedPreference.setJK(userModel.getJenis_kelamin());
                    mySharedPreference.setEMAIL(userModel.getEmail());
                    Intent intent= new Intent (RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else {
                    Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Koneksi error "+t, Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }
}
