package homeWorkCodeAutoTest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


public class TestFindElementsLess4 {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void compareSelectors() {
        open("data:text/html," +
                "<h1 id='first'>Первый h1 без div</h1>" +
                "<div id='outside'>Снаружи</div>" +
                "<h1 id='second'><div id='in-h1'>Внутри второго h1</div></h1>");

        // Глобальный поиск: ищет div внутри любого h1 на странице
        System.out.println("Глобальный поиск (h1 div): " + $$("h1 div").size());

        // Локальный поиск: ищет div только внутри первого найденного h1
        System.out.println("Локальный поиск (h1).$(div): " + $("h1").$$("div").size());
    }

}
