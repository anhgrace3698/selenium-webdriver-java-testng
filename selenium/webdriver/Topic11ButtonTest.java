package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.time.Duration;
import java.util.List;

public class Topic11ButtonTest {
    WebDriver driver;
    // String FirstName = "An", LastName = "Nguyen", Email = getRandomMail(), Password = "123456@aA", day = "1", month = "May", year = "1980";

    //learn basic wait - for loop - ...
    WebDriverWait explicitWait; // wait tươờng minh: co trang thai cu the cho elemtn
    // visible/ invisible/ presence/number of element/ clickable/..: co trong html nhung ko hien thi


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

    }

    @Test
    public void TC01_buttonDisable() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement button =  driver.findElement(By.cssSelector("input.egov-button"));

        // verify bi disable khi chua click vao check box
        Assert.assertFalse(button.isEnabled());
        driver.findElement(By.cssSelector("input#chinhSach")).click(); // click vao check box de enable button
        sleepInSeconds(1);
        Assert.assertTrue(button.isEnabled());

        //chu y dung thu vien color cua selenium de doi mau rgb va hexa
        Color buttonColor = Color.fromString(button.getCssValue("background-color"));
        Assert.assertEquals(buttonColor.asHex(),"#ef5a00");
    }

    @Test
    public void TC02_fahasaButton() {
        driver.get("https://www.fahasa.com/customer/account/create");

        // chuyen tab dang nhap
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        WebElement buttonFahasa = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        // kiem tra buton luc dau disable
        Assert.assertFalse(buttonFahasa.isEnabled());
        System.out.println(buttonFahasa.getCssValue("background-color"));

        //verify mau nen xam
        String buttonFahasaColor = Color.fromString(buttonFahasa.getCssValue("background-color")).asHex();
        System.out.println(buttonFahasaColor);

        // verify mau nen
        Assert.assertEquals(buttonFahasaColor,"#000000");

        // dien email pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("ngocduynguyen0@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

        sleepInSeconds(2);
        // veridy enable
        Assert.assertTrue(buttonFahasa.isEnabled());

        //veridy color
        //Assert.assertEquals(buttonFahasaColor.asHex(),"#C92127");

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

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
