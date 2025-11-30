package guru.qa.dZhurHomeWork.less21_Diplom;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseLess21 {
    @BeforeAll
    static void browserConfiguration() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "http://kakadu39.ru";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }
}
