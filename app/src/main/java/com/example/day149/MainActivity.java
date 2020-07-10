package com.example.day149;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.day149.fragment.CollectionFragment;
import com.example.day149.fragment.HomeFragment;
import com.example.day149.utils.ApkInstallUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tab)
    TabLayout tab;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private CollectionFragment collectionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();

        homeFragment = new HomeFragment();
        collectionFragment = new CollectionFragment();
        fragmentTransaction.add(R.id.ll,homeFragment);
        fragmentTransaction.add(R.id.ll,collectionFragment);
        fragmentTransaction.hide(collectionFragment).commit();
        tab.addTab(tab.newTab().setText("首页"));
        tab.addTab(tab.newTab().setText("下载"));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                int position = tab.getPosition();
                if (position==0){
                    fragmentTransaction.hide(collectionFragment).show(homeFragment).commit();
                }else{
                    fragmentTransaction.hide(homeFragment).show(collectionFragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ApkInstallUtil.UNKNOWN_CODE&&resultCode == 0){
            ApkInstallUtil.installApk(this,CollectionFragment.file);
        }
    }
}
