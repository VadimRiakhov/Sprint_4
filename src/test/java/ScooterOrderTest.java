import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.*;
import static org.junit.Assert.assertTrue;

// проверка флоу позитивного сценария заказа самоката
@RunWith(Parameterized.class)
public class ScooterOrderTest extends BaseUITest {

    // имя
    private final String firstName;
    // фамилия
    private final String lastName;
    // адрес
    private final String address;
    // станция метро
    private final String station;
    // телефон
    private final String phone;
    // дата доставки
    private final String deliveryDate;
    // срок аренды
    private final int rentDurationInDays;
    // цвет самоката
    private final String scooterColor;
    // комментарий
    private final String comment;

    public ScooterOrderTest(String firstName, String lastName, String address, String station, String phone, String deliveryDate, int rentDurationInDays, String scooterColor, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentDurationInDays = rentDurationInDays;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][]{
                {"Иван", "Иванов", "Тверь", "Лихоборы", "12345678901", "27.10.2024", 2, "Два цвета", ""},
                {"Петр", "Петров", "Псков", "Динамо", "88887776655", "25.10.2024", 4, "Черный", "Домофона нет"},
                {"Андрей", "Кузнецов", "Новосибирск", "Проспект Вернадского", "+79998887766", "01.11.2024", 7, "", "Не звонить"},
                {"Александр", "Невский", "Ростов Великий", "Водный стадион", "+73334445566", "15.11.2024", 1, "Серый", "Захватите пиццу"},
        };
    }

    // проверка флоу позитивного сценария заказа самоката при нажатии на кнопку "Заказать" в хедере
    @Test
    public void scooterOrderHeaderOrderButtonClick(){
        String expectedMessage = "Заказ оформлен";
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        // нажимаем кнопку "Заказать" в хедере
        objMainPage.headerOrderButtonClick();
        OrderPageWhomScooterFor objOrderPageWhomScooterFor = new OrderPageWhomScooterFor(driver);
        objOrderPageWhomScooterFor.openOrderPage();
        // заполняем поля на форме "Для кого самокат" и нажимаем кнопку "Далее"
        objOrderPageWhomScooterFor.setAllFields(firstName, lastName, address, station, phone);
        OrderPageAboutRent objOrderPageAboutRent = new OrderPageAboutRent(driver);
        // ожидаем загрузки формы "Про аренду"
        objOrderPageAboutRent.waitForLoadOrderPageAboutRent();
        // заполняем поля на форме "Про аренду" и нажимаем кнопку "Заказать"
        objOrderPageAboutRent.setAllFields(deliveryDate, rentDurationInDays, scooterColor, comment);
        // ожидаем загрузки всплывающего окна "Хотите оформить заказ?"
         objOrderPageAboutRent.waitForLoadOrderConfirmationWindow();
        // нажимаем кнопку "Да" в окне подтверждения заказа
        objOrderPageAboutRent.orderConfirmYesButtonClick();
        // ожидаем загрузки всплывающего окна "Заказ оформлен"
        objOrderPageAboutRent.waitForLoadConfirmedOrderWindow();
        // получаем текст всплывающего окна "Заказ оформлен"
        String actualMessage = objOrderPageAboutRent.getConfirmedOrderMessage();
        // проверяем что текст всплывающего окна содержит текст "Заказ оформлен"
        assertTrue("Заказ не оформлен", actualMessage.contains(expectedMessage));
    }

    // проверка заказа самоката при нажатии на кнопку "Заказать" в блоке "Как это работает"
    @Test
    public void scooterOrderMainOrderButtonClick(){
        String expectedMessage = "Заказ оформлен";
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        // нажимаем кнопку "Заказать" в блоке "Как это работает"
        objMainPage.mainOrderButtonClick();
        OrderPageWhomScooterFor objOrderPageWhomScooterFor = new OrderPageWhomScooterFor(driver);
        objOrderPageWhomScooterFor.openOrderPage();
        // заполняем поля на форме "Для кого самокат" и нажимаем кнопку "Далее"
        objOrderPageWhomScooterFor.setAllFields(firstName, lastName, address, station, phone);
        OrderPageAboutRent objOrderPageAboutRent = new OrderPageAboutRent(driver);
        // ожидаем загрузки формы "Про аренду"
        objOrderPageAboutRent.waitForLoadOrderPageAboutRent();
        // заполняем поля на форме "Про аренду" и нажимаем кнопку "Заказать"
        objOrderPageAboutRent.setAllFields(deliveryDate, rentDurationInDays, scooterColor, comment);
        // ожидаем загрузки всплывающего окна "Хотите оформить заказ?"
        objOrderPageAboutRent.waitForLoadOrderConfirmationWindow();
        // нажимаем кнопку "Да" в окне подтверждения заказа
        objOrderPageAboutRent.orderConfirmYesButtonClick();
        // ожидаем загрузки всплывающего окна "Заказ оформлен"
        objOrderPageAboutRent.waitForLoadConfirmedOrderWindow();
        // получаем текст всплывающего окна "Заказ оформлен"
        String actualMessage = objOrderPageAboutRent.getConfirmedOrderMessage();
        // проверяем что текст всплывающего окна содержит текст "Заказ оформлен"
        assertTrue("Заказ не оформлен", actualMessage.contains(expectedMessage));
    }
}
