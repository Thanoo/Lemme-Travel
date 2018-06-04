package com.lemmetravel.seniorproject.tabfragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lemmetravel.seniorproject.Model.TripData;
import com.lemmetravel.seniorproject.R;

import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class popularTabFragment extends Fragment {
    private RecyclerView trip_recyclerView;
    TripData[] tripDatas;

    public popularTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabfragment_popular, container, false);
        trip_recyclerView = rootView.findViewById(R.id.trip_list);
        trip_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        new FeedTripList().execute();

        return rootView;
    }

    public class FeedTripList extends AsyncTask<String, Void, String> {
        String result = null;

        public FeedTripList() {
            super();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(getApplication(),"Conecting . . .", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... lat_long) {

            final String trip_url = "http://10.0.2.2/LemmeMysql/TripList.php";
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(trip_url).build();

                Response response = client.newCall(request).execute();
                result ="" + response.body().string();
                Log.i("Test", "aaaaaaaaaaaaaaaaaa"+result);
            } catch (Exception e) {
                Log.i("Test", "asdf");
            }

            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<TripData>>(){}.getType();
            Collection<TripData> data = gson.fromJson(result, collectionType);
            tripDatas = data.toArray(new TripData[data.size()]);
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(getContext(),tripDatas[0].getTitle(), Toast.LENGTH_LONG).show();
            //trip_recyclerView.setAdapter(new TripRV(getActivity(),tripDatas));
        }


    }
}
