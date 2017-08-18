package com.lemmetravel.seniorproject.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.lemmetravel.seniorproject.R;
import com.lemmetravel.seniorproject.fragments.homeFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FloatingActionButton fab;

    private static final int HOME = 0;
    private static final int MYARTICLE = 1;
    private static final int FRAGMENT_COUNT = 2;
    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
        setIndexView();

    }

    private void setIndexView() {
        FragmentManager fm = getSupportFragmentManager();
        fragments[HOME] = fm.findFragmentById(R.id.homeFragment);
        fragments[MYARTICLE] = fm.findFragmentById(R.id.myArticleFragment);
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            transaction.hide(fragments[i]);
        }
        transaction.commit();

        // Home
        showFragment(HOME, false);
    }

    private void initInstance() {

        // init Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // init Logo image
        ImageView logoImg = (ImageView) findViewById(R.id.logo_img);
        Picasso.with(this).load(R.drawable.logo_img).into(logoImg);

        // init navigation drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Fab button listener
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                /**Intent nextPage = new Intent(getApplicationContext(),addArticleActivity.class);
                nextPage.putExtra("PARAM", "test");
                startActivity(nextPage);**/
            }
        });

    }


    private void showFragment(int fragmentIndex, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (i == fragmentIndex) {
                transaction.show(fragments[i]);

            } else {
                transaction.hide(fragments[i]);
            }
        }
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        homeFragment homeFragment = new homeFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = null;
        Class fragmentClass = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                //fragmentClass = homeFragment.class;
                showFragment(HOME, false);
                fab.show();
                break;
            case R.id.nav_camera:
                //fragmentClass = homeFragment.class;
                showFragment(MYARTICLE, false);
                hideFab();
                break;
            case R.id.nav_gallery:
                //fragmentClass = homeFragment.class;
                showFragment(HOME, false);
                break;
            default:
                //fragmentClass = homeFragment.class;
                showFragment(HOME, false);
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.layout_fragment_container, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_optionsmenu, menu);
        return true;
    }

    private void hideFab() {
        fab.hide(new FloatingActionButton.OnVisibilityChangedListener() {

            @Override
            public void onHidden(FloatingActionButton fab) {
                super.onShown(fab);
                fab.setVisibility(View.INVISIBLE);
            }
        });
    }

}
