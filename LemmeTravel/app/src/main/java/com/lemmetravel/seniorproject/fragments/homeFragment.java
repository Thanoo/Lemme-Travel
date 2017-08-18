package com.lemmetravel.seniorproject.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.adapters.homePagerAdapter;


public class homeFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        homePagerAdapter homePagerAdapter = new homePagerAdapter(getActivity(), getChildFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        viewPager.setOffscreenPageLimit(1);

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

}
