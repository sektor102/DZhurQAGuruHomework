package guru.qa.dZhurHomeWork.Less17_Owner_WebTest.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {

    private static final WebConfig webConfig;

    static {
        String env = System.getProperty("env", "local");
        ConfigFactory.setProperty("env", env);
        webConfig = ConfigFactory.create(WebConfig.class);
    }

    public static WebConfig read() {
        return webConfig;
    }
}
