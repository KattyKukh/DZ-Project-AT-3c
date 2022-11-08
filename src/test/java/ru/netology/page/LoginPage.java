package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorLoginMsg = $("[data-test-id=error-notification]");

    public VerificationPage login(DataHelper.AuthInfo authInfo) {
        login.sendKeys(Keys.chord(Keys.CONTROL + "A"), Keys.DELETE);
        login.setValue(authInfo.getLogin());
        password.sendKeys(Keys.chord(Keys.CONTROL + "A"), Keys.DELETE);
        password.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void showErrorLoginMessage() {
        errorLoginMsg.shouldBe(visible)
                .should(text("Ошибка! Неверно указан логин или пароль"));
    }

    public void showBlockMessage() {
        errorLoginMsg.shouldBe(visible)
                .should(text("Ошибка! Вы ввели пароль неверно 3 раза. Учетная запись заблокирована."), Duration.ofSeconds(5));
    }


}
