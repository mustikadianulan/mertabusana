package net.imakrisna.mertabusana.api;

import net.imakrisna.mertabusana.model.BusanaModel;
import net.imakrisna.mertabusana.model.UserModel;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mustika on 27/12/2017.
 */

public interface ApiInterface {
    @POST("login")
    Call<UserModel> login(@Query("email") String email, @Query("password") String password);

    @POST("register")
    Call<UserModel> register(@Query("nama") String nama, @Query("alamat") String alamat,
                              @Query("no_telp") String no_telp, @Query("jenis_kelamin") String jenis_kelamin,
                              @Query("email") String email, @Query("password") String password);

    @GET("busana/{jenis}")
    Call<List<BusanaModel>> getBusana(@Path("jenis") String jenis);

    @POST("booking")
    Call<JSONObject> booking(@Query("id_busana") int id_busana, @Query("id_user") int id_user, @Query("tgl_pinjam") String tgl_pinjam,
                             @Query("tgl_kembali") String tgl_kembali, @Query("jumlah_busana") int jumlah_busana);

    @GET("search")
    Call<List<BusanaModel>> searchBusana(@Query("keyword") String keyword);
}
