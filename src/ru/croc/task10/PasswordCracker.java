package ru.croc.task10;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCracker implements Runnable {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private static final String HASH_PASS = "40682260CC011947FC2D0B1A927138C5";

    private final int passLength;
    private long begin;
    private long end;
    private static volatile boolean passFounded = false;

    public PasswordCracker(int threadNumber, int numberOfThreads, int passLength) {
        this.passLength = passLength;

        long numberOfOptions = (long) Math.pow(26, passLength);
        begin = (numberOfOptions * threadNumber) / numberOfThreads;
        end = (numberOfOptions * (threadNumber + 1)) / numberOfThreads - 1;
    }

    private StringBuilder generatePass(long n) {
        int[] passASCII = new int[passLength];
        for (int i = 0; i < passLength; i++) {
            passASCII[i] = (int) (n % 26) + 'a';
            n /= passLength;
        }

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passLength; i++) {
            password.append((char) (passASCII[i]));
        }

        return password;
    }

    @Override
    public void run() {
        while (!passFounded) {
            for (long i = begin; i < end; i++) {
                String password = generatePass(i).toString();
                if (hashPassword(password).equals(HASH_PASS)) {
                    System.out.println("Пароль: " + password);
                    passFounded = true;
                    break;
                }
            }
        }
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
