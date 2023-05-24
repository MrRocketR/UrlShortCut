package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CutterService {
    private final String
            allowedChars
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    public String generateCode() {
        StringBuffer sb = new StringBuffer(8);
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar =  allowedChars.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
