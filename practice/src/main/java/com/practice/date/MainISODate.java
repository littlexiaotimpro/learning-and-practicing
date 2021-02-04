package com.practice.date;


import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 日期测试
 */
public class MainISODate {

    public static void main(String[] args) throws ParseException {
        DateFormat dateInstance = StdDateFormat.getDateInstance();
        Date format = dateInstance.parse("2010-01-01T12:00:00+01:00");
        System.out.println(format);
    }
}
