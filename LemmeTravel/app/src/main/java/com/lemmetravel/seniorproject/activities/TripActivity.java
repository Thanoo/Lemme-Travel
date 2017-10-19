package com.lemmetravel.seniorproject.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lemmetravel.seniorproject.Model.AuthorData;
import com.lemmetravel.seniorproject.Model.ChapterData;
import com.lemmetravel.seniorproject.Model.TripData;
import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.adapters.recyclerviews.AuthorRV;
import com.lemmetravel.seniorproject.adapters.recyclerviews.ChapterRV;
import com.squareup.picasso.Picasso;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TripActivity extends AppCompatActivity {
    ImageView tripCover;
    TextView tripDes;
    TripData tripData;
    AuthorData[] authorDatas, coAutDatas;
    ChapterData[] chapterDatas;
    RecyclerView recyclerView, authorRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        tripData = (TripData) getIntent().getSerializableExtra("tripdatas");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.chapter_toolbar);
        toolbar.setTitle(tripData.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.chapter_collapsingToolbarLayout);
        //collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        final Typeface tf = Typeface.createFromAsset(getApplication().getAssets(), "fonts/thaisanslite_r1.ttf");
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);
        collapsingToolbarLayout.setExpandedTitleTypeface(tf);


        tripDes = (TextView) findViewById(R.id.trip_description);
        tripCover = (ImageView) findViewById(R.id.trip_cover);
        Picasso.with(this).load("http://10.0.2.2/lemmetravel/public/uploads/" + tripData.getCover_img()).resize(400,400).centerInside().into(tripCover);
        tripDes.setText(tripData.getDescription());

        new FeedChapterList().execute();
        recyclerView = (RecyclerView) findViewById(R.id.chapterRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);

        authorRV = (RecyclerView) findViewById(R.id.authorRV);
        authorRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        authorRV.setNestedScrollingEnabled(false);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public class FeedChapterList extends AsyncTask<String, Void, String> {
        String result = null;

        public FeedChapterList() {
            super();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(getApplication(),"Conecting . . .", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... lat_long) {

            final String trip_url = "http://10.0.2.2/LemmeMysql/ChapterList.php";
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("tripid", tripData.getTrip_id()).build();
                Request request = new Request.Builder().url(trip_url).post(requestBody).build();
                Response response = client.newCall(request).execute();
                result ="" + response.body().string();
                Log.i("Chapter", ""+result);
            } catch (Exception e) {
                Log.i("Test", "asdf");
            }
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<ChapterData>>(){}.getType();
            Collection<ChapterData> data = gson.fromJson(result, collectionType);
            chapterDatas = data.toArray(new ChapterData[data.size()]);

            final String author_url = "http://10.0.2.2/LemmeMysql/AuthorList.php";
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("tripid", tripData.getTrip_id()).build();
                Request request = new Request.Builder().url(author_url).post(requestBody).build();
                Response response = client.newCall(request).execute();
                result ="" + response.body().string();
                Log.i("username", ""+result);
            } catch (Exception e) {
                Log.i("Test", "asdf");
            }
            Type authorCollectionType = new TypeToken<Collection<AuthorData>>(){}.getType();
            Collection<AuthorData> author_data = gson.fromJson(result, authorCollectionType);
            authorDatas = author_data.toArray(new AuthorData[author_data.size()]);

            final String co_aut_url = "http://10.0.2.2/LemmeMysql/CoAuthorList.php";
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("tripid", tripData.getTrip_id()).build();
                Request request = new Request.Builder().url(co_aut_url).post(requestBody).build();
                Response response = client.newCall(request).execute();
                result ="" + response.body().string();
                Log.i("co-username ", ""+result);
            } catch (Exception e) {
                Log.i("Test", "asdf");
            }

            Type CoautCollectionType = new TypeToken<Collection<AuthorData>>(){}.getType();
            Collection<AuthorData> co_author_data = gson.fromJson(result, CoautCollectionType);
            if (!result.equals("[]")){
                coAutDatas = co_author_data.toArray(new AuthorData[co_author_data.size()]);
            }

            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(getApplicationContext(),coAutDatas[0].getUsername(), Toast.LENGTH_LONG).show();
            recyclerView.setAdapter(new ChapterRV(getApplication(),chapterDatas));
            authorRV.setAdapter(new AuthorRV(getApplication(),authorDatas,coAutDatas));
        }

    }
}
