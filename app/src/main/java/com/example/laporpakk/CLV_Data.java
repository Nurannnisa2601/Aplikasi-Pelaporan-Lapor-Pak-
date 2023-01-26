package com.example.laporpakk;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CLV_Data extends ArrayAdapter<String> {
    //Declarasi variable
    private final Activity context;
   // private ArrayList<String> vusername;
    private ArrayList<String> vnama;
    private ArrayList<String> vtelepon;
    private ArrayList<String> vtanggal;
    private ArrayList<String> vlokasi;
    private ArrayList<String> visilaporan;
    private ArrayList<String> vfoto;

    public CLV_Data(Activity context, ArrayList<String> nama, ArrayList<String> nohp,ArrayList<String> tanggal,ArrayList<String> lokasi, ArrayList<String> isilaporan, ArrayList<String> foto)
    {
        super(context, R.layout.list_item,nama);
        this.context            = context;
        this.vnama              = nama;
        this.vtelepon           = nohp;
        this.vtanggal           = tanggal;
        this.vlokasi            = lokasi;
        this.visilaporan       = isilaporan;
        this.vfoto              = foto;

    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item, null, true);

        //Declarasi komponen
        TextView isi_laporan    = rowView.findViewById(R.id.deskripsi);
        TextView lokasi_detail  = rowView.findViewById(R.id.lokasi);
        TextView tanggal        = rowView.findViewById(R.id.datetime);
        CircleImageView foto    = rowView.findViewById(R.id.idIVGambar);


        //Set Parameter Value sesuai widget textview
        tanggal.setText(vtanggal.get(position));
        lokasi_detail.setText(vlokasi.get(position));
        isi_laporan.setText(visilaporan.get(position));


        if (vfoto.get(position).equals(""))
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_2/image/no_image.png").into(foto);
        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_2/image_laporpak/"+vfoto.get(position)).into(foto);
        }

        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }


}