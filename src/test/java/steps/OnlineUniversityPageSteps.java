package steps;

import io.qameta.allure.Step;
import pages.OnlineUniversityPage;

public class OnlineUniversityPageSteps {
    OnlineUniversityPage onlineUniversityPage = new OnlineUniversityPage();

    @Step("Click ellipsis  button")
    public OnlineUniversityPageSteps clickOnEllipsisButtonStep(){
        onlineUniversityPage.clickOnEllipsisButton();
        return this;
    }
    @Step("Click on online university link button")
    public OnlineUniversityPageSteps clickOnUniversityLinkStep(){
        onlineUniversityPage.clickOnUniversityLink();
        return this;
    }
    @Step("Verify university title")
    public OnlineUniversityPageSteps verifyPageTitleStep(){
        onlineUniversityPage.verifyPageTitle();
        return this;
    }
    @Step("Find module '{moduleName}' and click on it")
    public OnlineUniversityPageSteps findModuleByNameStep(String moduleName){
        onlineUniversityPage.findModuleByName(moduleName);
        return this;
    }
    @Step("Verify '{moduleName}' module page has title '{moduleName}'")
    public OnlineUniversityPageSteps verifyModuleTitleStep(String moduleName){
        onlineUniversityPage.verifyModuleTitle(moduleName);
        return this;
    }
}
