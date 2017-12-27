package net.imakrisna.mertabusana.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mustika on 27/12/2017.
 */

public class MySharedPreference {
    private static final String SHARED_USER="user";

    private static final String IS_LOGIN="is_login";
    private static final String USER_ID="user_id";
    private static final String NAMA="nama";
    private static final String ALAMAT="alamat";
    private static final String TELP="no_telp";
    private static final String JK="jenis_kelamin";
    private static final String EMAIL="email";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_USER,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setIsLogin (boolean isLogin){
        editor.putBoolean(IS_LOGIN,isLogin);
        editor.apply();
    }

    public void setUserId(int userId) {
        editor.putInt(USER_ID, userId);
        editor.apply();
    }

    public void setNAMA(String nama) {
        editor.putString(NAMA, nama);
        editor.apply();
    }

    public void setALAMAT(String Alamat) {
        editor.putString(ALAMAT, Alamat);
        editor.apply();
    }

    public void setTELP(String Telp) {
        editor.putString(TELP, Telp);
        editor.apply();
    }

    public void setJK(String JK) {
        editor.putString(JK, JK);
        editor.apply();
    }

    public void setEMAIL(String Email) {
        editor.putString(EMAIL, Email);
        editor.apply();
    }

    public Boolean getIsLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public int getUserId() {
        return sharedPreferences.getInt(USER_ID, 0);
    }

    public String getNAMA() {
        return sharedPreferences.getString(NAMA, "");
    }

    public String getALAMAT() {
        return sharedPreferences.getString(ALAMAT, "");
    }

    public String getTELP() {
        return sharedPreferences.getString(TELP, "");
    }

    public String getJK() {
        return sharedPreferences.getString(JK, "");
    }

    public String getEMAIL() {
        return sharedPreferences.getString(EMAIL, "");
    }
}
