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

//    @AfterEach
//    void cleanUp() {
//        closeWindow();
//    }

    @AfterAll
    void cleanUpBD() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should login if valid user info")
    void successLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var validUserInfo = DataHelper.validInfo();
        var verificationPage = loginPage.login(validUserInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        var DashboardPage = verificationPage.validVerify(verificationCode);
    }
}
