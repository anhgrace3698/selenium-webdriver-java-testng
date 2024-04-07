package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Topic13AlertTest {
    WebDriver driver;
    WebDriverWait explicitWait;// wait tươờng minh: co trang thai cu the cho elemtn
    // visible/ invisible/ presence/number of element/ clickable/..: co trong html nhung ko hien thi

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @Test
    public void TC01_default() {
        //Cac loai alert
        //
    }

    @Test
    public void TC02_radioAngular() {

    }

    @Test
    public void TC03_selectAll() {

    }

    @Test
    public void TC04_selectOneAll() {

    }
    @Test
    public void TC05_customCheckbox() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


//    public String getRandomMail() {
//        Random rand_number =new Random();
//        String email_address;
//        email_address = "automation" + rand_number.nextInt(99999)+"@gmail.net";
//        return email_address;
//    }

    public void checkElement(WebElement item){
        //neu nhu chua dc chon
        if(!item.isSelected()) {
            item.click();
            sleepInSeconds(2);
        }
    }
    public void uncheckElement(WebElement item){
        //neu nhu da duoc chon
        if(item.isSelected()) {
            item.click();
            sleepInSeconds(2);
        }
    }
    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
