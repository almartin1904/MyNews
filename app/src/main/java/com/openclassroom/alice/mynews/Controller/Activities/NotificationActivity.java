package com.openclassroom.alice.mynews.Controller.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.CategoriesFragment;
import com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments.KeyWordFragment;
import com.openclassroom.alice.mynews.Controller.SendNotificationReceiver;
import com.openclassroom.alice.mynews.Model.SearchCriteria;
import com.openclassroom.alice.mynews.R;

import java.util.Calendar;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    KeyWordFragment mKeyWordFragment;
    CategoriesFragment mCategoriesFragment;
    Switch notificationSwitch;
    SharedPreferences mPreferences;
    SearchCriteria mSearchCriteria;


    public static final String KEY_PREFERENCES = "KEY_PREFERENCES";
    public static final String IS_CHECKED = "IS_CHECKED";
    private static final String TAG = "NotificationActivityTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationSwitch = findViewById(R.id.notification_button);
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (mKeyWordFragment.searchNOk() || mCategoriesFragment.searchNOk()){
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(NotificationActivity.this);
                        builder.setTitle(R.string.ErrorTitle);
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        });
                        builder.setMessage(R.string.missFilterMessage);
                        builder.show();
                        notificationSwitch.setChecked(false);
                    } else {
                        mCategoriesFragment.disableChange(true);
                        mKeyWordFragment.setEnabledKeyWord(true);
                        alarmSet();
                    }
                } else {
                    mCategoriesFragment.disableChange(false);
                    mKeyWordFragment.setEnabledKeyWord(false);
                    alarmOff();
                }
                saveState();

            }
        });
        mPreferences = getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE);
        configureToolbar();
        configureAndShowFragments();
    }

    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCategoriesFragment.displayCheckBoxState(mPreferences);
        mKeyWordFragment.displayKeyWordState(mPreferences);
        notificationSwitch.setChecked(mPreferences.getBoolean(IS_CHECKED, false));
    }

    private void configureAndShowFragments(){
        mKeyWordFragment = (KeyWordFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_keyword);
        mCategoriesFragment = (CategoriesFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_categories);

        if (mKeyWordFragment == null) {
            mKeyWordFragment = new KeyWordFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_keyword, mKeyWordFragment)
                    .commit();
        }
        if (mCategoriesFragment == null) {
            mCategoriesFragment = new CategoriesFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_categories, mCategoriesFragment)
                    .commit();

        }
    }

    private void alarmSet(){
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Log.d(TAG, "onPause: alarm set");
        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(NotificationActivity.this, SendNotificationReceiver.class);
        mSearchCriteria = createSearchCriteria();
        intent.putExtra(String.valueOf(R.string.SearchCriteriaExtra), mSearchCriteria);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void alarmOff(){
        Log.d(TAG, "onPause: alarm off");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), SendNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, 0);
        alarmManager.cancel(pendingIntent);
    }

    private void saveState() {
        mPreferences.edit().putBoolean(IS_CHECKED, notificationSwitch.isChecked()).apply();
        mKeyWordFragment.saveKeyWordState(mPreferences);
        mCategoriesFragment.saveCheckBoxState(mPreferences);
    }

    private SearchCriteria createSearchCriteria() {

        String queryTerm = mKeyWordFragment.getKeyKeyWord();
        List<String> categories=mCategoriesFragment.getListOfCategories();

        String beginDate = getCurrentDate();
        String endDate = getCurrentDate();

        return new SearchCriteria(queryTerm, beginDate, endDate, categories);
    }

    private String getCurrentDate(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
    }


}