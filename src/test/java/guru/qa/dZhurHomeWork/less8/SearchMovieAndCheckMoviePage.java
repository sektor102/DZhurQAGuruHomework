package guru.qa.dZhurHomeWork.less8;

import guru.qa.dZhurHomeWork.less8.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SearchMovieAndCheckMoviePage {


    @BeforeEach
    void preCondition() {
        open("https://myshows.me/");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeCookiesIfPresent();
    }

    void closeCookiesIfPresent() {
        try {
            $(".CookiesPopup__button")
                    .should(appear, Duration.ofSeconds(2))
                    .click();
            $(".CookiesPopup").should(disappear);
        } catch (AssertionError ignored) {
        }
    }

    @ValueSource(strings = {
            "Веном", "Мстители", "Живая сталь"
    })
    @ParameterizedTest(name = "Для фильма {0} есть своя страница")
    @Tag("Smoke")
    @DisplayName("Проверяем что у фильма есть своя страница и название фильма корректно")
    void searchMovieAndCheckResultCheckNameFilmInFilmPageTest(String movie) {
        $(".Search-input").setValue(movie);
        $(".custom-scrollbar").shouldBe(visible, Duration.ofSeconds(30));
        $(".search-results__movies").$(byText(movie)).click();
        $(".MovieDetails").shouldHave(text(movie));

    }

    @EnumSource(Language.class)
    @ParameterizedTest
    @DisplayName("Проверяем переключение языка и перевод 1-й карусели")
    @Tag("Smoke")
    void checkLanguageOnSiteAndCheckNameBestSectorTest(Language language){
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
    @DisplayName("Проверяем что ссылка у сериала корректная")
    void searchSerialsAndCheckUrlSerialsTest(String searchQuery, String expectedLink){
        $(".Search-input").setValue(searchQuery);
        $(".custom-scrollbar").shouldBe(visible, Duration.ofSeconds(30));
        $(".ShowCol-title").$(byText(expectedLink));
    }


}