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

public class Topic12CheckboxRadioTest {
    WebDriver driver;
    WebDriverWait explicitWait;/ // wait tươờng minh: co trang thai cu the cho elemtn
    // visible/ invisible/ presence/number of element/ clickable/..: co trong html nhung ko hien thi


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

    }

    @Test
    public void TC01_default() {
        // default: the input hien
        // custom: the input an
        driver.get("https://material.angular.io/components/checkbox/examples");
        sleepInSeconds(3);
        //Click checkbox
        WebElement zoneAir = driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input"));

        Assert.assertTrue(zoneAir.isSelected());

        //verify checkbox da chon
        //Case 1. Mo ra da thay co check box dc chon
        //Case 2 . Mo ra thay checkbox chua duoc chon -> click
        checkElement(zoneAir);
    }

    @Test
    public void TC02_radioAngular() {
        driver.get("https://material.angular.io/components/radio/examples");
        sleepInSeconds(3);
        WebElement radio = driver.findElement(By.cssSelector("input[value='Summer']"));
        checkElement(radio);
    }

    @Test
    public void TC03_selectAll() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        sleepInSeconds(3);

        List<WebElement> allItem = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement a : allItem) {
            if (!a.isSelected()) { // kiem tra chua click thi clcik
                a.click();
                Assert.assertTrue(a.isSelected()); // verify da selected all
            }
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh(); // sau khi refresh lai va xoa cookie thi list wwebelement A cung se ve rong null -> phia gan lai neu muon theo tac tiep

        List<WebElement> allItem1 = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement a : allItem1) {
            if (!a.isSelected() && (Objects.equals(a.getAttribute("value"), "Heart Attack"))) { // kiem tra chua click thi clcik
                a.click();
                Assert.assertTrue(a.isSelected()); // verify da selected all
            }
        }
    }

    @Test
    public void TC04_selectOneAll() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        sleepInSeconds(3);

        List<WebElement> allItem = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for(WebElement a : allItem) {
            if(!a.isSelected()&&(Objects.equals(a.getAttribute("value"), "Heart Attack"))){ // kiem tra chua click thi clcik
                a.click();
                Assert.assertTrue(a.isSelected()); // verify da selected all
            }
        }
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
