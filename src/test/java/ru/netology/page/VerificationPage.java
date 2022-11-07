package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input__control");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorVerifyMsg = $("data-test-id=error-notification");

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
//        codeField.shouldBe(visible);
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public void showErrorVerifyMessage() {
        errorVerifyMsg.shouldBe(visible);
    }
}
