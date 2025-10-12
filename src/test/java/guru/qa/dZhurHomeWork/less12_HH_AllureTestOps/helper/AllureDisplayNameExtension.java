package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.helper;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureDisplayNameExtension implements BeforeTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext ctx) {
        String display = ctx.getDisplayName();

        String browserInfo = String.format("[%s_%s_%s]",
                Configuration.browser, Configuration.browserVersion, Configuration.browserSize);

        String name = display + " " + browserInfo;

        String historyId = ctx.getRequiredTestClass().getName()
                + "." + ctx.getRequiredTestMethod().getName()
                + browserInfo;

        Allure.getLifecycle().updateTestCase(tr -> {
            tr.setName(name);
            tr.setFullName(name);
            tr.setHistoryId(historyId);
        });

        Allure.parameter("Browser", Configuration.browser + " " + Configuration.browserVersion);
        Allure.parameter("Resolution", Configuration.browserSize);
    }
}
