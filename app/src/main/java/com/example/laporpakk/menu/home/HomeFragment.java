package com.example.laporpakk.menu.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laporpakk.InfoActivity;
import com.example.laporpakk.LaporanActivity;
import com.example.laporpakk.R;
import com.example.laporpakk.SessionManager;
import com.example.laporpakk.notif_history;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CardView imageView1, imageView2, imageView3, imageView4, infocard;
    TextView TVuser;
    ImageView IVnotif;
    SessionManager sessionManager;
    String username;


    //todo 0. If you want to add more types of objects, you can easily do so. Check "todo list" (I added it step by step)


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLayout();
    }


    private void setLayout() {

        imageView1 = getView().findViewById(R.id.card_pemadam);
        imageView2 = getView().findViewById(R.id.card_bencana);
        imageView3 = getView().findViewById(R.id.card_aspirasi);
        imageView4 = getView().findViewById(R.id.card_callcenter);
        infocard = getView().findViewById(R.id.cvinfo);
        TVuser = getView().findViewById(R.id.TVuser3);
        IVnotif = getView().findViewById(R.id.idnotifhistory);


        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetails();
        username = user.get(SessionManager.KEY_USERNAME);

        TVuser.setText(username);


        imageView1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), LaporanActivity.class);
            startActivity(intent);

        });

        imageView2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), LaporanActivity.class);
            startActivity(intent);
        });

        imageView3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), LaporanActivity.class);
            startActivity(intent);
        });

        infocard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), InfoActivity.class);
            startActivity(intent);
        });

        IVnotif.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFragment.this.getActivity(), notif_history.class);
            startActivity(intent);

        });

    }
}
