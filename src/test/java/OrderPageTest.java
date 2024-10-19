import org.junit.Test;
import pageobjects.MainPage;
import pageobjects.OrderPageWhomScooterFor;
import static constants.FieldErrorMessage.*;
import static constants.URL.*;
import static org.junit.Assert.assertEquals;

// тесты страницы оформления заказа
public class OrderPageTest extends BaseUITest {

    // проверка открытия главной страницы "Яндекс Самокат" при нажатии на логотип "Самокат"
    @Test
    public void scooterLogoClickScooterMainPageOpen(){
        OrderPageWhomScooterFor objOrderPageWhomScooterFor = new OrderPageWhomScooterFor(driver);
        // открываем форму "Для кого самокат" страницы оформления заказа
        objOrderPageWhomScooterFor.openOrderPage();
        // нажимаем на логотип "Самокат"
        objOrderPageWhomScooterFor.scooterLogoClick();
        MainPage objMainPage = new MainPage(driver);
        // ожидаем загрузки главной страницы "Яндекс Самокат"
        objMainPage.waitForLoadMainPage();
        // сравниваем URL главной страницы "Яндекс Самокат" и открытой страницы
        assertEquals("Клик по логотипу Самокат не ведет на главню страницу Яндекс Самокат", MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    // проверка текста ошибки поля "Имя"
    @Test
    public void setFirstNameInvalidCredentialsErrorMessage(){
        OrderPageWhomScooterFor objOrderPage = new OrderPageWhomScooterFor(driver);
        // открываем форму "Для кого самокат" страницы оформления заказа
        objOrderPage.openOrderPage();
        // вводим невалидные данные в поле "Имя"
        objOrderPage.setFirstName("Error");
        // вводим валидные данные в поле "Фамилия"
        objOrderPage.setLastName("Иванов");
        // вводим валидные данные в поле "Адрес"
        objOrderPage.setAddress("Тверь");
        // выбираем станцию метро
        objOrderPage.setStation("Динамо");
        // вводим валидные данные в поле "Телефон"
        objOrderPage.setPhone("+79998887766");
        // нажимаем на кнопку "Далее"
        objOrderPage.furtherButtonClick();
        assertEquals("Неправильный текст ошибки поля Имя", FIRST_NAME_ERROR_MESSAGE, objOrderPage.getFirstNameErrorMessage());
    }
    // проверка текста ошибки поля "Фамилия"
    @Test
    public void setLastNameInvalidCredentialsErrorMessage(){
        OrderPageWhomScooterFor objOrderPage = new OrderPageWhomScooterFor(driver);
        // открываем форму "Для кого самокат" страницы оформления заказа
        objOrderPage.openOrderPage();
        // вводим валидные данные в поле "Имя"
        objOrderPage.setFirstName("Иван");
        // вводим невалидные данные в поле "Фамилия"
        objOrderPage.setLastName("Error");
        // вводим валидные данные в поле "Адрес"
        objOrderPage.setAddress("Тверь");
        // выбираем станцию метро
        objOrderPage.setStation("Динамо");
        // вводим валидные данные в поле "Телефон"
        objOrderPage.setPhone("+79998887766");
        // нажимаем на кнопку "Далее"
        objOrderPage.furtherButtonClick();
        assertEquals("Неправильный текст ошибки поля Фамилия", LAST_NAME_ERROR_MESSAGE, objOrderPage.getLastNameErrorMessage());
    }
    // проверка текста ошибки поля "Адрес"
    @Test
    public void setAddressInvalidCredentialsErrorMessage(){
        OrderPageWhomScooterFor objOrderPage = new OrderPageWhomScooterFor(driver);
        // открываем форму "Для кого самокат" страницы оформления заказа
        objOrderPage.openOrderPage();
        // вводим валидные данные в поле "Имя"
        objOrderPage.setFirstName("Иван");
        // вводим валидные данные в поле "Фамилия"
        objOrderPage.setLastName("Иванов");
        // вводим невалидные данные в поле "Адрес"
        objOrderPage.setAddress("Error");
        // выбираем станцию метро
        objOrderPage.setStation("Динамо");
        // вводим валидные данные в поле "Телефон"
        objOrderPage.setPhone("+79998887766");
        // нажимаем на кнопку "Далее"
        objOrderPage.furtherButtonClick();
        assertEquals("Неправильный текст ошибки поля Адрес", ADDRESS_ERROR_MESSAGE, objOrderPage.getAddressErrorMessage());
    }
    // проверка текста ошибки поля "Станция метро"
    @Test
    public void setStationInvalidCredentialsErrorMessage(){
        OrderPageWhomScooterFor objOrderPage = new OrderPageWhomScooterFor(driver);
        // открываем форму "Для кого самокат" страницы оформления заказа
        objOrderPage.openOrderPage();
        // вводим валидные данные в поле "Имя"
        objOrderPage.setFirstName("Иван");
        // вводим валидные данные в поле "Фамилия"
        objOrderPage.setLastName("Иванов");
        // вводим валидные данные в поле "Адрес"
        objOrderPage.setAddress("Тверь");
        // вводим валидные данные в поле "Телефон"
        objOrderPage.setPhone("+79998887766");
        // нажимаем на кнопку "Далее"
        objOrderPage.furtherButtonClick();
        assertEquals("Неправильный текст ошибки поля Станция метро", STATION_ERROR_MESSAGE, objOrderPage.getStationErrorMessage());
    }
    // проверка текста ошибки поля "Телефон"
    @Test
    public void setPhoneInvalidCredentialsErrorMessage(){
        OrderPageWhomScooterFor objOrderPage = new OrderPageWhomScooterFor(driver);
        // открываем форму "Для кого самокат" страницы оформления заказа
        objOrderPage.openOrderPage();
        // вводим валидные данные в поле "Имя"
        objOrderPage.setFirstName("Иван");
        // вводим валидные данные в поле "Фамилия"
        objOrderPage.setLastName("Иванов");
        // вводим валидные данные в поле "Адрес"
        objOrderPage.setAddress("Тверь");
        // выбираем станцию метро
        objOrderPage.setStation("Динамо");
        // вводим невалидные данные в поле "Телефон"
        objOrderPage.setPhone("Error");
        // нажимаем на кнопку "Далее"
        objOrderPage.furtherButtonClick();
        assertEquals("Неправильный текст ошибки поля Телефон", PHONE_ERROR_MESSAGE, objOrderPage.getPhoneErrorMessage());
    }
}
