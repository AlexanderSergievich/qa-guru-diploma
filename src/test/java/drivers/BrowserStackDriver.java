package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AndroidConfig;
import config.BrowserStackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {
    static BrowserStackConfig bsConfig = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());
    static AndroidConfig androidConfig = ConfigFactory.create(AndroidConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.user", bsConfig.username());
        mutableCapabilities.setCapability("browserstack.key", bsConfig.password());
        mutableCapabilities.setCapability("app", androidConfig.bs());
        mutableCapabilities.setCapability("device", androidConfig.mobileDevice());
        mutableCapabilities.setCapability("os_version", androidConfig.mobileVersion());
        mutableCapabilities.setCapability("project", androidConfig.project());
        mutableCapabilities.setCapability("build", androidConfig.build());
        mutableCapabilities.setCapability("name", androidConfig.name());
        try {
            return new RemoteWebDriver(
                    new URL(bsConfig.baseUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
