import org.junit.Test;
import pageobjects.MainPage;
import pageobjects.OrderPageWhomScooterFor;
import pageobjects.TrackPage;
import static constants.URL.*;
import static org.junit.Assert.*;

// тесты главной страницы "Яндекс Самокат"
public class MainPageTest extends BaseUITest {

    // проверка открытия главной страницы Яндекс при нажатии логотипа Яндекс
    @Test
    public void yandexLogoClickYandexMainPageOpen(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        // нажимаем на логотип "Яндекс" в хедере
        objMainPage.yandexLogoClick();
        // получаем массив открытых окон
        Object[] windowHandles = driver.getWindowHandles().toArray();
        // переключаем драйвер на новую открытую вкладку
        driver.switchTo().window((String) windowHandles[1]);
        // ожидаем загрузки главной страницы "Яндекс"
        objMainPage.waitForLoadYandexMainPage();
        // сравниваем URL главной страницы "Яндекс" и открытой страницы
        assertEquals("Клик по логотипу Яндекса не ведет на главню страницу Яндекса", YANDEX_MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    // проверка появления изображения "Такого заказа нет" при вводе несуществующего номера заказа в поле "Статус заказа" хедера
    @Test
    public void checkOrderStatusWrongNumberNoSuchOrderImageDisplayed(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        // вводим в поле "Статус заказа" номер несуществующего заказа
        objMainPage.checkOrderStatus("1234");
        TrackPage objTrackPage = new TrackPage(driver);
        // ожидаем загрузки страницы "Статус заказа"
        objTrackPage.waitForLoadNoSuchOrderImage();
        // проверяем появилось ли сообщение "Такого заказа нет"
        assertTrue("Сообщение \"Такого заказа нет\" не появилось", objTrackPage.isNoSuchOrderImageDisplayed());
    }
}
