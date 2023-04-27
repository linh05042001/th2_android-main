package com.example.thuchanh2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.thuchanh2.fragment.FragmntThongTin;
import com.example.thuchanh2.fragment.FragmntDanhSach;
import com.example.thuchanh2.fragment.FragmntSearch;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new FragmntDanhSach();
            case 1:return new FragmntThongTin();
            case 2:return new FragmntSearch();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
