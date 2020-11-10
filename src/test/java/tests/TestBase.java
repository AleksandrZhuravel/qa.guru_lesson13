package tests;

import config.ConfigHelper;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;
import static helpers.BrowserstackHelper.getBSPublicLink;
import static helpers.DriverHelper.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestBase {

    @BeforeAll
    @Step("Tests setup")
    public static void beforeAll() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        configureSelenide();
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach(){
        String sessionId = getSessionId();

        attachScreenshot("Last screenshot");
        attachPageSource();
//        attachNetwork(); // todo
        if (ConfigHelper.isWeb())
            attachAsText("Browser console logs", getConsoleLogs());
        if (ConfigHelper.isAndroid() || ConfigHelper.isIos())
            attachAsText("Browserstack build link", getBSPublicLink(sessionId));

        closeWebDriver();

        if (!ConfigHelper.getWebVideoStorage().equals(""))
            attachVideo(sessionId); // in browserstack video url generates after driver close
    }
}
