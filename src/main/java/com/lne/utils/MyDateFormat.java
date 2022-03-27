package com.lne.utils;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: MyDateFormat
 * Author:   fengsulin
 * Date:     2022/3/27 0:00
 * Description:
 */
public class MyDateFormat implements Converter<String, Date> {
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public Date convert(String source) {
        Date date = null;
        try {
            date = format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
