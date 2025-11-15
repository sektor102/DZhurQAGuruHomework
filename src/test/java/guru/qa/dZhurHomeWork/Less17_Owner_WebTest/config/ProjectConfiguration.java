package guru.qa.dZhurHomeWork.Less17_Owner_WebTest.config;

import com.codeborne.selenide.Configuration;

public class ProjectConfiguration {

    private final WebConfig cfg;

    public ProjectConfiguration(WebConfig cfg) {
        this.cfg = cfg;
    }

    public void webConfig() {
        Configuration.baseUrl = cfg.baseUrl();
        Configuration.browser = cfg.browser();
        Configuration.browserVersion = cfg.version();
        Configuration.browserSize = cfg.browserSize();
        Configuration.pageLoadStrategy = cfg.pageLoadStrategy();

        if (cfg.remote()) {
            Configuration.remote = cfg.remoteUrl();
        }
    }
}
