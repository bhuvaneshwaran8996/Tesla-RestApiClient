package com.example.tesla_restapiclient.ui.rest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.ActivityRestBinding;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.ui.base.BaseActivity;
import com.example.tesla_restapiclient.ui.rest.fcmrequest.FcmFragment;
import com.example.tesla_restapiclient.ui.rest.response.ResponseFragment;
import com.example.tesla_restapiclient.ui.rest.restRequest.FcmAdpater;
import com.example.tesla_restapiclient.ui.rest.restRequest.RestFragment;
import com.example.tesla_restapiclient.ui.splash.SplashViewModel;
import com.example.tesla_restapiclient.utils.AppLogger;
import com.example.tesla_restapiclient.utils.ScreenUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RestActivity extends BaseActivity<ActivityRestBinding, RestViewModel> implements RestNavigator {

    public RestViewModel restViewModel;
    public String selected = "rest";

    Toolbar mToolbar;
    DrawerLayout mDrawer;
    NavigationView mNavigationView;
    @Inject
    RestFragmentAdapter restFragmentAdapter;

    @Inject
    FcmAdpater fcmAdpater;
    ActivityRestBinding activityRestBinding;
    @Inject
    ViewModelProviderFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestBinding = getBinding();

        setUp();
        setUpViewPager();

    }

    private void setUpViewPager() {

//        setSupportActionBar(activityRestBinding.toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }

        activityRestBinding.tablayout.removeAllTabs();
        restFragmentAdapter.setFragmentList(getFragmentList());
        activityRestBinding.viewpager.setAdapter(restFragmentAdapter);
        activityRestBinding.tablayout.addTab(activityRestBinding.tablayout.newTab().setText(getString(R.string.request)));
        activityRestBinding.tablayout.addTab(activityRestBinding.tablayout.newTab().setText(getString(R.string.response)));
        activityRestBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activityRestBinding.viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        activityRestBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                activityRestBinding.tablayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.clear();
        if(selected.equalsIgnoreCase("rest")){
            fragmentList.add(RestFragment.newInstance());
            fragmentList.add(ResponseFragment.newInstance());
        }else{
            fragmentList.add(FcmFragment.newInstance());
            fragmentList.add(ResponseFragment.newInstance());
        }


        return fragmentList;
    }

    private void setUp() {
        mDrawer = activityRestBinding.drawerView;
        mToolbar = activityRestBinding.toolbar;
        mNavigationView = activityRestBinding.navigationView;


        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.syncState();
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawer.isDrawerVisible(GravityCompat.START)) {
                    mDrawer.closeDrawer(GravityCompat.START);
                } else {
                    mDrawer.openDrawer(GravityCompat.START);
                }
            }
        });
        setupNavMenu();
//        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
//        mMainViewModel.updateAppVersion(version);
//        mMainViewModel.onNavMenuCreated();
//        setupCardContainerView();
//        subscribeToLiveData();

      //  loadRestFragment();


    }

//    private void loadRestFragment() {
//        lockDrawer();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .disallowAddToBackStack()
//               // .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
//                .add(R.id.rly_main, RestFragment.newInstance(), RestFragment.TAG)
//                .commit();
//    }

    private void lockDrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

//    @Override
//    protected void performDependencyInjection() {
//
//        AndroidInjection.inject(this);
//    }

    @Override
    protected int getViewModelVariable() {

        return BR.viewModel;
    }

    @Override
    protected RestViewModel getViewModel() {
        restViewModel = ViewModelProviders.of(this, factory).get(RestViewModel.class);
        restViewModel.setNavigator(this);
        return restViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rest;
    }

    private void unlockDrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    private void setupNavMenu() {
//        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
//                R.layout.nav_header_main, mActivityMainBinding.navigationView, false);
//        mActivityMainBinding.navigationView.addHeaderView(navHeaderMainBinding.getRoot());
//        navHeaderMainBinding.setViewModel(mMainViewModel);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.navRest:
                                if(!selected.equalsIgnoreCase("rest")){
                                    selected = "rest";
                                    setUpViewPager();

                                }



                                return true;
                            case R.id.navFCM:

                                if(!selected.equalsIgnoreCase("fcm")){
                                    selected = "fcm";
                                    setUpViewPagerForFCM();

                                }



                                return true;
//                            case R.id.navItemFeed:
////
//                                return true;
//                            case R.id.navItemLogout:
//
//                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    private void setUpViewPagerForFCM() {

        fcmAdpater.setListFragment(getFragmentList());

        activityRestBinding.viewpager.setAdapter(fcmAdpater);

        activityRestBinding.tablayout.removeAllTabs();
        activityRestBinding.tablayout.addTab(activityRestBinding.tablayout.newTab().setText(getString(R.string.fcm_request)));
        activityRestBinding.tablayout.addTab(activityRestBinding.tablayout.newTab().setText(getString(R.string.response)));
        activityRestBinding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activityRestBinding.viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        activityRestBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                activityRestBinding.tablayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onFragmentDetached(String tag) {
        super.onFragmentDetached(tag);
    }

    @Override
    public void fuckKaviya() {


    }

}