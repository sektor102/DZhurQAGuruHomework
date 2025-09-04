package guru.qa.dZhurHomeWork.less8;

import guru.qa.dZhurHomeWork.less8.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class searchMovieAndCheckMoviePage {


    @BeforeEach
    void PreCondition() {
        open("https://myshows.me/");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeCookiesIfPresent();
    }

    void closeCookiesIfPresent() {
        try {
            // даём попапу короткое окно на появление
            $(".CookiesPopup__button")
                    .should(appear, Duration.ofSeconds(2))
                    .click();
            $(".CookiesPopup").should(disappear); // если есть контейнер
        } catch (AssertionError ignored) {
            // попап не появился — едем дальше
        }
    }

    @ValueSource(strings = {
            "Веном", "Мстители", "Живая сталь"
    })
    @ParameterizedTest(name = "Для фильма {0} есть своя страница")
    @Tag("Smoke")
    void searchMovieAndCheckResultSearch(String movie) {
        $(".Search-input").setValue(movie);
        $(".custom-scrollbar").shouldBe(visible, Duration.ofSeconds(30));
        $(".search-results__movies").$(byText(movie)).click();
        $(".MovieDetails").shouldHave(text(movie));

    }

    @EnumSource(Language.class)
    @ParameterizedTest
    void checkLanguageOnSite(Language language){
        $(".LangSwitcher-current").click();
        $$(".LangSwitcher-optionText").find(text(language.name())).click();
        $(".PopularShows__title").shouldHave(text(language.description));
    }

    @CsvSource(value = {
            "Южный парк, /view/34/",
            "Рик и Морти, /view/34737/",
    })
    @ParameterizedTest(name = "Для поиска сериала {0} есть сериал с ссылкой {1}")
    @Tag("Functional")
    void searchSerialsAndCheckUrl(String searchQuery, String expectedLink){
        $(".Search-input").setValue(searchQuery);
        $(".custom-scrollbar").shouldBe(visible, Duration.ofSeconds(30));
        $(".ShowCol-title").$(byText(expectedLink));
    }


}