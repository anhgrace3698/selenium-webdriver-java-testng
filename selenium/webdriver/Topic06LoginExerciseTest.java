package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic06LoginExerciseTest {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver =new FirefoxDriver();
        // chi ap dung cho element, doi 30s neu tim thay thi di luon. k ap dung cho trang browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
//Browser
    @Test
    public void TC01_loginEmpty() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(),"This is a required field.");
    }
    @Test
    public void TC02_invalidEmail() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();

        driver.findElement(By.cssSelector("#email")).sendKeys("123434234@12312.123123");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC03_passUnder6() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();

        driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC04_incorrectPass(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#pass")).clear();

        driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123123123");

        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");

    }

    @Test
    public void TC05_loginSuccess(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSeconds(2);

        //Dang ky 1 tai khoan truoc
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);
        String firstname = "Anh", middlename = "Lan", lastname = "Nguyen", email_address = getRandomMail(), password = "12345678";

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstname);
        driver.findElement(By.cssSelector("input#middlename")).sendKeys(middlename);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastname);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(email_address);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();

        //verify register successfully
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
        String contactInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(firstname));
        Assert.assertTrue(contactInfor.contains(lastname));
        Assert.assertTrue(contactInfor.contains(email_address));

        // Log out
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();

        sleepInSeconds(6);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/");

        // Login by registed account
        sleepInSeconds(2);
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys(email_address);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Login']")).click();


        //Login success
        sleepInSeconds(2);
        String messLogin = driver.findElement(By.cssSelector("p.hello>strong")).getText();
        Assert.assertTrue(messLogin.contains("Hello, "));

        //Verify account information
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),email_address);

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void sleepInSeconds(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomMail() {
        Random rand_number =new Random();
        String email_address;
        email_address = "automation" + rand_number.nextInt(99999)+"@gmail.net";
        return email_address;

    }


}
