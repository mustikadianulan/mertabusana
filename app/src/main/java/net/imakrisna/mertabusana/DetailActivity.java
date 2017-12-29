package net.imakrisna.mertabusana;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import net.imakrisna.mertabusana.Utilities.MySharedPreference;
import net.imakrisna.mertabusana.api.ApiService;
import net.imakrisna.mertabusana.model.BusanaModel;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mustika on 28/12/2017.
 */

public class DetailActivity extends AppCompatActivity{
    public static final String KEY_BUSANA="busana";
    public static final String IMAGE_URL="http://192.168.43.124:8000/images/busana/";
    TextView txtDetailNama,txtDetailHarga,txtDetailDeskripsi;
    ImageView ivdetailGambar;
    ImageView ivBooking;
    BusanaModel busana;
    ApiService apiService=new ApiService();
    Call<JSONObject> apiCall;
    int year_x,month_x,day_x;
    EditText edtTanggalPinjam,edtTanggalKembali;
    Calendar calender,calendarKembali;

    String tglPinjam,tglKembali;

    int stok;
    MySharedPreference sharedPreference;

    ProgressDialog progressDialog;

//    Call<>
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        setDate();
    }

    public void setDate(){
        calender= Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        year_x=calender.get(Calendar.YEAR);
        month_x=calender.get(Calendar.MONTH);
        day_x=calender.get(Calendar.DAY_OF_MONTH);
    }

    public void init(){
        txtDetailNama=findViewById(R.id.detailnama);
        txtDetailHarga=findViewById(R.id.detailharga);
        txtDetailDeskripsi=findViewById(R.id.detaildeskripsi);
        ivdetailGambar=findViewById(R.id.detailgambar);
        ivBooking=findViewById(R.id.btn_booking);
        busana= (BusanaModel) getIntent().getSerializableExtra(KEY_BUSANA);

        txtDetailNama.setText(busana.getNama_busana());
        txtDetailHarga.setText("Rp."+busana.getHarga_sewa());
        txtDetailDeskripsi.setText(busana.getDeskripsi());

        stok=Integer.parseInt(busana.getStok());

        progressDialog=new ProgressDialog(this);
        Glide.with(this).load(IMAGE_URL+busana.getGambar()).into(ivdetailGambar);
        sharedPreference=new MySharedPreference(this);
        ivBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder alertdialog= new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.dialog_booking,null);
        alertdialog.setView(dialogView);

        final EditText edtJumlah=dialogView.findViewById(R.id.edt_jumlah_booking);
        edtTanggalKembali=dialogView.findViewById(R.id.edt_tanggal_kembali);
        edtTanggalPinjam=dialogView.findViewById(R.id.edt_tanggal_pinjam);
        TextView txtStok=dialogView.findViewById(R.id.txt_stok);
        txtStok.setText("Stok = "+String.valueOf(stok));
        edtTanggalPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTanggalPinjam();
            }
        });

        edtTanggalKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTanggalKembali();
            }
        });

        alertdialog.setTitle("Masukan Jumlah Booking");
        alertdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Integer.parseInt(edtJumlah.getText().toString())>stok){
                    Toast.makeText(DetailActivity.this, "Melebihi stok", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.setMessage("Please Wait. .");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    apiCall=apiService.getService().booking(busana.getId_busana(),sharedPreference.getUserId(),edtTanggalPinjam.getText().toString(),edtTanggalKembali.getText().toString(),Integer.parseInt(edtJumlah.getText().toString()));
                    apiCall.enqueue(new Callback<JSONObject>() {
                        @Override
                        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(DetailActivity.this, "Booking Berhasil", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(DetailActivity.this, "Respone Gagal", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.cancel();
                        }

                        @Override
                        public void onFailure(Call<JSONObject> call, Throwable t) {
                            Toast.makeText(DetailActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                        }
                    });
                }
            }
        });
        alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog b= alertdialog.create();
        b.show();
    }

    public void showTanggalPinjam(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date=year+"-"+dateValidation(month+1)+"-"+dateValidation(dayOfMonth);
                year_x=year;
                month_x=month;
                day_x=dayOfMonth;
                edtTanggalPinjam.setText(date);
                tglPinjam=dateValidation(dayOfMonth)+"-"+dateValidation(month+1)+"-"+year;
                minTanggalKembali();
            }
        },year_x,month_x,day_x);
        datePickerDialog.getDatePicker().setMinDate(calender.getTimeInMillis());
        datePickerDialog.show();
    }

    public void showTanggalKembali(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date=year+"-"+dateValidation(month+1)+"-"+dateValidation(dayOfMonth);
                year_x=year;
                month_x=month;
                day_x=dayOfMonth;
                edtTanggalKembali.setText(date);
            }
        },year_x,month_x,day_x);
        datePickerDialog.getDatePicker().setMinDate(calendarKembali.getTimeInMillis());
        datePickerDialog.show();
    }

    public String dateValidation(int value){
        if (value<10){
            return "0"+value;
        }
        return String.valueOf(value);
    }


    public void minTanggalKembali(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
//        String dateInString = "22-01-2015 10:20:56";
        Date date = null;
        try {
            date = sdf.parse(tglPinjam);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //2. Test - Convert Date to Calendar
        calendarKembali = dateToCalendar(date);
    }

    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }


}
