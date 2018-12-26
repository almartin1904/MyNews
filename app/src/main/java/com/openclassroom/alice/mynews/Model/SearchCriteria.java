package com.openclassroom.alice.mynews.Model;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alice on 12 December 2018.
 */
public class SearchCriteria implements Serializable {
    private String mSearchTerm;
    private String mBeginDate;
    private String mEndDate;
    private List<String> mCategories;

    public static final String TODAY = "today";

    //--------------
    //CONSTRUCTOR
    //--------------

    public SearchCriteria(String searchTerm, String beginDate, String endDate, List<String> categories){
        this.mSearchTerm = searchTerm;
        this.mCategories = categories;
        switch (beginDate){
            case TODAY:
                mBeginDate=getCurrentDate();
                break;
            case "":
                mBeginDate="01/01/1800";
                break;
            default:
                mBeginDate=beginDate;
        }
        if (endDate.equals(TODAY) || endDate.equals("")){
            this.mEndDate=getCurrentDate();
        } else {
            this.mEndDate=endDate;
        }
    }

    //-------------------
    //GETTER AND SETTER
    //-------------------

    public String getSearchTerm() {
        return mSearchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        mSearchTerm = searchTerm;
    }

    public String getBeginDate() {
        return mBeginDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setBeginDate(String beginDate) {
        mBeginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    public void setCategories(List<String> categories) {
        mCategories = categories;
    }

    //-----------------------------
    //MODIFY FORMAT OF PARAMETERS
    //-----------------------------

    public String getEndDateWithAdaptedFormat() {
        return adaptDateFormat(mEndDate);
    }

    public String getBeginDateWithAdaptedFormat() {
        return adaptDateFormat(mBeginDate);
    }

    private String adaptDateFormat(String date){
        if (date.length()>=10){
            date = date.substring(6,10)+date.substring(3,5)+date.substring(0,2);
        }
        return date;
    }

    public String getSerializedCategories() {
        StringBuffer categories = new StringBuffer("section_name:(");
        for (int i=0; i<mCategories.size(); i++){
            categories= categories.append("\"").append(mCategories.get(i)).append("\" ");
        }
        categories.append(")");
        return categories.toString();
    }

    //----------------
    //CHECK FORMAT
    //----------------

    public boolean dateFormatIsOk(){
        return testDateFormat(mBeginDate) && testDateFormat(mEndDate);
    }

    private boolean testDateFormat(String date) {
        //Format should be DD/MM/YYYY
        boolean result=true;
        if (date.length()==0){
            return true;
        } else {
            if (date.length()!=10){
                return false;
            }
        }
        if (!Character.isDigit(date.charAt(0))){
            result=false;
        }
        if (!Character.isDigit(date.charAt(1))){
            result=false;
        }
        if (date.charAt(2)!='/'){
            result=false;
        }
        if (!Character.isDigit(date.charAt(3))){
            result=false;
        }
        if (!Character.isDigit(date.charAt(4))){
            result=false;
        }
        if (date.charAt(5)!='/'){
            result=false;
        }
        if (!Character.isDigit(date.charAt(6))){
            result=false;
        }
        if (!Character.isDigit(date.charAt(7))){
            result=false;
        }
        if (!Character.isDigit(date.charAt(8))){
            result=false;
        }
        if (!Character.isDigit(date.charAt(9))){
            result=false;
        }
        return result;
    }

    private String getCurrentDate(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
    }

    public boolean compareDates(){
        if (!mBeginDate.equals("") && !mEndDate.equals("")){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date beginDate = formatter.parse(getBeginDate());
                Date endDate = formatter.parse(getEndDate());
                return beginDate.compareTo(endDate)<=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return true;
        }
    }
}
