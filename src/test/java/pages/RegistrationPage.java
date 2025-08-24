package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.CheckFillFormComponent;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    CheckFillFormComponent checkFillFormComponent = new CheckFillFormComponent();
    private String firstName, lastName, email, gender, phone, dayB, monthB, yearB, subject, hobby, picture, address, state, city;

    private String getDobUi() {
        return dayB + " " + monthB + "," + yearB;
    }

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            numberPhoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsContainer input"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateContainer = $("#state"),
            stateInput = $("#state input"),
            cityContainer = $("#city"),
            cityInput = $("#city input"),
            submitClick = $("#submit");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;

    }

    public RegistrationPage firstName(String value){
        firstNameInput.setValue(value);
        this.firstName = value;
        return this;
    }

    public RegistrationPage lastName(String value){
        lastNameInput.setValue(value);
        this.lastName = value;
        return this;
    }

    public RegistrationPage email(String value){
        userEmailInput.setValue(value);
        this.email = value;
        return this;
    }

    public RegistrationPage gender(String value){
        genderInput.$(byText(value)).click();
        this.gender = value;
        return this;
    }

    public RegistrationPage numberPhone(String value){
        numberPhoneInput.setValue(value);
        this.phone = value;
        return this;
    }

    public RegistrationPage dateOfBirth(String day, String month, String year){
        calendarComponent.setDate(day, month, year);
        this.dayB = day;
        this.monthB = month;
        this.yearB = year;
        return this;
    }

    public RegistrationPage subjects(String value){
        subjectsInput.setValue(value).pressEnter();
        this.subject  = value;
        return this;
    }

    public RegistrationPage hobbies(String value){
        hobbiesInput.$(byText(value)).click();
        this.hobby = value;
        return this;
    }

    public RegistrationPage picture(String value){
        pictureInput.uploadFromClasspath(value);
        this.picture = value;
        return this;
    }

    public RegistrationPage address(String value){
        addressInput.setValue(value);
        this.address = value;
        return this;
    }

    public RegistrationPage stateAndCity(String state, String city){
        stateContainer.click();
        stateInput.setValue(state).pressEnter();
        cityContainer.click();
        cityInput.setValue(city).pressEnter();
        this.state = state;
        this.city  = city;
        return this;
    }

    public RegistrationPage submit(){
        submitClick.click();

        return this;
    }

    public RegistrationPage notFillForm() {
        $("#userForm").shouldHave(cssClass("was-validated"));
        $("#firstName:invalid").should(exist);
        $("#lastName:invalid").should(exist);
        $("#userNumber:invalid").should(exist);
        $$("#userForm input[name='gender']:invalid").shouldHave(size(3));

        return this;
    }

    public void checkResultFull(){
        checkFillFormComponent.checkTable("Student Name", firstName + " " + lastName);
        checkFillFormComponent.checkTable("Student Email", email);
        checkFillFormComponent.checkTable("Gender", gender);
        checkFillFormComponent.checkTable("Mobile", phone);
        checkFillFormComponent.checkTable("Date of Birth", getDobUi());
        checkFillFormComponent.checkTable("Subjects", subject);
        checkFillFormComponent.checkTable("Hobbies", hobby);
        checkFillFormComponent.checkTable("Picture", picture);
        checkFillFormComponent.checkTable("Address", address);
        checkFillFormComponent.checkTable("State and City", state + " " + city);
    }

    public void checkResultPartial(){
        checkFillFormComponent.checkTable("Student Name", firstName + " " + lastName);
        checkFillFormComponent.checkTable("Gender", gender);
        checkFillFormComponent.checkTable("Mobile", phone);
        checkFillFormComponent.checkTable("Date of Birth", getDobUi());
    }

    public void checkNegative(){
        checkFillFormComponent.modalDialogNotAppear();
    }




}




