package com.example.appstock2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appstock2.ui.gallery.GalleryFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    DrawerLayout drawerLayout;
    ImageView topdrawericon;
    NavigationView navigationView;
    FrameLayout hamd;
    static final float END_SCALE = 0.7f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //getSupportActionBar().hide();



        drawerLayout = findViewById(R.id.drawerlayoutid);
        navigationView = findViewById(R.id.navigationviewid);
        topdrawericon = findViewById(R.id.topdrawericon);
        hamd = findViewById(R.id.hamd);

        navigationDrawer();
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }




    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        topdrawericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }





    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor(getResources().getColor(R.color.purple_200));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                hamd.setScaleX(offsetScale);
                hamd.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = hamd.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                hamd.setTranslationX(xTranslation);
            }
        });

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                break;

            case R.id.nav_gallery:
                startActivity(new Intent(getApplicationContext(), ProductList.class));
                break;
            case R.id.nav_slideshow:
                break;
            /*case R.id.nav_addNews:
                break;
            case R.id.nav_payDonation:
                break;
            case R.id.nav_takeDonation:
                break;
            case R.id.nav_notification:
                break;
            case R.id.nav_letsTalk:
                break;
            case R.id.nav_rulesAndRegulation:
                break;
            case R.id.nav_logout:
                break;*/

        }
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
