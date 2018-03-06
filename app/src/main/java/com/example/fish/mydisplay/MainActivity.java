package com.example.fish.mydisplay;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import custom.Cocktail;
import layout.FavoritesFragment;

//import android.app.Fragment;
//import android.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FavoritesFragment.OnFragmentInteractionListener, MainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        loadCocktails();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new MainFragment(), "main")
                .commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void loadCocktails() {
        final String TAG = "cocktailsGet";
        FirebaseApp.initializeApp(this);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(getString(R.string.db_cocktails_name))
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    public void onSuccess(QuerySnapshot documentSnapshots) {

                        try {

                            for (DocumentSnapshot document : documentSnapshots.getDocuments()) {

                                String id = document.getId();
                                String name = document.getString("name");
                                boolean alcoholic = false;
                                HashMap<String, String> recipeMap = new HashMap<>();
                                if (document.contains("alcoholic")) {
                                    alcoholic = document.getBoolean("alcoholic");
                                }
                                if (document.contains("recipe")) {
                                    recipeMap = (HashMap<String, String>) document.get("recipe");
                                }

                                Cocktail cocktail = new Cocktail(id, name, recipeMap, alcoholic);


                                Log.i(TAG, "document " + document.getId() + ": " + document.getData().toString());
                            }
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_favorites) {

            // set item as selected to persist highlight
            menuItem.setChecked(true);

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            int menuItemId = menuItem.getItemId();
            android.support.v4.app.Fragment favFrag = new FavoritesFragment();
            android.support.v4.app.FragmentManager frgMgr = getSupportFragmentManager();

            frgMgr.beginTransaction().replace(R.id.fragmentContainer, favFrag).commit();


            // Go to favorites fragment
        } else if (id == R.id.nav_find_cocktails) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_login) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
