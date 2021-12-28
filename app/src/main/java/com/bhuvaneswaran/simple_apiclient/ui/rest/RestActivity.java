package com.bhuvaneswaran.simple_apiclient.ui.rest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.library.baseAdapters.BR;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bhuvaneswaran.simple_apiclient.BuildConfig;
import com.bhuvaneswaran.simple_apiclient.R;
import com.bhuvaneswaran.simple_apiclient.databinding.ActivityRestBinding;
import com.bhuvaneswaran.simple_apiclient.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_apiclient.di.ViewModelProviderFactory;
import com.bhuvaneswaran.simple_apiclient.ui.base.BaseActivity;
import com.bhuvaneswaran.simple_apiclient.ui.history.HistoryFragment;
import com.bhuvaneswaran.simple_apiclient.ui.rest.fcmrequest.FcmFragment;
import com.bhuvaneswaran.simple_apiclient.ui.rest.response.ResponseFragment;
import com.bhuvaneswaran.simple_apiclient.ui.rest.restRequest.FcmAdpater;
import com.bhuvaneswaran.simple_apiclient.ui.rest.restRequest.RestFragment;
import com.bhuvaneswaran.simple_apiclient.ui.settings.SettingsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.view.View.GONE;

public class RestActivity extends BaseActivity<ActivityRestBinding, RestViewModel> implements RestNavigator {

    public RestViewModel restViewModel;
    public String selected = "rest";

    @Inject
    HistoryDao historyDao;

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
    ProgressDialog progressDialog;
    public String bodyResponse;
    public String headerResponse;
    public String requestCode;
    public String requesttime;

    ActionBarDrawerToggle mDrawerToggle;

    public int currentPagerPostion;
    ResponseFragment responseFragment;
    public Bundle restBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestBinding = getBinding();
//        restBundle  = new Bundle();
//        restBundle.putString("Body",bodyResponse);
//        restBundle.putString("Header",headerResponse);
        String versionName = BuildConfig.VERSION_NAME;
        binding.toolbar.setTitle(getResources().getString(R.string.app_name));
//        getSupportActionBar().setTitle("REST Request");


        if (savedInstanceState != null) {
            currentPagerPostion = savedInstanceState.getInt("position", 0);
            requesttime = savedInstanceState.getString("requesttime");
            requestCode = savedInstanceState.getString("requestCode");
        }


        setUp();
        setUpViewPager();
        if (currentPagerPostion > 0) {
            binding.viewpager.setCurrentItem(currentPagerPostion);
        }


//        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                HistoryFragment historyFragment  =(HistoryFragment) getSupportFragmentManager().findFragmentByTag("HistoryFragment");
//                if(historyFragment!=null && historyFragment.isVisible()){
////                    getSupportFragmentManager().popBackStack();
//                    historyFragment.destroy();
//                    binding.lnrMain.setVisibility(GONE);
//                    binding.lnrViewpager.setVisibility(View.VISIBLE);
//                    getSupportActionBar().setTitle("Tesla-Restclient");
//                    mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);
//                    historyFragment = null;
//
//                }else{
//
//                    if (mDrawer != null) {
//                        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
//                    }
//                }
//            }
//        });
    }

    public void opendrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPagerPostion);
        outState.putString("requestCode", requestCode);
        outState.putString("requesttime", requesttime);
        outState.putString("bodyResponse", bodyResponse);
        outState.putString("headerResponse", headerResponse);
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
        activityRestBinding.viewpager.setOffscreenPageLimit(2);

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

                currentPagerPostion = position;
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
        RestFragment restFragment = RestFragment.newInstance();

        restFragment.setRetainInstance(true);
        responseFragment = ResponseFragment.newInstance();

        responseFragment.setRetainInstance(true);
        responseFragment.setArguments(restBundle);

        if (selected.equalsIgnoreCase("rest")) {
            fragmentList.add(restFragment);
            fragmentList.add(responseFragment);
        } else {
            fragmentList.add(FcmFragment.newInstance());
            fragmentList.add(responseFragment);
        }


        return fragmentList;
    }

    private void setUp() {
        mDrawer = activityRestBinding.drawerView;
        mToolbar = activityRestBinding.toolbar;
        mNavigationView = activityRestBinding.navigationView;


        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        //  mDrawerToggle.syncState();
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

    public void lockDrawer() {
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
                                binding.lnrViewpager.setVisibility(View.VISIBLE);
                                binding.lnrMain.setVisibility(GONE);


                                if (!selected.equalsIgnoreCase("rest")) {
                                    selected = "rest";

                                    setUpViewPager();
                                }
                                return true;
                            case R.id.navFCM:

//                                if (!selected.equalsIgnoreCase("fcm")) {
//                                    selected = "fcm";
//                                    //   setUpViewPagerForFCM();
//
//
//                                }

                                Toast.makeText(RestActivity.this, "coming soon!", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.navSettings:
                                Intent intent = new Intent(RestActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.navHistory:

//                                Intent intenthistory  = new Intent(RestActivity.this, HistoryActivity.class);
//                                startActivity(intenthistory);
                                //   binding.viewpager.setVisibility(GONE);

                                binding.lnrViewpager.setVisibility(GONE);
                                binding.lnrMain.setVisibility(View.VISIBLE);
//                                getSupportActionBar().setTitle("Settings");
//                                mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

                                HistoryFragment historyFragment = HistoryFragment.newInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.lnr_main, historyFragment, "HistoryFragment")
                                        .addToBackStack(null)
                                        .commit();

                                return true;

                            case R.id.bugfeatureNav:
//                                startActivity(new Intent(RestActivity.this, AboutActivity.class));
                                sendEmail();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    protected void sendEmail() {
        Log.i("Send email", "bhuvaneshwaran8996@gmail.com");
        String[] TO = {"bhuvaneshwaran8996@gmail.com"};
//        String[] CC = {"cccgvg"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //    emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            //  Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(RestActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
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
    public void restNav() {


    }


    @Override
    public void onBackPressed() {

        if (binding.drawerView.isDrawerOpen(GravityCompat.START)) {
            binding.drawerView.closeDrawer(GravityCompat.START);
        } else {
            finish();
            //  super.onBackPressed();
        }


    }

    public void setResponseFragmentSuccesRsult() {

        responseFragment.setRestSuccessResults(bodyResponse, headerResponse, requestCode, requesttime);
    }

    public HistoryDao getHistoryDao() {
        return historyDao;
    }
}