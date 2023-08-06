package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/user.properties"
})
public interface UserConfig extends Config {
    @Key("email")
    String email();

    @Key("password")
    String password();

    @Key("newPassword")
    String newPassword();
}
