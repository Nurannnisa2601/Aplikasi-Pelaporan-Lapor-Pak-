package com.example.laporpakk;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class Login extends AppCompatActivity {


    TextView BTDaftar;
    Button BTlogin;
    EditText ETuser1, ETpass2;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String email, password;
//    int id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BTlogin = findViewById(R.id.idBTlogin);
        ETuser1 = findViewById(R.id.ET1);
        ETpass2 = findViewById(R.id.ET2);
        BTDaftar = findViewById(R.id.idTVdaftar);

        progressDialog = new ProgressDialog(this);

        sessionManager = new SessionManager(getApplicationContext());



        BTDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        BTlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(getApplicationContext(),"Berhasil Login",Toast.LENGTH_LONG).show();
            //    startActivity(new Intent(getApplicationContext(), MainFragment.class));
                progressDialog.setMessage("Login..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                email = ETuser1.getText().toString();
                password = ETpass2.getText().toString();

                validasiData();
            }
        });

    }

    void validasiData(){
        if(email.equals("") || password.equals("")){
            Toast.makeText(Login.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            cekLogin();
        }
    }

    void cekLogin(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_2/cek_logincopy2.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Cek Login")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("Cek Login",""+response);

                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan   = response.getString("result");
                            String username = response.getString("username");
                            String email = response.getString("email");
                            String id_users = response.getString("id_users");
                            String nik = response.getString("nik");
//                          int id_users = response.getInt("id_users");
                            Toast.makeText(Login.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(Login.this)
                                        .setMessage("Berhasil Login")
                                        .setCancelable(false)
                                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                sessionManager.createSession_username(username);
                                                sessionManager.createSession_email(email);
                                                sessionManager.createSession_nik(nik);

                                                Intent intent = new Intent(Login.this, MainFragment.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }
                            else {
                                new AlertDialog.Builder(Login.this)
                                        .setMessage("Gagal Melakukan Login !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Intent intent = new Intent(Login.this, Login.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorLogin",""+anError.getErrorBody());
                    }
                });

    }
}