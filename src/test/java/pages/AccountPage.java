package pages;

import com.codeborne.selenide.Condition;
import tests.TestBase;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AccountPage extends TestBase {
    String oldPasswordField = "Текущий пароль";
    String newPasswordField = "Новый пароль";
    String repeatNewPasswordField = "Подтвердите новый пароль";
    String changePasswordButton = "Изменить пароль";

    public AccountPage openAccountPage(){
        open("/account/edit");
        return this;
    }
    public AccountPage setOldPassword(String password){
        $(byText(oldPasswordField)).sibling(0).find(byTagName("input")).setValue(password);
        return this;
    }
    public AccountPage setNewPassword(String newPassword){
        $(byText(newPasswordField)).sibling(0).find(byTagName("input")).setValue(newPassword);
        return this;
    }
    public AccountPage repeatNewPassword(String newPassword){
        $(byText(repeatNewPasswordField)).sibling(0).find(byTagName("input")).setValue(newPassword);
        return this;
    }
    public AccountPage clickChangePassword(){
        $(byText(changePasswordButton)).click();
        return this;
    }
    public AccountPage checkInputsAreEmpty() {
        $(byText(oldPasswordField)).sibling(0).find(byTagName("input")).shouldBe(Condition.empty, Condition.exist);
        $(byText(newPasswordField)).sibling(0).find(byTagName("input")).shouldBe(Condition.empty, Condition.exist);
        $(byText(repeatNewPasswordField)).sibling(0).find(byTagName("input")).shouldBe(Condition.empty, Condition.exist);
        return this;
    }
}
