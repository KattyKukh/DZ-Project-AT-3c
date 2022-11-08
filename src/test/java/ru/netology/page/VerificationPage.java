package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorVerifyMsg = $("[data-test-id=error-notification]");

    public DashboardPage verify(DataHelper.VerificationCode verificationCode) {
        codeField.shouldBe(visible);
        enterCodeAndClick(verificationCode);
        return new DashboardPage();
    }

    public void enterCodeAndClick(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
    }

    public void showErrorVerifyMessage() {
        errorVerifyMsg.shouldBe(visible);
    }
}
