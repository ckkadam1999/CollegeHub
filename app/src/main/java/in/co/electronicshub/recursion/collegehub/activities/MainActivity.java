package in.co.electronicshub.recursion.collegehub.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.fragments.AddProject;
import in.co.electronicshub.recursion.collegehub.fragments.Home;
import in.co.electronicshub.recursion.collegehub.fragments.MyProjects;
import in.co.electronicshub.recursion.collegehub.fragments.Notifications;
import in.co.electronicshub.recursion.collegehub.fragments.Saved;
import in.co.electronicshub.recursion.collegehub.fragments.Settings;
import in.co.electronicshub.recursion.collegehub.utils.CustomToast;
import in.co.electronicshub.recursion.collegehub.utils.SessionManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawerLayout;
private FloatingActionButton fab;
private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Home()).commit();
        navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userName = headerView.findViewById(R.id.userName);
        TextView userPhone = headerView.findViewById(R.id.userPhone);
        ImageView userImage = headerView.findViewById(R.id.userImage);

        SessionManager sessionManager = new SessionManager(this);
        userPhone.setText(sessionManager.getPhone());
        userName.setText(sessionManager.getName());
        Glide.with(this).load(sessionManager.getImage()).into(userImage);
        }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment=null;
         switch (item.getItemId())
         {
             case R.id.home:
                 selectedFragment= new Home();
                 break;
             case R.id.add_project:
                 selectedFragment=new AddProject();
                 break;
             case R.id.my_projects:
                 selectedFragment=new MyProjects();
                 break;
             case R.id.saved:
                 selectedFragment= new Saved();
                 break;
             case R.id.notification:
                 selectedFragment= new Notifications();
                 break;
             case R.id.settings:
                 selectedFragment=new Settings();
                 break;
             case R.id.logout:
                 SessionManager sessionManager = new SessionManager(this);
                 sessionManager.setLogin(false);
                 Intent intent = new Intent(this, LoginActivity.class);
                 startActivity(intent);
                 finish();
                 break;
         }
         if(selectedFragment!=null) {
             getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
             drawerLayout.closeDrawer(GravityCompat.START);
         }
      return true;

    };
}