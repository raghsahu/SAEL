package dev.raghav.sael;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.raghav.sael.Connectivity.Connectivity;
import dev.raghav.sael.Connectivity.SessionManager;
import dev.raghav.sael.Connectivity.SharedPref;
import dev.raghav.sael.Fragment.Support_fraggment;
import dev.raghav.sael.Fragment.fragment_level;
import dev.raghav.sael.Fragment.fragment_text_home;

import static dev.raghav.sael.Fragment.fragment_start1.intNext;
import static dev.raghav.sael.Fragment.fragment_start1.link_level_question;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager manager;

    TextView Nav_text_name,Nav_text_email;
    CircleImageView Profile_img;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().hide();

        manager=new SessionManager(Main2Activity.this);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new fragment_text_home());
        tx.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        Nav_text_name=headerView.findViewById(R.id.nav_hedder_name);
        Nav_text_email=headerView.findViewById(R.id.nav_hedder_email);
        Profile_img=headerView.findViewById(R.id.profile_image12);

        Nav_text_name.setText(SharedPref.getFirstname(Main2Activity.this));
        Nav_text_email.setText(SharedPref.getEmail(Main2Activity.this));

        if (SharedPref.getProfileImage(Main2Activity.this).length()!=0)
        {
            Picasso.get()
                    .load("http://infocentroid.us/Star_Academy/uploads/"+SharedPref.getProfileImage(Main2Activity.this))
                    .into(Main2Activity.this.Profile_img);
        }
        else {
            Picasso.get()
                    .load(R.drawable.prof)
                    .into(Main2Activity.this.Profile_img);
        }

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(intNext==0)
            {
                link_level_question.clear();
                intNext=0;

            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this).setTitle("Learning English").
                setMessage("Are you sure, you want to Leave this Exercise");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        link_level_question.clear();
                        intNext=0;
                        finish();
                        Intent intent = new Intent(Main2Activity.this , Main2Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCancelable(true);
            }
            super.onBackPressed();
//            if(!link_level_question.isEmpty())
//            {
//                intNext=0;
//                link_level_question.clear();
//            }else {
//                Toast.makeText(this, "back pressed", Toast.LENGTH_SHORT).show();
//            }
//            intNext=0;
////            if (link_level_question.isEmpty()){
////                Toast.makeText(this, "levellll", Toast.LENGTH_SHORT).show();
////            }else {
////                link_level_question.clear();
////            }
//            link_level_question.clear();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, new fragment_text_home());
            tx.commit();
            intNext =0;
            link_level_question.clear();

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            intNext =0;
            link_level_question.clear();

            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, new fragment_level());
            tx.commit();


        } else if (id == R.id.nav_slideshow) {
            intNext =0;
            link_level_question.clear();

        } else if (id == R.id.nav_manage) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, new Support_fraggment());
            tx.commit();
            intNext =0;
            link_level_question.clear();

        } else if (id == R.id.nav_share) {
            intNext =0;
            link_level_question.clear();

            if (Connectivity.isNetworkAvailable(Main2Activity.this)){
                //shareApplication();

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }

            }else {
                Toast.makeText(Main2Activity.this, "No Internet", Toast.LENGTH_SHORT).show();

            }

        } else if (id == R.id.nav_logout) {
            intNext =0;
            link_level_question.clear();
            //            if (Connectivity.isNetworkAvailable(Main2Activity.this)){

            final AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this).setTitle("Learning English")
                    .setMessage("Are you sure, you want to logout this app");

            dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    exitLauncher();
                }

                private void exitLauncher() {
                    Intent intent=new Intent(Main2Activity.this,Login_Activity.class);
                    startActivity(intent);
                    //finish();
                    manager.logoutUser();
                   // SharedPref.setProfileImage(Main2Activity.this,"");
                    finish();
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();

        }else if (id == R.id.nav_profile) {
            intNext =0;
            link_level_question.clear();

            Intent intent=new Intent(Main2Activity.this,Profile_Update.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   // private String shareApplication() {

//        ApplicationInfo app = getApplicationContext().getApplicationInfo();
//        String filePath = app.sourceDir;
//
//        int lastDot = 0;
//        String packageName = app.packageName;
//        lastDot= packageName.lastIndexOf(".");
//        name = packageName.substring(lastDot + 1);
//
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
//        startActivity(Intent.createChooser(intent, "Share app via"));
//
//        return name;


// star.academy181@gmail.com


   // }


}
