package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper;
import io.qameta.allure.Allure;
import java.util.UUID;


import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserVersion;
import static com.codeborne.selenide.Configuration.browserSize;

public class AllureHelperLess12 {

    public static void updateTestMeta(String testDisplayName) {
        try {
            String uniqueId = UUID.randomUUID().toString();
            String testName = String.format("%s [%s_%s %s]",
                    testDisplayName, browser, browserVersion, browserSize);

            Allure.getLifecycle().updateTestCase(tc -> {
                tc.setUuid(uniqueId);
                tc.setName(testName);
                tc.setFullName(testName);
                tc.setHistoryId(browser + "_" + browserVersion + "_" + browserSize);
            });
        } catch (Exception e) {
            System.out.println("Не удалось обновить метаданные теста: " + e.getMessage());
        }
    }
}
