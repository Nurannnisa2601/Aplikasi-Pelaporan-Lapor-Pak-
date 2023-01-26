package com.example.laporpakk;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class Register extends AppCompatActivity {


    Button BTregister;
    EditText ETEmail, ETUsername, ETNik, ETPassword  ;
    String username,email, password, nik;
    ImageView IconBack;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // deklarasikan variabel
        BTregister = findViewById(R.id.BT1);
        ETEmail = findViewById(R.id.ETR0);
        ETUsername = findViewById(R.id.ETR);
        ETNik = findViewById(R.id.ETR1);
        ETPassword = findViewById(R.id.ETR2);
        IconBack = findViewById(R.id.idIconBack);


        // Memfungsikan Widget




        BTregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = ETEmail.getText().toString();
                username = ETUsername.getText().toString();
                nik = ETNik.getText().toString();
                password = ETPassword.getText().toString();

                validasiData();

                // Toast.makeText(getApplicationContext(),"Berhasil Melakukan Register",Toast.LENGTH_LONG).show();
                // startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }

    void validasiData(){
        if(email.equals("") ||username.equals("") || nik.equals("") || password.equals("")){
            Toast.makeText(Register.this, "Data Tidak Lengkap", Toast.LENGTH_SHORT).show();
        } else {
            kirimData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_2/cek_registercopy2.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("username",""+username)
                .addBodyParameter("nik",""+nik)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("cekRegister",""+response);

                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan   = response.getString("result");
                            Toast.makeText(Register.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(Register.this)
                                        .setMessage("Berhasil Register")
                                        .setCancelable(false)
                                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(Register.this, Login.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }
                            else {
                                new AlertDialog.Builder(Register.this)
                                        .setMessage("Gagal Melakukan Register !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(Register.this, Register.class);
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
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });


    }
}