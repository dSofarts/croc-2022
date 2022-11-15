package ru.croc.task10;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCracker implements Runnable {

    private String initialHash;
    private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public PasswordCracker(String initialHash) {
        this.initialHash = initialHash;
    }

    @Override
    public void run() {
        System.out.println(searchPassword());
    }

    private String searchPassword() {
        String password = "";
        for (char a : ALPHABET) {
            for (char b : ALPHABET) {
                for (char c : ALPHABET) {
                    for (char d : ALPHABET) {
                        password += d;
                        for (char e : ALPHABET) {
                            password += e;
                            for (char f : ALPHABET) {
                                password += f;
                                for (char g : ALPHABET) {
                                    password = Character.toString(a) + Character.toString(b) +
                                            Character.toString(c) + Character.toString(d) +
                                            Character.toString(e) + Character.toString(f) +
                                            Character.toString(g);
                                    if (initialHash.equals(hashPassword(password))) {
                                        return password;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "Пароль не найден";
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
