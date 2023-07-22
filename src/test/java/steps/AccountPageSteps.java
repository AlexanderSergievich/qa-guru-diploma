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

    @Step("Set old password '{password}'")
    public AccountPageSteps setOldPasswordStep(String password) {
        accountPage.setOldPassword(password);
        return this;
    }
    @Step("Set new password '{newPassword}'")
    public AccountPageSteps setNewPasswordStep(String newPassword) {
        accountPage.setNewPassword(newPassword);
        return this;
    }
    @Step("Repeat new password '{newPassword}'")
    public AccountPageSteps repeatNewPasswordStep(String newPassword) {
        accountPage.repeatNewPassword(newPassword);
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
