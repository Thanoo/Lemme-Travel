package com.lemmetravel.seniorproject.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lemmetravel.seniorproject.Model.ChapterData;
import com.lemmetravel.seniorproject.Model.EventData;
import com.lemmetravel.seniorproject.R;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class EventActivity extends AppCompatActivity {

    EventData eventData;
    String chapterTitle;
    HtmlTextView eventContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventData = (EventData) getIntent().getSerializableExtra("eventdata");
        chapterTitle = (String) getIntent().getSerializableExtra("chaptertitle");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.chapter_toolbar);
        toolbar.setTitle(chapterTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        eventContent = (HtmlTextView) findViewById(R.id.eventContent);
        eventContent.setHtml(eventData.getContent(), new HtmlHttpImageGetter(eventContent));
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
}
