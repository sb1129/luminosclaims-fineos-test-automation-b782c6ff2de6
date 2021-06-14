package com.gb.fineos.page.utils;

import com.gb.fineos.domain.PageRequest;
import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class RandomData {


    private RandomData() {
        // Do nothing.
    }

    public static Faker getRandomDataService(final PageRequest request) {
        switch (request.getTestInstance()) {
            case AU:
            case ICARE:
                return new Faker(new Locale("en-AU"));
            case NZ:
                return new Faker(new Locale("en-NZ"));
            case UK:
                return new Faker(new Locale("en-GB"));
            default:
                throw new AssertionError("This test instance do not exist" + request.getTestInstance());
        }
    }

    public static String getCalculatedDateAndTime(final int days) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM");
        calendar.add(Calendar.DATE, days);
        Date calculatedDate = calendar.getTime();
        return dateFormat.format(calculatedDate);
    }
}
