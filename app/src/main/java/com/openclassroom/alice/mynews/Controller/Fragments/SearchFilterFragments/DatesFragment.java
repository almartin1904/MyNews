package com.openclassroom.alice.mynews.Controller.Fragments.SearchFilterFragments;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.openclassroom.alice.mynews.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatesFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    private int startYear, startMonth, startDay;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    @BindView(R.id.date_begin) EditText beginDateEditText;
    @BindView(R.id.date_end) EditText endDateEditText;
    @BindView(R.id.begin_date_button) ImageButton beginDateButton;
    @BindView(R.id.end_date_button) ImageButton endDateButton;

    private int last_button_clicked=0;

    public DatesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        startYear = Calendar.getInstance().get(Calendar.YEAR);
        startMonth = Calendar.getInstance().get(Calendar.MONTH);
        startDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        View view = inflater.inflate(R.layout.fragment_dates, container, false);
        ButterKnife.bind(this, view);

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = dayOfMonth + "/" + month + "/" + year;
                if (last_button_clicked==1){
                    beginDateEditText.setText(date);
                }
                if (last_button_clicked==2){
                    endDateEditText.setText(date);
                }
                last_button_clicked=0;
            }
        };

        return view;
    }

    @OnClick(R.id.begin_date_button)
    public void submit() {
        last_button_clicked=1;
        createAndShowDatePickerDialog();
    }

    @OnClick(R.id.end_date_button)
    public void setEndDate() {
        last_button_clicked=2;
        createAndShowDatePickerDialog();
    }

    //Theme_Holo_Dialog_MinWidth

    private void createAndShowDatePickerDialog(){
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    }

    public String getBeginDate(){
        return beginDateEditText.getText().toString();
    }

    public String getEndDate(){
        return endDateEditText.getText().toString();
    }

}
