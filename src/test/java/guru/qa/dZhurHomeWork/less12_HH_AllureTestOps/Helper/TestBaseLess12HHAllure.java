package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.*;

public class TestBaseLess12HHAllure {

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


        Allure.parameter("Browser", Configuration.browser + " " + Configuration.browserVersion);
        Allure.parameter("Resolution", Configuration.browserSize);
    }

    @AfterEach
    void addAttachments() {
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.screenshotAs("Last screenshot");
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.pageSource();
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.browserConsoleLogs();
        guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Attach.AttachHH12.addVideo();
    }
    public static final String urlWorkHH = "/vacancy/123603592";


}
