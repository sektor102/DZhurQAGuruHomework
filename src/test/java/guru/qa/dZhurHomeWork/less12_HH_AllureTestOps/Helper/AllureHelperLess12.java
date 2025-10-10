package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.TestInfo;



import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserVersion;
import static com.codeborne.selenide.Configuration.browserSize;

public class AllureHelperLess12 {
    public static void setDisplayName(TestInfo testInfo) {
        String browserInfo = String.format("[%s_%s_%s]", browser, browserVersion, browserSize);
        String name = testInfo.getDisplayName() + " " + browserInfo;

        Allure.getLifecycle().updateTestCase(tc -> tc.setName(name));
    }

}
