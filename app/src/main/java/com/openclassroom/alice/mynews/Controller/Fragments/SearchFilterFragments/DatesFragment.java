package com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.openclassroom.alice.mynews.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DatesFragment extends Fragment  {

    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    @BindView(R.id.date_begin) EditText mBeginDateEditText;
    @BindView(R.id.date_end) EditText mEndDateEditText;

    private int last_button_clicked=0;
    private static final String KEY_BEGIN_DATE= "KEY_BEGIN_DATE";
    private static final String KEY_END_DATE= "KEY_END_DATE";

    public DatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dates, container, false);
        ButterKnife.bind(this, view);
        this.configureOnDateSet();
        return view;
    }

    @OnClick(R.id.begin_date_button)
    public void submit() {
        last_button_clicked=1;
        this.createAndShowDatePickerDialog();
    }

    @OnClick(R.id.end_date_button)
    public void setEndDate() {
        last_button_clicked=2;
        this.createAndShowDatePickerDialog();
    }

    private void createAndShowDatePickerDialog(){
        int startYear = Calendar.getInstance().get(Calendar.YEAR);
        int startMonth = Calendar.getInstance().get(Calendar.MONTH);
        int startDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                this.requireContext(),
                android.R.style.Theme_DeviceDefault_Dialog_NoActionBar,
                mOnDateSetListener,
                startYear,startMonth,startDay);
        if (dialog.getWindow()!=null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
    }

    private void configureOnDateSet(){
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (last_button_clicked==1){
                    mBeginDateEditText.setText(createDate(year, month, dayOfMonth));
                }
                if (last_button_clicked==2){
                    mEndDateEditText.setText(createDate(year, month, dayOfMonth));
                }
                last_button_clicked=0;
            }
        };
    }

    private String createDate(int year, int month, int dayOfMonth){
        month=month+1;
        String stringMonth=String.valueOf(month);
        if (month<10){
            stringMonth="0"+stringMonth;
        }
        String stringDay=String.valueOf(dayOfMonth);
        if (dayOfMonth<10){
            stringDay="0"+stringDay;
        }
        return stringDay + "/" + stringMonth + "/" + year;
    }

    public String getBeginDate(){
        return mBeginDateEditText.getText().toString();
    }

    public String getEndDate(){
        return mEndDateEditText.getText().toString();
    }

    public void displayDatesState(SharedPreferences mPreferences) {
        mBeginDateEditText.setText(mPreferences.getString(KEY_BEGIN_DATE, ""));
        mEndDateEditText.setText(mPreferences.getString(KEY_END_DATE, ""));
    }

    public void saveDatesState(SharedPreferences mPreferences){
        mPreferences.edit().putString(KEY_BEGIN_DATE, getBeginDate()).apply();
        mPreferences.edit().putString(KEY_END_DATE, getEndDate()).apply();
    }

}
