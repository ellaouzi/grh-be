package com.fact.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class EncrytedPasswordUtils {

    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);
    }



    public static void main(String[] args) {
        String password = "admin";
        String encrytedPassword = encrytePassword(password);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.matches("admin", "$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u");
        System.out.println("Encryted Password: " + encrytedPassword);
        System.out.println("Encryted Password: " + encoder.matches("fact-secret", "$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG"));
        System.out.println("Encryted Password: " + encoder.matches(password, encrytedPassword));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = Calendar.getInstance().get(Calendar.YEAR);

        int year1 = Calendar.getInstance().get(Calendar.YEAR);
         int year2 = Year.now().getValue();
         System.out.println(year+" "+ year1+ " "+year2);

    }

}
