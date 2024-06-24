import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/mbpro/Desktop/JavAppiumAutomation/JavaAppiumAuto/JavaAppiumAuto/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub/"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFindTextInFielsSearch() {
        //Ищем кнопку "Skip" и тапаем по ней
        waitForElementForClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Not find button Skip",
                15
        );
        //Ищем поле ввода и тапаем по нему
        waitForElementForClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                15
        );

        //Ищем нашу статью и тапаем по ней
        waitForElementForClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Not find статья",
                15
        );

        //Ждем и проверяем что статья появилась
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Not find Title in page",
                15
        );
        // теперь получил текст из заголовка присвоив его новой переменной
        String text_search = title_element.getAttribute("text" );
        Assert.assertEquals(
                "Не нашли текст ",
                "Search Wikipedia",
                text_search
        );

    }

    //Пишем метод ожидающий элемент
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        return waitForElementPresent(by, error_message, 15);
    }

    //Пишем метод на ожидание элемента и клика по нему
    private WebElement waitForElementForClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 15);
        element.click();
        return element;
    }

//Пишем метод который проверяет наличие ожидаемого текста у элемента
    private WebElement assertElementHasText(By by, String value, String error_message) {
        WebElement element = assertElementHasText(by, value, error_message);
        element.sendKeys(value);
        return element;
    }
}











