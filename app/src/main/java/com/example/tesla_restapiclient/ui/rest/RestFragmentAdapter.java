package com.example.tesla_restapiclient.ui.rest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RestFragmentAdapter extends FragmentStatePagerAdapter {


    public List<Fragment> fragmentList;

    public RestFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList<>();

    }

    public void setFragmentList(List<Fragment> fragmentList) {

        if(this.fragmentList.size()>0){
            this.fragmentList.clear();
        }
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        return fragmentList.get(position);

    }



    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
