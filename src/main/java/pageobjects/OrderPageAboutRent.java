package pageobjects;

import constants.ScooterColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// класс формы "Про аренду"
public class OrderPageAboutRent {

    private WebDriver driver;
    // локатор для поля с датой доставки
    private By deliveryDateInput = By.xpath(".//input[@type='text' and contains(@placeholder,'Когда привезти самокат')]");
    //локатор для выбранного в календаре дня
    private By calendarSelectedDay = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");
    // локатор для поля "Срок аренды"
    private By rentDurationInput = By.xpath(".//div[contains(@class, 'Dropdown-control')]");
    // локатор для выпадающего списка выбора срока аренды
    private By rentDurationDropDown = By.xpath(".//div[@class='Dropdown-menu']");
    // шаблон для выбора срока аренды в выпадающем списке
    private static final String RENT_DURATION = ".//div[@class='Dropdown-menu']/div[position()=%d]";
    // локатор для чекбокса цвета самоката "Черный жемчуг"
    private By scooterColorBlackInput = By.xpath(".//input[contains (@class, 'Checkbox_Input') and @id='black']");
    // локатор для чекбокса цвета самоката "Серая безысходность"
    private By scooterColorGreyInput = By.xpath(".//input[contains (@class, 'Checkbox_Input') and @id='grey']");
    // локатор для поля "Комментарий"
    private By commentInput = By.xpath(".//input[contains (@placeholder, 'Комментарий')]");
    // локатор для кнопки "Заказать" под формой "Про аренду"
    private By orderButton = By.xpath(".//button[contains (@class, 'Button_Middle') and text()='Заказать']");
    // локатор для кнопки "Да" во всплывающем окне подтверждения оформления заказа
    private By confirmOrderYesButton = By.xpath(".//button[text()='Да']");
    // локатор для сообщения "Заказ оформлен" во всплывающем окне
    private By confirmedOrderMessage = By.xpath(".//div[contains(@class,'Order_ModalHeader')]");

    public OrderPageAboutRent(WebDriver driver) {
        this.driver = driver;
    }
    // ожидание загрузки формы "Про аренду"
    public void waitForLoadOrderPageAboutRent(){
        new WebDriverWait(driver, Duration.ofSeconds(6)).
                until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }
    // заполнение поля "Когда привезти самокат"
    public void setDeliveryDate(String deliveryDate){
        // нажимаем на поле ввода даты доставки и вставляем дату доставки из параметра
        driver.findElement(deliveryDateInput).sendKeys(deliveryDate);
        // ожидаем появления календаря
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(calendarSelectedDay));
        // нажимаем на выбранную дату доставки в календаре
        driver.findElement(calendarSelectedDay).click();
    }
    // заполнение поля "Срок аренды"
    public void setRentDuration(int rentDurationInDays){
        // нажимаем на поле ввода срока аренды
        driver.findElement(rentDurationInput).click();
        // ожидаем появления выпадающего списка
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(rentDurationDropDown));
        // выбираем из выпадающего списка количество суток аренды в соответствии с параметром
        driver.findElement(By.xpath(String.format(RENT_DURATION,rentDurationInDays))).click();
    }
    // выбираем цвет самоката
    public void setScooterColor(String color) {
        ScooterColor scooterColor = ScooterColor.NO_COLOR;
        for (ScooterColor colorChoice : ScooterColor.values())
        {
            if(colorChoice.getColor().equals(color)) {
                scooterColor = colorChoice;
            }
        }
        switch(scooterColor) {
            case BLACK:
                driver.findElement(scooterColorBlackInput).click();
                break;
            case GREY:
                driver.findElement(scooterColorGreyInput).click();
                break;
            case BOTH:
                driver.findElement(scooterColorBlackInput).click();
                driver.findElement(scooterColorGreyInput).click();
                break;
        }
    }
    // заполнение поля "Комментарий для курьера"
    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }
    // нажатие кноки "Заказать" под формой "Про аренду"
    public void orderButtonClick(){
        driver.findElement(orderButton).click();
    }
    // заполнение всех полей формы "Про аренду" и нажатие кноки "Заказать"
    public void setAllFields(String deliveryDate, int rentDurationInDays, String scooterColor, String comment){
        setDeliveryDate(deliveryDate);
        setRentDuration(rentDurationInDays);
        setScooterColor(scooterColor);
        setComment(comment);
        orderButtonClick();
    }
    // ожидание загрузки всплывающего окна "Хотите оформить заказ?"
    public void waitForLoadOrderConfirmationWindow(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(confirmOrderYesButton));
    }
    // нажатие кнопки "Да" во всплывающем окне "Хотите оформить заказ?"
    public void orderConfirmYesButtonClick(){
        driver.findElement(confirmOrderYesButton).click();
    }
    // ожидание загрузки всплывающего окна "Заказ оформлен"
    public void waitForLoadConfirmedOrderWindow(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(confirmedOrderMessage));
    }
    // получение текста оформленного заказа во всплывающем окне "Заказ оформлен"
    public String getConfirmedOrderMessage(){
        return driver.findElement(confirmedOrderMessage).getText();
    }
}
