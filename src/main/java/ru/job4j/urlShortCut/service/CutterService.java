package ru.job4j.urlShortCut.service;

import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
public class CutterService {
    private final String
            ALLOWED_CHARS
            = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        CutterService cutterService = new CutterService();
        cutterService.generateCode();
    }

    public String generateCode() {
        StringBuffer sb = new StringBuffer(8);
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARS.length());
            char randomChar = ALLOWED_CHARS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
