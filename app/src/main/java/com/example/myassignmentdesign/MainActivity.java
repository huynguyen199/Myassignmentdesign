package com.example.myassignmentdesign;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignmentdesign.DAO.KhoanthuchiDao;
import com.example.myassignmentdesign.adapter.KhoanTCRCVAdapter;
import com.example.myassignmentdesign.model.Khoanthuchi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    KhoanTCRCVAdapter khoanTCRCVAdapter;
    FrameLayout layout;
    DrawerLayout drawer;
    KhoanTCFragment khoanTCFragment;
    ArrayList<Khoanthuchi> list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KhoanthuchiDao daothuchi = new KhoanthuchiDao(MainActivity.this);

        final Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        final FrameLayout frame = findViewById(R.id.frameContainer);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.nav_thu:
//                        KhoanTCFragment khoanTCFragment = new KhoanTCFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new KhoanTCFragment()).commit();
                        Toast.makeText(MainActivity.this,"Quản Lý Thư",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_chi:
//                        ChiFragment chiFragment = new ChiFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new ChiFragment()).commit();
                        Toast.makeText(MainActivity.this,"Quản Lý Chi",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_thongke:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new ChiFragment()).commit();
//                        Toast.makeText(MainActivity.this,"Quản Lý Chi",Toast.LENGTH_SHORT).show();
//                        Toast.makeText(MainActivity.this,"Quản Lý Thư",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_thoat:
//                        Toast.makeText(MainActivity.this,"Quản Lý Thư",Toast.LENGTH_SHORT).show();
                        break;
                }
                drawer.setSelected(true);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
