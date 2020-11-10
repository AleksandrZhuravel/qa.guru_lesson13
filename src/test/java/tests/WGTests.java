package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("zhuravel")
@Tag("web")
public class WGTests extends TestBase {

    final String mainPage = "https://wargaming.com/ru/",
            bannerPage = "https://wargaming.com/ru/banner/",
            privacyPage = "https://legal.ru.wargaming.net/ru/privacy-policy/",
            faqPage = "https://wargaming.com/ru/faq/",
            godsGloryPage = "https://godsandglory.com/1957_ru1";

    @Test
    @Story("")
    @DisplayName("1) Проверка работы открывающегося баннера 'Подробнее' на главной странице")
    @Link(value = "Wargaming", url = mainPage)
    public void bannerDetailedShouldOpen() {

        step("a) Открыть стартовую страницу сайта" + mainPage + ";", () -> {
            open(mainPage);
        });

        step("b) Нажать на заголовок баннера 'Подробнее' с правого края страницы;", () -> {
            $(byText("Подробнее")).click();
        });

        step("c) Проверить переход на страницу" + bannerPage + ";", () -> {
            assertEquals(bannerPage, WebDriverRunner.url());
        });
        step("d) Проверить, что заголовок страницы содержит текст 'Подчини себе Вселенную!'.", () -> {
            $(".master-of-orion-banner-intro").shouldHave(Condition.text("Подчини себе Вселенную!"));
        });
    }

    @Test
    @Story("")
    @DisplayName("2) Проверка работы слайдера на главной странице")
    @Link(value = "Wargaming", url = mainPage)
    public void sliderShouldSwitchPages() {

        step("a) Открыть стартовую страницу сайта" + mainPage + ";", () -> {
            open(mainPage);
        });

        step("b) Нажать на кнопку перелистывания слайдера вправо 3 раза;", () -> {
            $(".next", 0).click();
            $(".next", 0).click();
            $(".next", 0).click();
        });

        step("c) Проверить, что заголовок слайдера содержит текст 'Начни свою историю с нами';", () -> {
            $(".s4").$(byTagName("h1")).shouldHave(Condition.text("Начни свою историю с нами"));
        });

        step("d) Проверить, что счетчик страниц слайдера показывает значение '4/4'.", () -> {
            $(".pages").shouldHave(Condition.text("4"));
        });
    }

    @Test
    @Story("")
    @DisplayName("3) Проверка открытия документа 'ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ WARGAMING'")
    @Link(value = "Wargaming", url = mainPage)
    public void confidentialityDocumentShouldOpen() {

        step("a) Открыть стартовую страницу сайта" + mainPage + ";", () -> {
            open(mainPage);
        });

        step("b) Нажать на ссылку 'Защита данных' с левого края страницы;", () -> {
            $(".group", 5).$(byText("Защита данных")).click();
        });

        step("c) Нажать на ссылку 'Политикой конфиденциальности' в верхнем обзаце параграфа \n" +
                "       'Защита данных';", () -> {
            $x("//a[contains(text(),'Политикой конфиденциальности')]").click();
        });

        step("d) Проверить переход на страницу 'https://legal.ru.wargaming.net/ru/privacy-policy/';", () -> {
            assertEquals(privacyPage, WebDriverRunner.url());
        });

        step("e) Проверить, что заголовок страницы содержит текст 'ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ WARGAMING'.", () -> {
            $(".docs-popup-wrapper-title").shouldHave(Condition.text("Политика конфиденциальности Wargaming"));
        });
    }

    @Test
    @Story("")
    @DisplayName("4) Проверка открытия страницы 'FAQ'")
    @Link(value = "Wargaming", url = mainPage)
    public void faqPageShouldOpen() {

        step("a) Открыть стартовую страницу сайта" + mainPage + ";", () -> {
            open(mainPage);
        });

        step("b) Нажать на ссылку 'Карьера' с левого края страницы;", () -> {
            $(".group", 4).$(byText("Карьера")).click();
        });

        step("c) Нажать на ссылку 'FAQ' с левого края страницы;", () -> {
            $x("//span[contains(text(),'FAQ')]").click();
        });

        step("d) Проверить переход на страницу 'https://wargaming.com/ru/faq/';", () -> {
            assertEquals(faqPage, WebDriverRunner.url());
        });

        step(" e) Проверить, что заголовок страницы содержит текст 'FAQ'.", () -> {
            $x("//div[@class='section-content-wrapper']/h1").shouldHave(Condition.text("FAQ"));
        });
    }

    @Test
    @Story("")
    @DisplayName("5) Проверка открытия страницы 'Gods & Glory'")
    @Link(value = "Wargaming", url = mainPage)
    public void godsGloryPageShouldOpen() {

        step("a) Открыть стартовую страницу сайта" + mainPage + ";", () -> {
            open(mainPage);
        });

        step("b) Нажать на ссылку 'Игры' с левого края страницы;", () -> {
            $(".group", 4).$(byText("Игры")).click();
        });

        step("c) Нажать на ссылку 'Gods & Glory' с левого края страницы;", () -> {
            $x("//ul[@class='submenu']/li//span[contains(text(),'Gods & Glory')]").click();
        });

        step("d) Нажать на кнопку 'Вступить в Бой';", () -> {
            $(".button", 0).click();
            switchTo().window(1);
        });

        step("e) Проверить переход на страницу 'https://godsandglory.com/1957_ru1';", () -> {
            assertEquals(godsGloryPage, WebDriverRunner.url());
        });

        step("f) Проверить, что заголовок страницы содержит текст 'СТАНЬ ПРАВИТЕЛЕМ ВЕЛИКОЙ ИМПЕРИИ'.", () -> {
            $(".js-screen-1-title").shouldHave(Condition.text("СТАНЬ ПРАВИТЕЛЕМ ВЕЛИКОЙ ИМПЕРИИ"));
        });
    }
}
