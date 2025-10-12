package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.helper;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.TestInfo;

public class AllureHelperLess12 {

    public static void setDisplayName(TestInfo testInfo) {
        String browserInfo = String.format("[%s_%s_%s]",
                Configuration.browser, Configuration.browserVersion, Configuration.browserSize);

        String name = testInfo.getDisplayName() + " " + browserInfo;

        String historyId = testInfo.getTestMethod()
                .map(m -> m.getDeclaringClass().getName() + "." + m.getName() + browserInfo)
                .orElse(name);

        Allure.getLifecycle().updateTestCase(tc -> {
            tc.setName(name);
            tc.setFullName(name);
            tc.setHistoryId(historyId);
        });
    }

}
