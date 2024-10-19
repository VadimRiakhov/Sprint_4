package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//класс страницы "Статус заказа"
public class TrackPage {
    WebDriver driver;

    // локатор для изображения "Такого заказа нет"
    private By noSuchOrderImage = By.xpath(".//div[contains(@class,'Track_NotFound')]/img");

    //локатор для кнопки "Посмотреть"
    private By statusOrderMainButton = By.xpath(".//button[text()='Посмотреть']");

    public TrackPage(WebDriver driver) {
        this.driver = driver;
    }

    // ожидание загрузки страницы "Статус заказа"
    public void waitForLoadTrackPage(){
        new WebDriverWait(driver, Duration.ofSeconds(6)).
                until(ExpectedConditions.visibilityOfElementLocated(statusOrderMainButton));
    }
    // ожидание загрузки изображения "Такого заказа нет"
    public void waitForLoadNoSuchOrderImage(){
        new WebDriverWait(driver, Duration.ofSeconds(6)).
                until(ExpectedConditions.visibilityOfElementLocated(noSuchOrderImage));
    }
    // проверка появления изображения "Такого заказа нет"
    public boolean isNoSuchOrderImageDisplayed() {
        return driver.findElement(noSuchOrderImage).isDisplayed();
    }
}
