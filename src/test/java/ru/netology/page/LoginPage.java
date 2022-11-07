package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input__control");
    private SelenideElement password = $("[data-test-id=password] input__control");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorLoginMsg = $("data-test-id=error-notification");

    public VerificationPage login(DataHelper.AuthInfo authInfo){
        login.setValue(authInfo.getLogin());
        password.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void showErrorLoginMessage() {
        errorLoginMsg.shouldBe(visible);
    }


}
