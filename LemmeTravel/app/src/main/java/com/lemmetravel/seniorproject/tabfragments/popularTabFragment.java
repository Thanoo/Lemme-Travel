package com.lemmetravel.seniorproject.tabfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.adapters.RectclerViewAdapter;

public class popularTabFragment extends Fragment {

    private RecyclerView trip_recyclerView;

    public popularTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabfragment_popular, container, false);
        trip_recyclerView = (RecyclerView) rootView.findViewById(R.id.trip_list);
        trip_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        trip_recyclerView.setAdapter(new RectclerViewAdapter(getActivity()));

        return rootView;
    }

}
