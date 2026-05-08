package com.FosemDefense.util;

import org.apache.commons.

import java.sql.Date;
import java.sql.Timestamp;

public class MyDateUtil {

    public static String timestampFormat(Timestamp timestamp){
        Date currentDate = new Date(timestamp.getTime());
        return DateFormatUtils.format(currentDate, "yyyy-MM-dd HH:mm");
    }
}
