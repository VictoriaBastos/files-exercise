package service;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class SignService {

    public Integer getAge(LocalDateTime localDateTime) {
        return Period.between(localDateTime.toLocalDate(), LocalDate.now()).getYears();
    }

    public boolean isLeapYear(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().isLeapYear();
    }

    public String format(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public ZoneOffset timeZone(LocalDateTime localDateTime, String zone) {
        return ZoneId.of(zone).getRules().getOffset(localDateTime);
    }

}
