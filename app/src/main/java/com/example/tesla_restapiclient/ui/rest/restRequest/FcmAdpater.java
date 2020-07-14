package com.example.tesla_restapiclient.ui.rest.restRequest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FcmAdpater extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;

    public FcmAdpater(FragmentManager fragmentManager){
        super(fragmentManager);
        this.fragmentList = new ArrayList<>();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public void setListFragment(List<Fragment> fragmentList){

        this.fragmentList = fragmentList;

    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
