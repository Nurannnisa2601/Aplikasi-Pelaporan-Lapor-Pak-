package com.example.laporpakk.menu.profil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.laporpakk.R;
import com.example.laporpakk.SessionManager;

import java.util.HashMap;

public class ProfilFragment extends Fragment {

    private Context mContext;
    TextView TVemail, TVnik, TVnomor;
    SessionManager sessionManager;
    String email, nik;
    Button btnlogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_profil_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TVemail = getView().findViewById(R.id.tv_email);

        TVnik = getView().findViewById(R.id.tv_nik);
        btnlogout = getView().findViewById(R.id.btn_logout);

        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);
        nik = user.get(SessionManager.KEY_NIK);


        TVemail.setText(email);
        TVnik.setText(nik);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logOut();
                Toast.makeText(getActivity(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

    }
});
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

}
