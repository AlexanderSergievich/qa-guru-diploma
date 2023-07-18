package steps;

import io.qameta.allure.Step;
import pages.AccountPage;

public class AccountPageSteps {
    AccountPage accountPage = new AccountPage();
    @Step("Open account page")
    public AccountPageSteps openAccountPageStep(){
        accountPage.openAccountPage();
        return this;
    }

    @Step("Set old password")
    public AccountPageSteps setOldPasswordStep() {
        accountPage.setOldPassword();
        return this;
    }
    @Step("Set new password")
    public AccountPageSteps setNewPasswordStep() {
        accountPage.setNewPassword();
        return this;
    }
    @Step("Repeat new password")
    public AccountPageSteps repeatNewPasswordStep() {
        accountPage.repeatNewPassword();
        return this;
    }
    @Step("Click change password")
    public AccountPageSteps ClickChangePasswordStep() {
        accountPage.clickChangePassword();
        return this;
    }
    @Step("Repeat new password")
    public AccountPageSteps checkInputsAreEmptyStep() {
        accountPage.checkInputsAreEmpty();
        return this;
    }
}
