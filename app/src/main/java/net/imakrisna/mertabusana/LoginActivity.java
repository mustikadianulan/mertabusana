package net.imakrisna.mertabusana;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.imakrisna.mertabusana.Utilities.MySharedPreference;
import net.imakrisna.mertabusana.api.ApiService;
import net.imakrisna.mertabusana.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ApiService apiService= new ApiService();
    Call<UserModel> apiCall;
    UserModel userModel;
    EditText edtEmail,edtpass;
    ProgressDialog progressDialog;
    ImageView btnLogin;
    MySharedPreference mySharedPreference;
    TextView txt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySharedPreference=new MySharedPreference(this);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        edtEmail=findViewById(R.id.edt_emails);
        edtpass=findViewById(R.id.edt_pas);
        btnLogin=findViewById(R.id.btn_login);
        progressDialog=new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        txt_register=findViewById(R.id.txt_register);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }});
    }

    public void login(){
        progressDialog.setMessage("Please Wait. .");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String email=edtEmail.getText().toString();
        String pass=edtpass.getText().toString();

        apiCall=apiService.getService().login(email,pass);
        apiCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                String json=response.get
//                JSONObject user=response.body();
//                Toast.makeText(LoginActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                   userModel=response.body();
                   if (userModel.isSuccess()){
                       Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                       mySharedPreference.setIsLogin(true);
                       mySharedPreference.setUserId(userModel.getId_user());
                       mySharedPreference.setNAMA(userModel.getNama());
                       mySharedPreference.setALAMAT(userModel.getAlamat());
                       mySharedPreference.setTELP(userModel.getNo_telp());
                       mySharedPreference.setJK(userModel.getJenis_kelamin());
                       mySharedPreference.setEMAIL(userModel.getEmail());
//                       finish();
                       Intent intent= new Intent (LoginActivity.this, MainActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                   }else{
                       Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                   }
                    progressDialog.cancel();
                }else {
                    Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Koneksi error "+t, Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });
    }
}
