package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/remote.properties"
})
public interface WebDriverConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("browserVersion")
    @DefaultValue("100")
    String browserVersion();

    @Key("baseUrl")
    @DefaultValue("https://arzamas.academy")
    String baseUrl();

    @Key("isRemoteWebDriver")
    @DefaultValue("false")
    Boolean isRemoteWebDriver();

    @Key("remoteURL")
    @DefaultValue("selenoid.autotests.cloud")
    String remoteURL();

    @Key("login")
    @DefaultValue("user1")
    String login();

    @Key("password")
    @DefaultValue("1234")
    String password();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browserBinary")
    String browserBinary();
}