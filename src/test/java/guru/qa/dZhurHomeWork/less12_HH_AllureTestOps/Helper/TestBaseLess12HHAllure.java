package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.*;

public class TestBaseLess12HHAllure {
    public static final String urlWorkHH = "/vacancy/123603592";

    private static void writeEnv() {
        try {
            java.io.File dir = new java.io.File("build/allure-results");
            if (!dir.exists()) dir.mkdirs();

            var p = new java.util.Properties();
            p.setProperty("browser", Configuration.browser + "_" + Configuration.browserVersion);
            p.setProperty("size", Configuration.browserSize);
            p.setProperty("baseUrl", Configuration.baseUrl); // опционально

            try (var fos = new java.io.FileOutputStream(new java.io.File(dir, "environment.properties"))) {
                p.store(fos, "env");
            }
        } catch (Exception ignored) {}
    }

    @BeforeEach
    void setTestMeta(TestInfo testInfo) {
        AllureHelperLess12.setDisplayName(testInfo);
        Allure.parameter("Browser", Configuration.browser + " " + Configuration.browserVersion);
        Allure.parameter("Resolution", Configuration.browserSize);
    }


    @BeforeAll
    static void beforeAll() {
        String login = System.getProperty("remoteLogin");
        String password = System.getProperty("remotePassword");
        String urlSelenide = System.getProperty("urlSelenide");
        String combo = System.getProperty("browserAndVersion");
        String[] parts = combo.split("_");
        browserSize = System.getProperty("browserSize");
        browser = parts[0];
        browserVersion = parts[1];
        Configuration.baseUrl = "https://hh.ru";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = String.format("https://%s:%s@%s/wd/hub", login, password, urlSelenide);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String desktopUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) "
                + "Chrome/128.0.6613.137 Safari/537.36";

        switch (Configuration.browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();


                options.addArguments("--disable-blink-features=AutomationControlled");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.setExperimentalOption("useAutomationExtension", false);


                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");


                options.addArguments("--user-agent=" + desktopUserAgent);


                if (Configuration.headless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=" + Configuration.browserSize.replace("x", ","));
                }

                MutableCapabilities caps = new MutableCapabilities();
                caps.merge(options);
                Configuration.browserCapabilities = caps;
            }

            case "firefox" -> {
                MutableCapabilities caps = getMutableCapabilities(desktopUserAgent);
                Configuration.browserCapabilities = caps;


            }
        }
        writeEnv();

    }

    private static MutableCapabilities getMutableCapabilities(String desktopUserAgent) {
        FirefoxOptions options = new FirefoxOptions();


        options.addPreference("general.useragent.override", desktopUserAgent);
        options.addPreference("dom.webdriver.enabled", false);
        options.addPreference("useAutomationExtension", false);

        if (Configuration.headless) {
            options.addArguments("-headless");
            options.addArguments("--width=" + Configuration.browserSize.split("x")[0]);
            options.addArguments("--height=" + Configuration.browserSize.split("x")[1]);
        }

        MutableCapabilities caps = new MutableCapabilities();
        caps.merge(options);
        return caps;
    }


    @AfterEach
    void addAttachments() {
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.screenshotAs("Last screenshot");
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.pageSource();
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.browserConsoleLogs();
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.addVideo();
    }
}




