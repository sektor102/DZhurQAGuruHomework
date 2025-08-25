package homeWorkCodeAutoTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class TestRectangleTransferLess5 {
    @BeforeAll
    static void setupEnvironment () {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://the-internet.herokuapp.com/drag_and_drop";
    }

    @Test
    void testRectangleTransferSelenideAction(){
        open("");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        SelenideElement elementA = $("#column-a");
        SelenideElement elementB = $("#column-b");
        actions()
                .clickAndHold(elementA)
                .moveToElement(elementB)
                .release()
                .perform();
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void testRectangleTransferDragAndDrop(){
        open("");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        $("#column-a").dragAndDrop(to("#column-b"));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

}
