package com.example.laporpakk;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class notif_history extends AppCompatActivity {

    SwipeRefreshLayout srl_main;
    ArrayList<String> array_nama, array_telepon,array_tanggal,array_lokasi_detail,array_isi_laporan,array_foto;
    ProgressDialog progressDialog;
    ListView listProses;
    String nama_user;

    SessionManager sessionManager;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_history);

        //set variable sesuai dengan widget yang digunakan
        listProses = findViewById(R.id.ListV1);
        srl_main    = findViewById(R.id.swipe_container);
        progressDialog = new ProgressDialog(this);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        nama_user = user.get(SessionManager.KEY_USERNAME);

        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });
        // Scheme colors for animation
        srl_main.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );

        scrollRefresh();
    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {getData(); }
        },2000);
    }

    void initializeArray(){
        array_nama              = new ArrayList<String>();
        array_telepon           = new ArrayList<String>();
        array_tanggal           = new ArrayList<String>();
        array_lokasi_detail     = new ArrayList<String>();
        array_isi_laporan       = new ArrayList<String>();
        array_foto              = new ArrayList<String>();

        // clear ini untuk menginilisasi array
        array_nama.clear();
        array_telepon.clear();
        array_tanggal.clear();
        array_lokasi_detail.clear();
        array_isi_laporan.clear();
        array_foto.clear();

        //nama2 = nama.getText().toString();

    }

    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://tkjbpnup.com/kelompok_2/getDatalapor.php?nama="+nama_user)
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);
                                    array_nama.add(jo.getString("nama"));
                                    array_telepon.add(jo.getString("nohp"));
                                    array_tanggal.add(jo.getString("tanggal"));
                                    array_lokasi_detail.add(jo.getString("lokasi"));
                                    array_isi_laporan.add(jo.getString("isilaporan"));
                                    //add this code
                                    array_foto.add(jo.getString("gambar"));

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_Data adapter = new CLV_Data(notif_history.this, array_nama,array_telepon,
                                        array_tanggal,array_lokasi_detail,array_isi_laporan,array_foto);

                                listProses.setAdapter(adapter);

                            }else{
                                Toast.makeText(notif_history.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }


                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                scrollRefresh();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode==2){
            if(resultCode==RESULT_OK){
                scrollRefresh();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
