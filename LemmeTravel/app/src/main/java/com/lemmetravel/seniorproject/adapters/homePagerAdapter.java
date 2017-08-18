package com.lemmetravel.seniorproject.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.tabfragments.popularTabFragment;

public class homePagerAdapter  extends FragmentPagerAdapter {
    private Context mContext;
    private String poppular, news, recommend;
    private String tabTitles[];
    private popularTabFragment popularFragment;
    public homePagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
        poppular = mContext.getResources().getString(R.string.tab_popular);
        news = mContext.getResources().getString(R.string.tab_new);
        recommend = mContext.getResources().getString(R.string.tab_recomend);
        tabTitles = new String[] {poppular, news, recommend};
    }

    // determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new popularTabFragment();
        } else if (position == 1){
            return new popularTabFragment();
        } else if (position == 2){
            return new popularTabFragment();
        } else{
            return new popularTabFragment();
        }

    }

    // determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
