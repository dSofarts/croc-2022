package ru.croc.task10;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

public class PasswordCracker implements Callable<String> {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private String initialHash;
    private long begin;
    private long end;

    public PasswordCracker(long begin, long end, String initialHash) {
        this.initialHash = initialHash;
        this.begin = begin;
        this.end = end;
    }

    /**
     * Перевести число в 26-ричную систему исчесления (только из букв)
     * @param number число
     * @return число в 26-ричной системе исчесления
     */
    private String toAlphabetic(long number) {
        long quot = number / 26;
        long rem = number % 26;
        char letter = (char) ((int) 'A' + rem);
        if (quot == 0) {
            return "" + letter;
        } else {
            return toAlphabetic(quot - 1) + letter;
        }
    }

    @Override
    public String call() {
        for (long i = begin; i <= end; i++) {

            String password = toAlphabetic(i - 1).toLowerCase();

            // дополнить пароль до 7 символов при необходимости
            while (password.length() < 7) {
                password = "a" + password;
            }

            if (hashPassword(password).equals(initialHash)) {
                return password;
            }
        }
        return "";
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    public static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }
}
