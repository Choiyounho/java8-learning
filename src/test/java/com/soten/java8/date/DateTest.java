package com.soten.java8.date;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    @DisplayName("Date 실습")
    @Description("Date 는 (1) 처럼 mutable 한 객체이다. 변하기 쉬운 API 이기때문에 " +
            "다른 스레드와의 작업이 겹쳐지게 되면 데이터가 변할 수 있기 때문에 버그 발생할 여지가 많다.. ")
    void dateTest() throws InterruptedException {
        Date date = new Date();
        long time = date.getTime(); // 1970년 기준으로 현재까지의 ms
        System.out.println(date); // 현재 시간
        System.out.println(time);


        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date(); // (1) 3초 후로 셋팅
        System.out.println(after3Seconds);
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);
    }

    @Test
    @DisplayName("LocalDateTime")
    void localDateTimeTest() {
        LocalDate birthday = LocalDate.of(1994, 12, 6); // 특정한 시간
        LocalDate firthBirthday = birthday.plusDays(1);
        System.out.println(birthday);
        System.out.println(firthBirthday);

        LocalDateTime now = LocalDateTime.now(); // 내 정보의 시간
        System.out.println(now);
    }

    @Test
    @DisplayName("기계적인 시간")
    void instantTest() {
        Instant instant = Instant.now();
        System.out.println(instant); // 기준시를 가져옴

        ZoneId zone = ZoneId.systemDefault(); // 현재 내 위치
        System.out.println(zone);

        ZonedDateTime zonedDateTime = instant.atZone(zone); // 현재 내 위치의 시간
        System.out.println(zonedDateTime);
    }

    @Test
    @DisplayName("기간 표시")
    void periodTest() {
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2020, Month.DECEMBER, 25);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));
    }

    @Test
    @DisplayName("기계용 시간 비교")
    void durationTest() {
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);

        System.out.println(between.getSeconds());
    }

    @Test
    @DisplayName("시간 format")
    void formatTest() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(MMddyyyy));

        LocalDate parse = LocalDate.parse("12/06/1994", MMddyyyy);
        System.out.println(parse);
    }

}
