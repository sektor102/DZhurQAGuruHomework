package pages.components;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.*;

public class CalendarComponent {
    public void setDate(String day, String month, String year){

        $("#dateOfBirthInput").sendKeys(COMMAND, "a");
        $("#dateOfBirthInput").sendKeys(CONTROL, "a");
        $("#dateOfBirthInput").sendKeys(day, month, year, ENTER);
        $("#subjectsContainer").click();

    }
}
