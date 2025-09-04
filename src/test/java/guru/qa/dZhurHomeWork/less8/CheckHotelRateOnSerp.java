package guru.qa.dZhurHomeWork.less8;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CheckHotelRateOnSerp {


    @BeforeEach
    void PreCondition() {
        open("https://ostrovok.ru/");
        Configuration.holdBrowserOpen = true;
//        Configuration.pageLoadStrategy = "eager";
        executeJavaScript(
                "(function(){\n" +
                        "  // 1) Ставим глобальный стиль с !important\n" +
                        "  if(!document.getElementById('kill-flocktory-style')){\n" +
                        "    const css = `.flocktory-widget-overlay,\n" +
                        "                 iframe.flocktory-widget,\n" +
                        "                 [id^=\"fl-\"],\n" +
                        "                 .flocktory-iframe,\n" +
                        "                 .flocktory-popup { display: none !important; visibility: hidden !important; pointer-events: none !important; }`;\n" +
                        "    const s = document.createElement('style');\n" +
                        "    s.id = 'kill-flocktory-style'; s.innerHTML = css; document.head.appendChild(s);\n" +
                        "  }\n" +
                        "  // 2) Функция зачистки уже вставленного\n" +
                        "  const purge = () => document.querySelectorAll(\n" +
                        "    '.flocktory-widget-overlay, iframe.flocktory-widget, [id^=\"fl-\"], .flocktory-iframe, .flocktory-popup'\n" +
                        "  ).forEach(n => { try { n.remove(); } catch(e) { n.style.display='none'; } });\n" +
                        "  purge();\n" +
                        "  // 3) Наблюдатель — если баннер вставят снова\n" +
                        "  if(!window.__flocktoryObserver){\n" +
                        "    window.__flocktoryObserver = new MutationObserver(() => purge());\n" +
                        "    window.__flocktoryObserver.observe(document.documentElement, {childList:true, subtree:true});\n" +
                        "  }\n" +
                        "})();"
        );
    }

    @ValueSource(strings = {
            "Ташкент", "Санкт-Петербург", "Пятигорск"
    })
    @ParameterizedTest(name = "Для города {0} есть отели на серпе")
    @Tag("Smoke")
    void searchHotelRateOnSerp(String cityDestination) {
        $("[data-testid=destination-input]")
                .shouldBe(visible, enabled)
                .setValue(cityDestination);
        $$("div[class*='Popup-module__popup']")
                .filter(visible)
                .first()
                .should(appear)
                .unfocus();
//        $$("div[class*='Suggest-module__destination'][title]")
//                .filter(visible)
//                .findBy(attribute("title", cityDestination))
//                .shouldBe(visible, enabled)
//                .click();
        $("[data-testid=search-button]").click();
        $$("[class*='Distances_distance_address'][role='button']").shouldBe(sizeGreaterThanOrEqual(20));
    }
}