package com.spring.baseSetting.controller;


import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        System.out.println(now.getYear());
    }
}
