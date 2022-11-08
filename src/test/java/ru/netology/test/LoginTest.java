package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;


public class LoginTest {
    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void cleanUp() {
        closeWindow();
    }

    @AfterAll
    static void cleanUpBD() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should login if valid user info")
    void successLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var validUserInfo = DataHelper.validInfo();
        var verificationPage = loginPage.login(validUserInfo);
        var verificationCode = SQLHelper.getVerificationCode(validUserInfo);
        var dashboardPage = verificationPage.verify(verificationCode);
    }

    @Test
    @DisplayName("Should not login if invalid user info")
    void failedLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var invalidUserInfo = DataHelper.invalidInfo();
        loginPage.login(invalidUserInfo);
        loginPage.showErrorLoginMessage();
    }

    @Test
    @DisplayName("Should not login if verification code is invalid")
    void failedVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var validUserInfo = DataHelper.validInfo();
        var verificationPage = loginPage.login(validUserInfo);
        var invalidVerificationCode = DataHelper.generateCode();
        verificationPage.enterCodeAndClick(invalidVerificationCode);
        verificationPage.showErrorVerifyMessage();
    }

    @Test
    @DisplayName("Should not login if invalid user login")
    void failedLoginIfInvalidUserLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var invalidUserInfo = DataHelper.infoWithInvalidLogin();
        loginPage.login(invalidUserInfo);
        loginPage.showErrorLoginMessage();
    }

    @Test
    @DisplayName("Should not login if password is invalid 3 times")
    void failedLoginIfPasswordInvalid3Times() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var invalidUserInfo1 = DataHelper.infoWithInvalidPassword();
        var invalidUserInfo2 = DataHelper.infoWithInvalidPassword();
        var invalidUserInfo3 = DataHelper.infoWithInvalidPassword();
        loginPage.login(invalidUserInfo1);
        loginPage.showErrorLoginMessage();
        loginPage.login(invalidUserInfo2);
        loginPage.showErrorLoginMessage();
        loginPage.login(invalidUserInfo3);
        loginPage.showErrorLoginMessage();
        loginPage.showBlockMessage();
    }
}
