package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic08DropDownListTest {
    WebDriver driver;
    String FirstName = "An", LastName = "Nguyen", Email = getRandomMail(), Password = "123456@aA";
    @BeforeClass
    public void beforeClass(){
        driver =new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void TC01_DropdownList_Register() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.id("gender-female")).click();
        driver.findElement(By.id("FirstName")).sendKeys(FirstName);
        driver.findElement(By.id("LastName")).sendKeys(LastName);

        // check drop down list
        // Create an object of the Select class
        Select selectedDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectedDay.selectByValue("1");
        // verify la single
        Assert.assertFalse(selectedDay.isMultiple());
        // verify co 32 gia tri trong drop down
        System.out.println(selectedDay.getOptions().size());


        Select selectedMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        selectedMonth.selectByVisibleText("May");

        Select selectedYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectedYear.selectByValue("1980");


        driver.findElement(By.id("Email")).sendKeys(Email);
        driver.findElement(By.id("Password")).sendKeys(Password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(Password);

        driver.findElement(By.id("register-button")).click();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

    }


    @Test
    public void TC02_verifyTitle() {

    }

    @Test
    public void TC03_navigateFunction() {

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public String getRandomMail() {
        Random rand_number =new Random();
        String email_address;
        email_address = "automation" + rand_number.nextInt(99999)+"@gmail.net";
        return email_address;
    }

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
