package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static constants.URL.*;
import java.time.Duration;

//класс главной страницы "Яндекс Самокат"
public class MainPage {
    private WebDriver driver;
    // шаблон для отдельного вопроса в FAQ
    private static final String FAQ_QUESTION_PATTERN = ".//div[contains(@id,'accordion__heading') and contains(text(), '%s')]";
    // шаблон для ответа (обращаемся к родителю FAQ_QUESTION_PATTERN и получаем элемент-сосед, у которого нет атрибута hidden)
    private static final String FAQ_ANSWER_PATTERN = ".//div[contains(@id,'accordion__heading') and contains(text(), '%s')]/../following-sibling::div[not(@hidden)]/p";
    // локатор для кнопки "да все привыкли" в футере
    private By cookieButton = By.xpath(".//button[contains(@class,'App_CookieButton')]");
    // локатор для кнопки "Заказать" в хедере
    private By headerOrderButton = By.xpath(".//div[contains(@class,'Header_Nav')]/button[contains(@class,'Button_Button')]");
    // локатор для кнопки "Заказать" в блоке "Как это работает"
    private By mainOrderButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button[contains(@class,'Button_Button')]");
    //локатор для логотипа Яндекса
    private By yandexLogo = By.xpath(".//a[contains(@class,'Header_LogoYandex')]/img");
    // локатор для кнопки "Статус заказа"
    private By statusOrderButton = By.xpath(".//button[contains(@class,'Header_Link')]");
    // локатор для поля ввода "Статус заказа"
    private By orderNumberInput = By.xpath(".//input[contains(@class,'Header_Input')]");
    // локатор для кнопки "Go"
    private By goButton = By.xpath(".//button[contains(@class,'Header_Button')]");
    // локатор для хедера главной страницы "Яндекс"
    private By headerYandexMainPage = By.xpath(".//header[@id='dzen-header']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    // открытие главной страницы
    public void openMainPage(){
        driver.get(MAIN_PAGE_URL);
        waitForLoadMainPage();
        new WebDriverWait(driver, Duration.ofSeconds(6)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='accordion']")));
        // если появляется кнопка cookie, то нажимаем ее
        if(!driver.findElements(cookieButton).isEmpty()){
            driver.findElement(cookieButton).click();
        }
    }

    // ожидание загрузки страницы
    public void waitForLoadMainPage(){
        new WebDriverWait(driver, Duration.ofSeconds(6)).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='accordion']")));
    }
    // клик по кнопке "Заказать" в хедере
    public void headerOrderButtonClick(){
        driver.findElement(headerOrderButton).click();
    }
    // клик по кнопке "Заказать" в блоке "Как это работает"
    public void mainOrderButtonClick(){
        WebElement mainOrderButtonElement = driver.findElement(mainOrderButton);
        scrollToElement(mainOrderButtonElement);
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(mainOrderButton));
        mainOrderButtonElement.click();
    }

    // клик по вопросу из FAQ
    public void FAQQuestionClick(String question){
        String questionLocatorString = String.format(FAQ_QUESTION_PATTERN, question);
        By questionLocator = By.xpath(questionLocatorString);
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOfElementLocated(questionLocator));
        WebElement questionElement = driver.findElement(questionLocator);
        scrollToElement(questionElement);
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.elementToBeClickable(questionLocator));
        questionElement.click();
    }
    // получение текста ответа
    public String getAnswerText(String question){
        String answerLocator = String.format(FAQ_ANSWER_PATTERN, question);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerLocator)));
        WebElement answerElement = driver.findElement(By.xpath(answerLocator));
        return answerElement.getText();
    }

    // клик по логотипу Яндекс
    public void yandexLogoClick(){
        driver.findElement(yandexLogo).click();
    }
    // клик по кнопке "Статус заказа"
    public void orderStatusButtonClick(){
        driver.findElement(statusOrderButton).click();
    }
    // заполнение поля "Статус заказа"
    public void setOrderNumber(String orderNumber){
        WebElement orderNumberInputElement = driver.findElement(orderNumberInput);
        orderNumberInputElement.clear();
        orderNumberInputElement.sendKeys(orderNumber);
    }
    // клик по кнопке "Go"
    public void goButtonClick(){
        driver.findElement(goButton).click();
    }

    // проверка статуса заказа
    public void checkOrderStatus(String orderNumber){
        orderStatusButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        setOrderNumber(orderNumber);
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(goButton));
        goButtonClick();
    }
    // скролл до элемента
    private void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // ожидание загрузки главной страницы Яндекс
    public void waitForLoadYandexMainPage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(headerYandexMainPage));
    }
}
