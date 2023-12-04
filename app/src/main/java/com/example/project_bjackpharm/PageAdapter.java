package com.example.project_bjackpharm;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project_bjackpharm.fragments.Fragment_ListMedicine;
import com.example.project_bjackpharm.fragments.Fragment_Transaction;

public class PageAdapter extends FragmentPagerAdapter {


    private Fragment[] fragments = {
            new Fragment_ListMedicine(),
            new Fragment_Transaction()
    };

    private String [] titles = {
            "List Medicine", "Transaction"
    };

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
