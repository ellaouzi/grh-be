package com.fact.util;

public class StringFormat {
    public static void main(String[] args) {
        int heure = 2;
        int minute =9;

        String.format("|%02d|", 9);

        System.out.println( String.format("%02d", heure) + ":" +  String.format("%02d", minute));
    }
}
