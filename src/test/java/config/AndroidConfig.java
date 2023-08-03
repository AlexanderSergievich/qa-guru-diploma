package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${deviceHost}.properties"
})
public interface AndroidConfig extends Config {
    @Key("mobile.version")
    String mobileVersion();
    @Key("bs")
    String bs();
    @Key("project")
    String project();
    @Key("build")
    String build();
    @Key("name")
    String name();
    @Key("mobile.deviceName")
    String mobileDevice();

    @Key("mobile.app.path")
    @DefaultValue("src/test/resources/apps/Radio_Arzamas_2.19.0_apkcombo.com.apk")
    String mobileAppPath();

    @Key("mobile.apppackage")
    String mobileAppPackage();

    @Key("mobile.appactivity")
    String mobileAppActivity();

    @Key("mobile.url")
    String mobileUrl();
}
