package com.churchcac.pastorApp.model.requestDTO;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@Service
public class EmailValidator implements Predicate<String> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";


    @Override
    public boolean test(String s) {
        //Regex to validate email
        return s.matches(EMAIL_REGEX);
    }
}
