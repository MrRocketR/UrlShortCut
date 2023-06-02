package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public final class CutterService {
    private static final String
            ALLOWED_CHARS
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private CutterService() {
    }


    public static String generateCode() {
        StringBuffer sb = new StringBuffer(8);
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARS.length());
            char randomChar =  ALLOWED_CHARS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
