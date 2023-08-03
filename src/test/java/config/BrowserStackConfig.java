package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:browserstackauth.properties",
})
public interface BrowserStackConfig extends Config {
    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("baseUrl")
    String baseUrl();
}
