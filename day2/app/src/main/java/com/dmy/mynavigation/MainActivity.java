package com.dmy.mynavigation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    FloatingActionButton mainFab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView, navController);

        initFAB();

    }


    public void initFAB() {
        mainFab = findViewById(R.id.fab_main);
        fab1 = findViewById(R.id.fab_home);
        fab2 = findViewById(R.id.fab_2);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_formFragment_to_anotherFragment);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_anotherFragment_to_formFragment);
            }
        });

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainFab.isExpanded()) {
                    mainFab.setExpanded(false);
                    collapseFABMenu();
                } else {
                    mainFab.setExpanded(true);
                    expendFABMenu();
                }
            }
        });
    }

    private void animate(boolean isForward) {
        int fromDegree = isForward ? 180 : 0;
        int toDegree = isForward ? 0 : 180;
        ValueAnimator animator = ValueAnimator.ofInt(fromDegree, toDegree);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                mainFab.setRotation(currentValue);
            }
        });
        animator.start();
    }

    private void expendFABMenu() {
        animate(true);
        fab1.show();
        fab2.show();

        fab1.animate().translationY(-
                getResources().getDimension(androidx.cardview.R.dimen.cardview_compat_inset_shadow));
        fab2.animate().translationY(-
                getResources().getDimension(androidx.cardview.R.dimen.cardview_compat_inset_shadow));
    }

    private void collapseFABMenu() {
        animate(false);
        fab1.hide();
        fab2.hide();

        fab1.animate().translationY(0);
        fab2.animate().translationY(0);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}