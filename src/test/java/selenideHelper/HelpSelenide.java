package selenideHelper;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class HelpSelenide {

    //примеры команд браузера
    void browser_command_examples() {
        open("https://google.com"); // открыть абсолютный URL в текущем браузерном окне
        open("/customer/orders");     // открыть относительный путь (основан на -Dselenide.baseUrl)
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("", "user", "password")); // открыть с HTTP Basic Auth

        Selenide.back(); // шаг назад в истории браузера
        Selenide.refresh(); // перезагрузить текущую страницу

        Selenide.clearBrowserCookies(); // удалить все куки
        Selenide.clearBrowserLocalStorage(); // очистить localStorage
        executeJavaScript("sessionStorage.clear();"); // очистить sessionStorage (встроенной команды нет)

        Selenide.confirm(); // нажать OK в alert/confirm
        Selenide.dismiss(); // нажать Cancel в alert/confirm

        Selenide.closeWindow(); // закрыть активную вкладку
        Selenide.closeWebDriver(); // полностью закрыть браузер и WebDriver

        Selenide.switchTo().frame("new"); // переключиться во фрейм по имени/идентификатору
        Selenide.switchTo().defaultContent(); // вернуться из фрейма в основной DOM

        Selenide.switchTo().window("The Internet"); // переключиться в окно/вкладку по имени/заголовку

        var cookie = new Cookie("foo", "bar"); // создать cookie (Selenium)
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); // добавить cookie в текущую сессию
    }

    //примеры селекторов
    void selectors_examples() {
        $("div").click(); // CSS-селектор: первый div
        element("div").click(); // то же самое, синоним $

        $("div", 2).click(); // элемент с индексом 2 среди найденных (третий div)

        $x("//h1/div").click(); // XPath
        $(byXpath("//h1/div")).click(); // XPath через селектор-хелпер

        $(byText("full text")).click(); // элемент, содержащий точный текст
        $(withText("ull tex")).click(); // элемент, содержащий подстроку

        $(byTagAndText("div", "full text")); // div с точным текстом
        $(withTagAndText("div", "ull text")); // div с подстрокой в тексте

        $("").parent(); // родительский элемент
        $("").sibling(1); // соседний элемент с индексом 1
        $("").preceding(1); // предыдущий элемент с индексом 1
        $("").closest("div"); // ближайший предок по селектору
        $("").ancestor("div"); // синоним closest для Selenide
        $("div:last-child"); // CSS-псевдокласс: последний потомок

        $("div").$("h1").find(byText("abc")).click(); // вложенный поиск: div → h1 → по тексту
        // очень опционально
        $(byAttribute("abc", "x")).click(); // по атрибуту abc="x"
        $("[abc=x]").click(); // то же через CSS

        $(byId("mytext")).click(); // по id
        $("#mytext").click(); // краткая форма по id

        $(byClassName("red")).click(); // по классу
        $(".red").click(); // краткая форма по классу
    }

    //примеры действий
    void actions_examples() {
        $("").click(); // клик по элементу
        $("").doubleClick(); // двойной клик
        $("").contextClick(); // правый клик

        $("").hover(); // наведение курсора (mouse over)

        $("").setValue("text"); // очистить и ввести текст
        $("").append("text"); // дописать текст в конец (без очистки)
        $("").clear(); // очистить поле
        $("").setValue(""); // тоже очистка полем пустой строки

        $("div").sendKeys("c"); // отправить клавишу/строки в элемент (focus должен быть на нём)
        actions().sendKeys("c").perform(); // отправить клавишу на приложение/активный элемент
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // послать сочетание Ctrl+F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // аналог через конкретный элемент

        $("").pressEnter(); // нажать Enter на элементе
        $("").pressEscape(); // нажать Esc
        $("").pressTab(); // нажать Tab (часто для блюра/перевода фокуса)

        // комплексные действия: перемещение мыши, зажатие, drag по офсету, отпускание
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        // старые HTML-контролы: select/radio. На современных UI могут не работать
        $("").selectOption("dropdown_option"); // выбрать опцию по видимому тексту
        $("").selectRadio("radio_options"); // выбрать radio по значению value
    }

    //примеры утверждений
    void assertions_examples() {
        $("").shouldBe(visible); // ожидать, что элемент видим
        $("").shouldNotBe(visible); // ожидать, что элемент невидим
        $("").shouldHave(text("abc")); // ожидать, что текст содержит подстроку
        $("").shouldNotHave(text("abc")); // ожидать отсутствия подстроки в тексте
        $("").should(appear); // ожидать появления в DOM и видимости
        $("").shouldNot(appear); // ожидать отсутствия/исчезновения

        // увеличенный таймаут ожидания условия
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }

    // примеры условий
    void conditions_examples() {
        $("").shouldBe(visible); // видим
        $("").shouldBe(hidden); // скрыт (не видим)

        $("").shouldHave(text("abc")); // содержит подстроку
        $("").shouldHave(exactText("abc")); // текст совпадает ровно
        $("").shouldHave(textCaseSensitive("abc")); // подстрока с учётом регистра
        $("").shouldHave(exactTextCaseSensitive("abc")); // ровно и чувствительно к регистру
        $("").should(matchText("[0-9]abc$")); // regex-совпадение текста

        $("").shouldHave(cssClass("red")); // содержит CSS-класс
        $("").shouldHave(cssValue("font-size", "12")); // CSS-свойство имеет значение

        $("").shouldHave(value("25")); // значение input содержит подстроку 25
        $("").shouldHave(exactValue("25")); // значение input ровно 25
        $("").shouldBe(empty); // пустое значение (input/textarea)

        $("").shouldHave(attribute("disabled")); // наличие атрибута
        $("").shouldHave(attribute("name", "example")); // атрибут name со значением
        $("").shouldHave(attributeMatching("name", "[0-9]abc$")); // значение name по regex

        $("").shouldBe(checked); // чекбокс/радио отмечен

        // Важно: exist проверяет только присутствие в DOM, не видимость
        $("").should(exist);

        // Важно: disabled/enabled проверяют HTML-атрибут/состояние. В SPA могут не работать
        $("").shouldBe(disabled); // элемент отключён
        $("").shouldBe(enabled); // элемент включён
    }

    // примеры коллекций
    void collections_examples() {

        $$("div"); // получить коллекцию элементов по CSS (без действий)

        $$x("//div"); // коллекция по XPath

        // выборки/фильтры по коллекции
        $$("div").filterBy(text("123")).shouldHave(size(1)); // оставить с текстом 123
        $$("div").excludeWith(text("123")).shouldHave(size(1)); // исключить с текстом 123

        $$("div").first().click(); // первый элемент коллекции
        elements("div").first().click(); // синоним $$ → elements
        // $("div").click(); // тоже первый, но через одиночный селектор
        $$("div").last().click(); // последний элемент
        $$("div").get(1).click(); // элемент по индексу 1 (второй)
        $("div", 1).click(); // короткая форма: индекс в $
        $$("div").findBy(text("123")).click(); // первый, удовлетворяющий условию

        // проверки коллекций
        $$("").shouldHave(size(0)); // размер коллекции 0
        $$("").shouldBe(CollectionCondition.empty); // то же

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); // тексты в таком порядке (подстроки)
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); // тексты ровно такие

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa")); // порядок не важен
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa")); // ровно и регистрозависимо

        $$("").shouldHave(itemWithText("Gamma")); // в коллекции есть элемент с текстом

        $$("").shouldHave(sizeGreaterThan(0)); // размер > 0
        $$("").shouldHave(sizeGreaterThanOrEqual(1)); // размер ≥ 1
        $$("").shouldHave(sizeLessThan(3)); // размер < 3
        $$("").shouldHave(sizeLessThanOrEqual(2)); // размер ≤ 2
    }

    // примеры файловых операций
    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // скачать по href (только для <a>)
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // скачать через директорию (подходит для Selenide 6+, но может не работать в Grid/Selenoid без настроек)

        File file = new File("src/test/resources/readme.txt"); // локальный файл
        $("#file-upload").uploadFile(file); // загрузить файл с диска
        $("#file-upload").uploadFromClasspath("readme.txt"); // загрузить из classpath
        // не забыть сабмит формы после загрузки
        $("uploadButton").click(); // клик по кнопке отправки
    }

    // примеры JavaScript
    void javascript_examples() {
        executeJavaScript("alert('selenide')"); // выполнить JS без возврата
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12); // передача аргументов
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7); // выполнить JS и вернуть значение
    }
}
