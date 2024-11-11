package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static constants.URL.*;
import java.time.Duration;

// класс формы "Для кого самокат"
public class OrderPageWhomScooterFor {

    private WebDriver driver;

    // локатор для поля "Имя"
    private By firstNameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    // локатор для поля "Фамилия"
    private By lastNameInput = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    // локатор для поля "Адрес"
    private By addressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    // локатор для поля ввода "Станция метро"
    private By stationInput = By.xpath(".//div[@class='select-search']");
    // шаблон для поля "Станция метро"
    private String STATION_PATTERN = ".//div[contains(@class, 'Order_Text') and text()='%s']";
    // локатор для поля "Телефон"
    private By phoneInput = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    // локатор для кнопки "Далее"
    private By furtherButton = By.xpath(".//div[contains(@class, 'Order_NextButton')]/button");
    // локатор для логотипа Самокат
    private By scooterLogo = By.xpath(".//a[contains(@class, 'Header_LogoScooter')]/img");
    // локатор для текста ошибки поля "Имя"
    private By firstNameError = By.xpath(".//input[contains(@placeholder, 'Имя')]/following-sibling::div[contains(@class, 'Input_Visible')]");
    // локатор для текста ошибки поля "Фамилия"
    private By lastNameError = By.xpath(".//input[contains(@placeholder, 'Фамилия')]/following-sibling::div[contains(@class, 'Input_Visible')]");
    // локатор для текста ошибки поля "Адрес"
    private By addressError = By.xpath(".//input[contains(@placeholder, 'Адрес')]/following-sibling::div[contains(@class, 'Input_Visible')]");
    // локатор для текста ошибки поля "Станция метро"
    private By stationError = By.xpath(".//div[contains(@class, 'Order_MetroError')]");
    // локатор для текста ошибки поля "Телефон"
    private By phoneError = By.xpath(".//input[contains(@placeholder, 'Телефон')]/following-sibling::div[contains(@class, 'Input_Visible')]");

    public OrderPageWhomScooterFor(WebDriver driver) {
        this.driver = driver;
    }

    // открытие формы "Для кого самокат" страницы заказа
    public void openOrderPage(){
        driver.get(ORDER_PAGE_URL);
        waitForLoadOrderPageWhomScooterFor();
    }

    // ожидание загрузки формы "Для кого самокат"
    public void waitForLoadOrderPageWhomScooterFor(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(furtherButton));
    }

    // получение URL страницы
    public String getOrderPageUrl(){
        return driver.getCurrentUrl();
    }

    // заполнение поля "Имя"
    public void setFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    // заполнение поля "Фамилия"
    public void setLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    // заполнение поля "Адрес"
    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    // заполнение поля "Станция метро"
    public void setStation(String station) {
        driver.findElement(stationInput).click();
        WebElement stationElement = driver.findElement(By.xpath(String.format(STATION_PATTERN, station)));
        scrollToElement(stationElement);
        stationElement.click();
    }

    // заполнение поля "Телефон"
    public void setPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    // нажатие кнопки "Далее"
    public void furtherButtonClick(){
        driver.findElement((furtherButton)).click();
    }

    // заполнение всех полей формы "Для кого самокат" и нажатие кнопки "Далее"
    public void setAllFields(String firstName, String lastName, String address, String station, String phone){
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setStation(station);
        setPhone(phone);
        furtherButtonClick();
    }

    // клик по логотипу Самокат
    public void scooterLogoClick(){
        driver.findElement(scooterLogo).click();
    }

    // скролл до элемента
    private void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // получение текста ошибки для поля "Имя"
    public String getFirstNameErrorMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(firstNameError));
        return driver.findElement(firstNameError).getText();
    }
    // получение текста ошибки для поля "Фамилия"
    public String getLastNameErrorMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(lastNameError));
        return driver.findElement(lastNameError).getText();
    }
    // получение текста ошибки для поля "Адрес"
    public String getAddressErrorMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(addressError));
        return driver.findElement(addressError).getText();
    }
    // получение текста ошибки для поля "Станция метро"
    public String getStationErrorMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(stationError));
        return driver.findElement(stationError).getText();
    }
    // получение текста ошибки для поля "Телефон"
    public String getPhoneErrorMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(phoneError));
        return driver.findElement(phoneError).getText();
    }
}
