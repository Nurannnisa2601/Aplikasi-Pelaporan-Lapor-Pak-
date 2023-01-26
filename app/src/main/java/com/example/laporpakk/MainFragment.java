package com.example.laporpakk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.laporpakk.menu.history.HistoryFragment;
import com.example.laporpakk.menu.home.HomeFragment;
import com.example.laporpakk.menu.profil.ProfilFragment;

import nl.joery.animatedbottombar.AnimatedBottomBar;


public class MainFragment extends AppCompatActivity {
    private static final String TAG = MainFragment.class.getSimpleName();

    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        initViews(savedInstanceState);
    }

    @SuppressLint("NonConstantResourceId")
    private void initViews(Bundle savedInstanceState) {
        /**
         * Menu Bottom Navigation Drawer
         * */
        animatedBottomBar = findViewById(R.id.navigation);

        if (savedInstanceState == null) {
            animatedBottomBar.selectTabById(R.id.nav_menu_home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

        animatedBottomBar.setOnTabSelectListener((lastIndex, lastTab, newIndex, newTab) -> {
            Fragment fragment = null;
            switch (newTab.getId()) {
                case R.id.nav_menu_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nav_menu_wishlist:
                    fragment = new HistoryFragment();
                    break;
                case R.id.nav_menu_signin:
                    fragment = new ProfilFragment();
                    break;
            }

            if (fragment != null) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            } else {
                Log.e(TAG, "Error in creating Fragment");
            }
        });

    }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }