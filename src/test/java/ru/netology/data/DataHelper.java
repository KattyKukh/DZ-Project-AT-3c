package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo validInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo invalidInfo() {
        String invalidLogin = faker.name().username();
        String invalidPass = faker.internet().password();
        return new AuthInfo(invalidLogin, invalidPass);
    }

    public static VerificationCode generateCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }


}
