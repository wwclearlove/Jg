package cdictv.test.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtil {
    public static String show() {
        String date;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      date=df.format(new Date());
      return date;
    }
}