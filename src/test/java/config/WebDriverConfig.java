package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/remote.properties"
})
public interface WebDriverConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("baseUrl")
    @DefaultValue("https://arzamas.academy")
    String getBaseUrl();

    @Key("isRemoteWebDriver")
    @DefaultValue("false")
    Boolean isRemoteWebDriver();

    @Key("remoteURL")
    String getRemoteURL();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

}