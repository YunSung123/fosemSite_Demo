package com.FosemDefense.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Date;
import java.sql.Timestamp;

// 날짜/시간 포맷 변환용 유틸 클래스
public class MyDateUtil {

    public static String timestampFormat(Timestamp timestamp){
        Date currentDate = new Date(timestamp.getTime());
        return DateFormatUtils.format(currentDate, "yyyy-MM-dd HH:mm");
    }
}
