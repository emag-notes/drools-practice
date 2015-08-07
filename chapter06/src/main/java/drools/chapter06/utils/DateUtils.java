package drools.chapter06.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author tanabe
 */
public class DateUtils {

  public static Date toDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date toDate(LocalDate localDate) {
    return toDate(localDate.atStartOfDay());
  }

}
