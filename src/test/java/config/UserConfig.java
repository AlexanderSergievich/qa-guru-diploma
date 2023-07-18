package config;

import org.aeonbits.owner.Config;
@Config.Sources({
        "classpath:properties/user.properties"
})
public interface UserConfig extends Config {
    @Key("email")
    String getEmail();
    @Key("password")
    String getPassword();
    @Key("newPassword")
    String getNewPassword();
}
