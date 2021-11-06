import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class HomeWork6 {
    protected static WebDriver driver;
    private final Logger logger = (Logger) LogManager.getLogger(HomeWork6.class);

    @Test
    public void PersonalAccountTest() throws Exception {
        loginAndGoToPersonalAccount();

        // Заполнение значений атрибутов
        driver.findElement(By.cssSelector("input[id='id_fname']")).clear();
        driver.findElement(By.cssSelector("input[id='id_fname']")).sendKeys("Алексей");
        driver.findElement(By.cssSelector("input[id='id_lname']")).clear();
        driver.findElement(By.cssSelector("input[id='id_lname']")).sendKeys("Нестеренко");
        driver.findElement(By.cssSelector("input[id='id_blog_name']")).sendKeys("Алексей");
        driver.findElement(By.cssSelector("input[id='id_fname_latin']")).sendKeys("Alexey");
        driver.findElement(By.cssSelector("input[id='id_lname_latin']")).sendKeys("Nesterenko");
        driver.findElement(By.cssSelector("input[name='date_of_birth']")).sendKeys("06.09.1985" + Keys.ENTER);
        driver.findElement(By.cssSelector("input[name='country'] + div")).click();
        driver.findElement(By.cssSelector("button[title='Россия']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[data-title='Город'] + div")).click();
        driver.findElement(By.cssSelector("button[title='Анапа']")).click();
        driver.findElement(By.cssSelector("input[data-title='Уровень знания английского языка'] + div")).click();
        driver.findElement(By.cssSelector("button[title='Средний (Intermediate)']")).click();
        driver.findElement(By.cssSelector("input[id='id_ready_to_relocate_1']+ span")).click();
        driver.findElement(By.cssSelector("input[title='Гибкий график'] + span")).click();
        driver.findElement(By.cssSelector("input[name='contact-0-service'] + div")).click();
        driver.findElement(By.cssSelector("button[data-value='telegram']")).click();
        driver.findElement(By.cssSelector("input[id='id_contact-0-value']")).sendKeys("11111");
        driver.findElement(By.cssSelector("button[class*='js-lk-cv-custom-select-add']")).click();
        driver.findElement(By.cssSelector("input[name='contact-1-service'] + div")).click();
        driver.findElement(By.cssSelector("div[data-num='1']>div>div>div>div>div>div>button+button+button+button+button+button+button+button")).click();
        driver.findElement(By.cssSelector("input[id='id_contact-1-value']")).sendKeys("22222");
        driver.findElement(By.cssSelector("select[id='id_gender']")).click();
        driver.findElement(By.cssSelector("option[value='m']")).click();
        driver.findElement(By.cssSelector("input[id='id_work']")).sendKeys("тестировщик");
        driver.findElement(By.cssSelector("input[id='id_company']")).sendKeys("тест");
        logger.info("Заполнены значения атрибутов");
        // Нажатие на кнопку "Сохранить и продолжить"
        driver.findElement(By.cssSelector("button[title='Сохранить и продолжить']")).click();
        Thread.sleep(1000);
        logout();
        loginAndGoToPersonalAccount();
        checkEnteredValues();
        deleteEnteredValues();
        logout();
    }

    private void deleteEnteredValues() throws InterruptedException {
        // Удаляем значения атрибутов
        driver.findElement(By.cssSelector("input[title='Гибкий график'] + span")).click();

        driver.findElement(By.cssSelector("input[id='id_blog_name']")).clear();
        driver.findElement(By.cssSelector("input[id='id_fname_latin']")).clear();
        driver.findElement(By.cssSelector("input[id='id_lname_latin']")).clear();
        driver.findElement(By.cssSelector("input[name='date_of_birth']")).clear();
        driver.findElement(By.cssSelector("input[name='contact-0-service'] + div")).click();
        driver.findElement(By.cssSelector("button[data-empty='Способ связи']")).click();
        driver.findElement(By.cssSelector("input[id='id_contact-0-value']")).clear();
        driver.findElement(By.cssSelector("input[id='id_contact-1-value']")).clear();
        driver.findElement(By.cssSelector("input[id='id_work']")).clear();
        driver.findElement(By.cssSelector("input[id='id_company']")).clear();

        // Нажатие на кнопку "Сохранить и продолжить"
        logger.info("Значения атрибутов удалены");
        driver.findElement(By.cssSelector("button[title='Сохранить и продолжить']")).click();
        Thread.sleep(1000);

    }


    private void checkEnteredValues() {
        Assert.assertEquals("Алексей", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assert.assertEquals("Нестеренко", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assert.assertEquals("Alexey", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assert.assertEquals("Nesterenko", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assert.assertEquals("06.09.1985", driver.findElement(By.cssSelector("input[name='date_of_birth']")).getAttribute("value"));
        Assert.assertEquals("Россия", driver.findElement(By.cssSelector("input[name='country']+div")).getText());
        Assert.assertEquals("Анапа", driver.findElement(By.cssSelector("input[data-title='Город']+div")).getText());
        Assert.assertEquals("Средний (Intermediate)", driver.findElement(By.cssSelector("input[data-title='Уровень знания английского языка']+div")).getText());
        Assert.assertTrue("Есть готовность к переезду", driver.findElement(By.cssSelector("input[id='id_ready_to_relocate_1']")).isSelected());
        Assert.assertTrue("Гибкий график", driver.findElement(By.cssSelector("input[title='Гибкий график']")).isSelected());
        Assert.assertEquals("11111", driver.findElement(By.id("id_contact-0-value")).getAttribute("value"));
        Assert.assertEquals("22222", driver.findElement(By.id("id_contact-1-value")).getAttribute("value"));
        Assert.assertEquals("m", driver.findElement(By.id("id_gender")).getAttribute("value"));
        Assert.assertEquals("тестировщик", driver.findElement(By.id("id_work")).getAttribute("value"));
        Assert.assertEquals("тест", driver.findElement(By.id("id_company")).getAttribute("value"));

    }

    private void loginAndGoToPersonalAccount() {
        //Настройка ВебДрайвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Драйвер поднят");
        //Переход на сайт отуса
        driver.get("https://otus.ru/");
        logger.info("Сайт открыт");
        //Ввод логина и пароля
        driver.findElement(By.cssSelector("span[class*=header2__auth-reg]")).click();
        driver.findElement(By.cssSelector("input[class*='js-email-input']")).sendKeys("nesterenkoav996@gmail.com");
        driver.findElement(By.cssSelector("input[class*='js-psw-input']")).sendKeys("Q1w2e3r4");
        //нажатие на кнопку "Вход и регистрация"
        driver.findElement(By.cssSelector("button[class*='new-button_md']")).click();
        // Переход на форму "Личный кабинет"
        driver.findElement(By.cssSelector("div[class*='item-wrapper__username']")).click();
        driver.findElement(By.xpath("//b[contains(text(), 'Алексей Нестеренко')]")).click();
        logger.info("Открыт личный кабинет");
    }

    private void logout() throws InterruptedException {
        // Выход из "Личного кабинета". Закрытие браузера
        driver.findElement(By.cssSelector("div[class*='item-wrapper__username']")).click();
        driver.findElement(By.cssSelector("a[title='Выход']")).click();
        Thread.sleep(1000);
        logger.info("Вышли из личного кабинета");
        driver.quit();

    }

}
