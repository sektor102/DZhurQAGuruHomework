package testSelenideHomeWork;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
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
        $("#column-a").dragAndDrop((DragAndDropOptions) DragAndDropOptions.to(Selenide.$("#column-b")));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

}
