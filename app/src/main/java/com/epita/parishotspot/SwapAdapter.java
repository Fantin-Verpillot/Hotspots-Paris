package com.epita.parishotspot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SwapAdapter extends FragmentPagerAdapter {

    public SwapAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }
}