package ru.croc.task10;

public class Task10 {

    public static void main(String[] args) {

        String hashOldPassword = "40682260CC011947FC2D0B1A927138C5";


        PasswordCracker passwordCracker = new PasswordCracker(hashOldPassword);
        passwordCracker.run();

    }
}
