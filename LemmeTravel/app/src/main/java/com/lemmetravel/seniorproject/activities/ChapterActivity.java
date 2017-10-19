package com.lemmetravel.seniorproject.activities;

import android.content.Context;
import android.graphics.Typeface;
//import android.support.design.widget.CollapsingToolbarLayout;
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
import com.lemmetravel.seniorproject.Model.ChapterData;
import com.lemmetravel.seniorproject.Model.EventData;
import com.lemmetravel.seniorproject.Model.TripData;
import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.adapters.recyclerviews.ChapterRV;
import com.lemmetravel.seniorproject.adapters.recyclerviews.EventRV;
import com.lemmetravel.seniorproject.adapters.recyclerviews.TripRV;
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

public class ChapterActivity extends AppCompatActivity {
    ImageView tripCover;
    TextView tripDes;
    EventData[] eventDatas;
    ChapterData chapterData;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        chapterData = (ChapterData) getIntent().getSerializableExtra("chapterdata");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.chapter_toolbar);
        toolbar.setTitle(chapterData.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.chapter_collapsingToolbarLayout);
        //collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        final Typeface tf = Typeface.createFromAsset(getApplication().getAssets(), "fonts/thaisanslite_r1.ttf");
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);
        collapsingToolbarLayout.setExpandedTitleTypeface(tf);


        tripDes = (TextView) findViewById(R.id.trip_description);
        tripCover = (ImageView) findViewById(R.id.trip_cover);
        Picasso.with(this).load("http://10.0.2.2/lemmetravel/public/uploads/" + chapterData.getCover_img()).fit().centerInside().into(tripCover);
        tripDes.setText(chapterData.getDescription());

        recyclerView = (RecyclerView) findViewById(R.id.eventRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        new FeedEventList().execute();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public class FeedEventList extends AsyncTask<String, Void, String> {
        String result = null;

        public FeedEventList() {
            super();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(getApplication(),"Conecting . . .", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... lat_long) {

            final String trip_url = "http://10.0.2.2/LemmeMysql/EventList.php";
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("chapterid", chapterData.getChapter_id())
                        .build();

                Request request = new Request.Builder()
                        .url(trip_url)
                        .post(requestBody)
                        .build();

                //Request request = new Request.Builder().url(trip_url).build();

                Response response = client.newCall(request).execute();
                result ="" + response.body().string();
                Log.i("Test", "aaaaaaaaaaaaaaaaaa"+result);
            } catch (Exception e) {
                Log.i("Test", "asdf");
            }

            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<EventData>>(){}.getType();
            Collection<EventData> data = gson.fromJson(result, collectionType);
            eventDatas = data.toArray(new EventData[data.size()]);
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(getApplication(),chapterData.getTitle(), Toast.LENGTH_LONG).show();
            recyclerView.setAdapter(new EventRV(getApplication(), eventDatas, chapterData.getTitle()));
        }
    }

    // Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
