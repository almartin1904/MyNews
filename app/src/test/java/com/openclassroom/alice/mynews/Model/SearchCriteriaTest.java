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

    @Test
    public void createSearchCriteria(){
        List<String> categories = new ArrayList<String>();
        categories.add("Arts");
        categories.add("Business");
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/1996", categories);
        assertEquals("France", searchCriteria.getSearchTerm());
        assertEquals("19930419", searchCriteria.getBeginDateWithAdaptedFormat());
        assertEquals("19961010", searchCriteria.getEndDateWithAdaptedFormat());
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
    public void TestDateFormatIsOkWithNoDate(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "", "", new ArrayList<String>());
        assertTrue(searchCriteria.dateFormatIsOk());
    }

    @Test
    public void TestDateFormatIsOkWithTwoDateOk(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/1996", new ArrayList<String>());
        assertTrue(searchCriteria.dateFormatIsOk());
    }

    @Test
    public void TestDateFormatIsOkWithOneDateOk(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "19/04/1993", "10/10/96", new ArrayList<String>());
        assertFalse(searchCriteria.dateFormatIsOk());
    }

    @Test
    public void TestCompareDatesOk(){
        SearchCriteria searchCriteria = new SearchCriteria("", "12/12/2015", "26/12/2018", new ArrayList<String>());
        assertTrue(searchCriteria.compareDates());
    }

    @Test
    public void TestCompareDatesNOk(){
        SearchCriteria searchCriteria = new SearchCriteria("", "12/12/2015", "26/12/2008", new ArrayList<String>());
        assertFalse(searchCriteria.compareDates());
    }

    @Test
    public void TestCompareDatesEquals(){
        SearchCriteria searchCriteria = new SearchCriteria("", "12/12/2015", "12/12/2015", new ArrayList<String>());
        assertTrue(searchCriteria.compareDates());
    }

    @Test
    public void TestDateFormatWrongFormat(){
        SearchCriteria searchCriteria = new SearchCriteria("France", "888888888", "am6am6pmpm", new ArrayList<String>());
        assertFalse(searchCriteria.dateFormatIsOk());
    }

    @Test
    public void setSearchTerm(){
        SearchCriteria searchCriteria = new SearchCriteria("", "", "", null);
        searchCriteria.setSearchTerm("France");
        assertEquals("France", searchCriteria.getSearchTerm());
    }

    @Test
    public void setBeginDate(){
        SearchCriteria searchCriteria = new SearchCriteria("", "", "", null);
        searchCriteria.setBeginDate("19/04/1993");
        assertEquals("19/04/1993", searchCriteria.getBeginDate());
    }

    @Test
    public void setEndDate(){
        SearchCriteria searchCriteria = new SearchCriteria("", "", "", null);
        searchCriteria.setEndDate("19/04/1993");
        assertEquals("19/04/1993", searchCriteria.getEndDate());
    }

    @Test
    public void setCategories(){
        SearchCriteria searchCriteria = new SearchCriteria("", "", "", null);
        searchCriteria.setCategories(new ArrayList<String>());
        searchCriteria.getCategories().add("Politics");
        assertEquals("Politics", searchCriteria.getCategories().get(0));
    }
}
