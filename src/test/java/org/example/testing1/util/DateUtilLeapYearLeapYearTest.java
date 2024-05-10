package org.example.testing1.util;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateUtilLeapYearLeapYearTest {
    @Test
    public void return_true_when_yeardivby400(){
        assertTrue(DateUtilLeapYear.isLeapYear(1600));
        assertTrue(DateUtilLeapYear.isLeapYear(2000));
        assertTrue(DateUtilLeapYear.isLeapYear(2400));
    }
    @Test
    public void return_false_when_yeardivby100_not400(){
        assertFalse(DateUtilLeapYear.isLeapYear(1700));
        assertFalse(DateUtilLeapYear.isLeapYear(1800));
        assertFalse(DateUtilLeapYear.isLeapYear(1900));
    }
    @Test
    public void return_false_when_yearnotdivby4(){
        assertFalse(DateUtilLeapYear.isLeapYear(2017));
        assertFalse(DateUtilLeapYear.isLeapYear(2018));
        assertFalse(DateUtilLeapYear.isLeapYear(2019));
    }
}