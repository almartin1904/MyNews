package com.openclassroom.alice.mynews.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Alice on 17 December 2018.
 */

public class SearchCriteriaTest {
    //test création
    @Test
    public void createSearchCriteria(){
        List<String> categories = new ArrayList<String>();
        categories.add("Arts");
        categories.add("Business");
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/1996", categories);
        assertEquals(searchCriteria.getSearchTerm(), "France");
        assertEquals("19930419", searchCriteria.getBeginDate());
        assertEquals("19961010", searchCriteria.getEndDate());
        assertEquals("Arts", searchCriteria.getCategories().get(0));
    }

    //test adaptFormat
    @Test
    public void TestAdaptedFormatWithDates(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/1996", new ArrayList<String>());
        assertEquals("19930419", searchCriteria.getBeginDateWithAdaptedFormat());
        assertEquals("19961010", searchCriteria.getEndDateWithAdaptedFormat());
    }

    @Test
    public void TestAdaptedFormatWithoutDates(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "", "", new ArrayList<String>());
        assertEquals("18000101", searchCriteria.getBeginDateWithAdaptedFormat());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        assertEquals(String.valueOf(year)+String.valueOf(month)+String.valueOf(day), searchCriteria.getEndDateWithAdaptedFormat());
    }

    @Test
    public void TestSerializedCategories(){
        List<String> categories = new ArrayList<>();
        categories.add("Arts");
        categories.add("Business");
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/1996", categories);
        assertEquals("section_name:(\"Arts\" \"Business\" )", searchCriteria.getSerializedCategories());
    }

    @Test
    public void TestDateFormatIsOkwithNoDate(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "", "", new ArrayList<String>());
        assertTrue(searchCriteria.dateFormatIsOk());
    }

    @Test
    public void TestDateFormatIsOkwithTwoDateOk(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/1996", new ArrayList<String>());
        assertTrue(searchCriteria.dateFormatIsOk());
    }

    @Test
    public void TestDateFormatIsOkwithOneDateOk(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/96", new ArrayList<String>());
        assertFalse(searchCriteria.dateFormatIsOk());
    }
}
